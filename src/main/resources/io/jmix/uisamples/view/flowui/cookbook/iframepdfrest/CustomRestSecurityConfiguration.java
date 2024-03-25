package io.jmix.uisamples.view.flowui.cookbook.iframepdfrest;

import io.jmix.core.JmixSecurityFilterChainOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomRestSecurityConfiguration {

    @Bean
    @Order(JmixSecurityFilterChainOrder.FLOWUI - 10)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/custom-rest/files/**")
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/custom-rest/files/**").permitAll());
        http.headers(headers ->
                headers.contentSecurityPolicy(secPolicy ->
                        secPolicy.policyDirectives("frame-ancestors demo.jmix.io")
                )
        );

        return http.build();
    }
}
