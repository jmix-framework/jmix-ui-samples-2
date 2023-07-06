package io.jmix.flowuisampler.view.flowui.components.datepicker.dataaware;

import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@ViewController("date-picker-dataaware")
@ViewDescriptor("date-picker-dataaware.xml")
public class DatePickerDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Order> orderDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Order order = metadata.create(Order.class);
        order.setDate(new Date());
        orderDc.setItem(order);
    }

    @Subscribe("datePicker")
    protected void onDatePickerValueChange(TypedValueChangeEvent<TypedDatePicker<Date>, Date> event) {
        spanValue.setText(String.valueOf(orderDc.getItem().getDate()));
    }
}
