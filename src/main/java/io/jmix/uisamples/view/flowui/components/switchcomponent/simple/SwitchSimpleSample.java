package io.jmix.uisamples.view.flowui.components.switchcomponent.simple;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.Switch;
import io.jmix.flowui.kit.component.checkbox.JmixSwitch;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("switch-simple")
@ViewDescriptor("switch-simple.xml")
public class SwitchSimpleSample extends StandardView {

    @ViewComponent
    private Switch carField;

    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(InitEvent event) {
        carField.setValue(true);
        carField.addValueChangeListener(this::carFieldValueChangeListener);
    }

    private void carFieldValueChangeListener(ComponentValueChangeEvent<JmixSwitch, Boolean> changeEvent) {
        notifications.show(Boolean.TRUE.equals(changeEvent.getValue())
                ? "I have a car"
                : "I don't have a car");
    }
}
