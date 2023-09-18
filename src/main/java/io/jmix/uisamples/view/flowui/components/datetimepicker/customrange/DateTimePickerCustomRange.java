package io.jmix.uisamples.view.flowui.components.datetimepicker.customrange;

import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.view.*;

import java.time.LocalDateTime;

@ViewController("date-time-picker-custom-range")
@ViewDescriptor("date-time-picker-custom-range.xml")
public class DateTimePickerCustomRange extends StandardView {

    @ViewComponent
    protected TypedDateTimePicker<LocalDateTime> dateTimePicker;

    @Subscribe
    protected void onInit(InitEvent event) {
        dateTimePicker.setMin(LocalDateTime.now().minusWeeks(1));
        dateTimePicker.setMax(LocalDateTime.now().plusWeeks(1));
    }
}
