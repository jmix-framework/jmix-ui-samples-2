package io.jmix.uisamples.bean;

import com.vaadin.flow.server.*;
import io.jmix.core.security.ClientDetails;
import io.jmix.core.security.CurrentAuthentication;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Routing datasource to be used for additional data store
 */
public class UiSamplesRoutingDataSource extends AbstractDataSource implements ApplicationContextAware, VaadinServiceInitListener {

    private static final Logger log = LoggerFactory.getLogger(UiSamplesRoutingDataSource.class);
    private static final String SESSION_DATASOURCE_PROPERTY = "jmix.uisamples.session-datasource";

    protected Map<String, DataSource> dataSources = new ConcurrentHashMap<>();
    protected SessionRegistry sessionRegistry = new SessionRegistryImpl();

    protected ApplicationContext applicationContext;

    protected String urlPrefix;
    protected String defaultSessionId;
    protected String sessionDataSourceBeanName;

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getDefaultSessionId() {
        return defaultSessionId;
    }

    public void setDefaultSessionId(String defaultSessionId) {
        this.defaultSessionId = defaultSessionId;
    }

    public String getSessionDataSourceBeanName() {
        return sessionDataSourceBeanName;
    }

    public void setSessionDataSourceBeanName(String sessionDataSourceBeanName) {
        this.sessionDataSourceBeanName = sessionDataSourceBeanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return determineSessionDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineSessionDataSource().getConnection(username, password);
    }

    @Scheduled(fixedRate = 60000)
    public void shutdown() {
        log.debug("Cleaning up session datasources ({})", dataSources.size());
        Iterator<Map.Entry<String, DataSource>> iterator = dataSources.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, DataSource> entry = iterator.next();
            if (defaultSessionId.equals(entry.getKey())) {
                continue;
            }

            SessionInformation session = sessionRegistry.getSessionInformation(entry.getKey());
            if (session == null || session.isExpired()) {
                shutdownSessionDataSource(entry.getKey(), entry.getValue());
                iterator.remove();
                sessionRegistry.removeSessionInformation(entry.getKey());
            }
        }
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(this::onSessionInit);
        event.getSource().addSessionDestroyListener(this::onSessionDestroyed);
    }

    protected void onSessionDestroyed(SessionDestroyEvent event) {
        String sessionId = event.getSession().getSession().getId();

        DataSource sessionDataSource = dataSources.get(sessionId);
        if (sessionDataSource != null) {
            shutdownSessionDataSource(sessionId, sessionDataSource);
            dataSources.remove(sessionId);
        }

        sessionRegistry.removeSessionInformation(sessionId);
    }

    protected void onSessionInit(SessionInitEvent event) {
        WrappedSession session = event.getSession().getSession();
        String sessionId = session.getId();

        CurrentAuthentication currentAuthentication = applicationContext.getBean(CurrentAuthentication.class);
        Object principal = currentAuthentication.getAuthentication().getPrincipal();

        sessionRegistry.registerNewSession(sessionId, principal);
    }

    protected void shutdownSessionDataSource(String sessionId, DataSource sessionDataSource) {
        log.debug("Session with id {} does not exist or has expired, the datasource must be deleted", sessionId);
        try (Statement statement = sessionDataSource.getConnection().createStatement()) {
            statement.executeUpdate("SHUTDOWN");
        } catch (SQLException e) {
            log.warn("Error shutting down datasource {}", sessionId);
        }

        try {
            ((BasicDataSource) sessionDataSource).close();
        } catch (SQLException e) {
            log.warn("Error closing datasource {}", sessionId);
        }
    }

    protected DataSource determineSessionDataSource() {
        String sessionId = getSessionId();
        log.debug("Session datasource with url {} is used", urlPrefix + sessionId);

        if (Objects.equals(
                applicationContext.getEnvironment().getProperty(SESSION_DATASOURCE_PROPERTY),
                Boolean.FALSE.toString())
        ) {
            if (!dataSources.isEmpty()) {
                return dataSources.values().iterator().next();
            }
        }

        return dataSources.computeIfAbsent(sessionId, this::createSessionDataSource);
    }

    protected String getSessionId() {
        CurrentAuthentication currentAuthentication = applicationContext.getBean(CurrentAuthentication.class);

        String sessionId = null;
        if (currentAuthentication.isSet()) {
            Authentication authentication = currentAuthentication.getAuthentication();
            Object details = authentication.getDetails();

            if (details instanceof WebAuthenticationDetails) {
                sessionId = ((WebAuthenticationDetails) details).getSessionId();
            } else if (details instanceof ClientDetails) {
                sessionId = ((ClientDetails) details).getSessionId();
            }
        }

        return sessionId != null ? sessionId : defaultSessionId;
    }

    protected DataSource createSessionDataSource(String sessionId) {
        log.debug("Creating datasource for session {}", sessionId);
        BasicDataSource sessionDataSource = (BasicDataSource) applicationContext.getBean(sessionDataSourceBeanName);
        sessionDataSource.setUrl(urlPrefix + sessionId);

        SpringLiquibase liquibase = applicationContext.getBean(SpringLiquibase.class);
        liquibase.setShouldRun(true);
        liquibase.setDataSource(sessionDataSource);
        try {
            liquibase.afterPropertiesSet();
        } catch (LiquibaseException e) {
            throw new RuntimeException("Error initializing datasource " + urlPrefix + sessionId, e);
        }

        return sessionDataSource;
    }
}
