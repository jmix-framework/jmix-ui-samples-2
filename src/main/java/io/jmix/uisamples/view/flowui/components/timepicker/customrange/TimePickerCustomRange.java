package io.jmix.uisamples.view.flowui.components.timepicker.customrange;

import io.jmix.flowui.component.timepicker.TypedTimePicker;
import io.jmix.flowui.view.*;

import java.time.LocalTime;

@ViewController("time-picker-custom-range")
@ViewDescriptor("time-picker-custom-range.xml")
public class TimePickerCustomRange extends StandardView {

    // tag::time-range[] sample-hide
    @ViewComponent
    protected TypedTimePicker<LocalTime> timePicker;

    @Subscribe
    protected void onInit(InitEvent event) {
        timePicker.setMin(LocalTime.of(8, 0));
        timePicker.setMax(LocalTime.of(17, 0));
    }
    // end::time-range[] sample-hide
}
