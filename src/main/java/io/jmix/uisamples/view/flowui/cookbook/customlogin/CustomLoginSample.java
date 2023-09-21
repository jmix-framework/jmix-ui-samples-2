package io.jmix.uisamples.view.flowui.cookbook.customlogin;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.notification.NotificationVariant;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.loginform.JmixLoginForm;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-login")
@ViewDescriptor("custom-login.xml")
public class CustomLoginSample extends StandardView {

    @ViewComponent
    protected JmixLoginForm login;

    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(final InitEvent event) {
        login.setUsername("user");
        login.setPassword("user");
    }

    @Subscribe("login")
    protected void onLogin(AbstractLogin.LoginEvent event) {
        Html content = new Html(String.format(getHtmlTemplate(), event.getUsername(), event.getPassword()));

        notifications.create(content)
                .withThemeVariant(NotificationVariant.LUMO_PRIMARY)
                .show();

        event.getSource().setEnabled(true);
    }

    protected String getHtmlTemplate() {
        return """
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <b>Let's pretend that you are logged in as:</b>
                    <span><b>Username: </b> %s</span>
                    <span><b>Password: </b> %s</span>
                </div>
                """;
    }
}
