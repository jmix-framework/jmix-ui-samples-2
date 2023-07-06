package io.jmix.flowuisampler.view.flowui.components.timepicker.customrange;

import io.jmix.flowui.component.timepicker.TypedTimePicker;
import io.jmix.flowui.view.*;

import java.time.Duration;
import java.time.LocalTime;

@ViewController("time-picker-custom-range")
@ViewDescriptor("time-picker-custom-range.xml")
public class TimePickerCustomRange extends StandardView {

    @ViewComponent
    protected TypedTimePicker<LocalTime> timePicker;

    @Subscribe
    protected void onInit(InitEvent event) {
        timePicker.setMin(LocalTime.of(8, 0));
        timePicker.setMax(LocalTime.of(17, 0));
        timePicker.setStep(Duration.ofMinutes(15));
    }
}
