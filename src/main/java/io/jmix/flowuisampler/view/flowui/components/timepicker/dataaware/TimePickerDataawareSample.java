package io.jmix.flowuisampler.view.flowui.components.timepicker.dataaware;

import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.timepicker.TypedTimePicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

@ViewController("time-picker-dataaware")
@ViewDescriptor("time-picker-dataaware.xml")
public class TimePickerDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Task> taskDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Task task = metadata.create(Task.class);
        task.setDueDate(new Date());
        taskDc.setItem(task);
    }

    @Subscribe("timePicker")
    protected void onTimePickerValueChange(TypedValueChangeEvent<TypedTimePicker<Date>, Date> event) {
        LocalTime localTime = LocalTime.ofInstant(taskDc.getItem().getDueDate().toInstant(),
                TimeZone.getDefault().toZoneId());
        spanValue.setText(String.valueOf(localTime));
    }
}
