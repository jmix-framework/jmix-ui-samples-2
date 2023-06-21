package io.jmix.flowuisampler.view.flowui.components.checkbox.simple;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("checkbox-simple")
@ViewDescriptor("checkbox-simple.xml")
public class CheckboxSimpleSample extends StandardView {

    @ViewComponent
    protected JmixCheckbox carField;

    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        carField.setValue(true);
        carField.addValueChangeListener(this::carFieldValueChangeListener);
    }

    protected void carFieldValueChangeListener(ComponentValueChangeEvent<Checkbox, Boolean> changeEvent) {
        notifications.show(Boolean.TRUE.equals(changeEvent.getValue())
                        ? "I have a car"
                        : "I don't have a car");
    }
}
