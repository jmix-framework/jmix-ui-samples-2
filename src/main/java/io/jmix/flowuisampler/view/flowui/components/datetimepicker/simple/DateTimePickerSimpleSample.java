package io.jmix.flowuisampler.view.flowui.components.datetimepicker.simple;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@ViewController("date-time-picker-simple")
@ViewDescriptor("date-time-picker-simple.xml")
public class DateTimePickerSimpleSample extends StandardView {

    @ViewComponent
    protected TypedDateTimePicker<LocalDate> dateTimePicker;

    @Autowired
    protected Notifications notifications;

    @Subscribe("button")
    protected void onButtonClick(ClickEvent<JmixButton> event) {
        notifications.show(String.valueOf(dateTimePicker.getTypedValue()));
    }
}
