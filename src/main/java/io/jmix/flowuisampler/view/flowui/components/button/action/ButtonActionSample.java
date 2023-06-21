package io.jmix.flowuisampler.view.flowui.components.button.action;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("button-action")
@ViewDescriptor("button-action.xml")
public class ButtonActionSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("someAction")
    protected void onSomeActionButtonClick(ActionPerformedEvent event) {
        notifications.show("Action performed");
    }
}
