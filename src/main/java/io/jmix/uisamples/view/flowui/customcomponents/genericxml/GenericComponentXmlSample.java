package io.jmix.uisamples.view.flowui.customcomponents.genericxml;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("generic-component-xml")
@ViewDescriptor("generic-component-xml.xml")
public class GenericComponentXmlSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe(id = "button", subject = "clickListener")
    public void onButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Saved!");
    }
}
