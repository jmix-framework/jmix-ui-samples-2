package io.jmix.flowuisampler.view.flowui.dialogsandnotifications.notifications.simple;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("notification-simple")
@ViewDescriptor("notification-simple.xml")
public class NotificationSimpleSample extends StandardView {

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected UiComponents uiComponents;

    @Subscribe("standardNotificationButton")
    protected void onStandardNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Standard notification");
    }

    @Subscribe("messageNotificationButton")
    protected void onMessageNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Notification with message", "Message");
    }

    @Subscribe("componentNotificationButton")
    protected void onComponentNotificationButtonClick(ClickEvent<JmixButton> event) {
        HorizontalLayout content = uiComponents.create(HorizontalLayout.class);

        Text text = new Text("Hello world!");
        Icon icon = VaadinIcon.SMILEY_O.create();

        content.add(text, icon);

        notifications.show(content);
    }
}
