package io.jmix.flowuisampler.view.flowui.dialogsandnotifications.notifications.position;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("notification-position")
@ViewDescriptor("notification-position.xml")
public class NotificationPositionSample extends StandardView {

    @ViewComponent
    protected FormLayout buttonsLayout;

    @Autowired
    protected Notifications notifications;

    @Subscribe("standardNotificationButton")
    protected void onStandardNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Position: Standard");
    }

    @Subscribe("clickAllButton")
    protected void onClickAllButtonClick(ClickEvent<JmixButton> event) {
        buttonsLayout.getChildren()
                .forEach(button -> ((JmixButton) button).click());
    }

    @Subscribe("topStretchNotificationButton")
    protected void onTopStretchNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Top Stretch")
                .withPosition(Notification.Position.TOP_STRETCH)
                .show();
    }

    @Subscribe("topStartNotificationButton")
    protected void onTopStartNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Top Start")
                .withPosition(Notification.Position.TOP_START)
                .show();
    }

    @Subscribe("topCenterNotificationButton")
    protected void onTopCenterNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Top Center")
                .withPosition(Notification.Position.TOP_CENTER)
                .show();
    }

    @Subscribe("topEndNotificationButton")
    protected void onTopEndNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Top End")
                .withPosition(Notification.Position.TOP_END)
                .show();
    }

    @Subscribe("middleNotificationButton")
    protected void onMiddleNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Middle")
                .withPosition(Notification.Position.MIDDLE)
                .show();
    }

    @Subscribe("bottomStartNotificationButton")
    protected void onBottomStartNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Bottom Start")
                .withPosition(Notification.Position.BOTTOM_START)
                .show();
    }

    @Subscribe("bottomCenterNotificationButton")
    protected void onBottomCenterNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Bottom Center")
                .withPosition(Notification.Position.BOTTOM_CENTER)
                .show();
    }

    @Subscribe("bottomEndNotificationButton")
    protected void onBottomEndNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Bottom End")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("bottomStretchNotificationButton")
    protected void onBottomStretchNotificationButtonClick(ClickEvent<JmixButton> event) {
        notifications.create("Position: Bottom Stretch")
                .withPosition(Notification.Position.BOTTOM_STRETCH)
                .show();
    }
}
