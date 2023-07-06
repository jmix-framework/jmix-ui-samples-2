package io.jmix.flowuisampler.view.flowui.components.datepicker.customrange;

import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.view.*;

import java.time.LocalDate;

@ViewController("date-picker-custom-range")
@ViewDescriptor("date-picker-custom-range.xml")
public class DatePickerCustomRange extends StandardView {

    @ViewComponent
    protected TypedDatePicker<LocalDate> datePicker;

    @Subscribe
    protected void onInit(InitEvent event) {
        datePicker.setMin(LocalDate.now().minusWeeks(1));
        datePicker.setMax(LocalDate.now().plusWeeks(1));
    }
}
