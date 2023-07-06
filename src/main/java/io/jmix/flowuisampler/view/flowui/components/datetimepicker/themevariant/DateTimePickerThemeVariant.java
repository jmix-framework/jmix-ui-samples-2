package io.jmix.flowuisampler.view.flowui.components.datetimepicker.themevariant;

import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.view.*;

import java.time.LocalDateTime;

@ViewController("date-time-picker-theme-variant")
@ViewDescriptor("date-time-picker-theme-variant.xml")
public class DateTimePickerThemeVariant extends StandardView {

    @ViewComponent
    protected TypedDateTimePicker<LocalDateTime> dateTimePicker;
    @ViewComponent
    protected TypedDateTimePicker<LocalDateTime> dateTimePicker2;
    @ViewComponent
    protected TypedDateTimePicker<LocalDateTime> dateTimePicker3;

    @Subscribe
    protected void onInit(InitEvent event) {
        dateTimePicker.setTypedValue(LocalDateTime.now());
        dateTimePicker2.setTypedValue(LocalDateTime.now());
        dateTimePicker3.setTypedValue(LocalDateTime.now());
    }
}
