package io.jmix.uisamples.view.flowui.components.timepicker.simple;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.timepicker.TypedTimePicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

@ViewController("time-picker-simple")
@ViewDescriptor("time-picker-simple.xml")
public class TimePickerSimpleSample extends StandardView {

    @ViewComponent
    protected TypedTimePicker<LocalTime> timePicker;

    @Autowired
    protected Notifications notifications;

    @Subscribe("button")
    protected void onButtonClick(ClickEvent<JmixButton> event) {
        notifications.show(String.valueOf(timePicker.getTypedValue()));
    }
}
