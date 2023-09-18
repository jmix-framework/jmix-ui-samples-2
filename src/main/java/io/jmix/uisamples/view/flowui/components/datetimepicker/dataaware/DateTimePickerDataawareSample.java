package io.jmix.uisamples.view.flowui.components.datetimepicker.dataaware;

import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@ViewController("date-time-picker-dataaware")
@ViewDescriptor("date-time-picker-dataaware.xml")
public class DateTimePickerDataawareSample extends StandardView {

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

    @Subscribe("dateTimePicker")
    protected void onDateTimePickerValueChange(TypedValueChangeEvent<TypedDateTimePicker<Date>, Date> event) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(taskDc.getItem().getDueDate().toInstant(),
                TimeZone.getDefault().toZoneId());
        spanValue.setText(String.valueOf(localDateTime));
    }
}
