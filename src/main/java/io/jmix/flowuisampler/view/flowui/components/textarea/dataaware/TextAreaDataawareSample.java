package io.jmix.flowuisampler.view.flowui.components.textarea.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Label;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("text-area-dataaware")
@ViewDescriptor("text-area-dataaware.xml")
public class TextAreaDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Order> orderDc;
    @ViewComponent
    protected Label labelValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Order order = metadata.create(Order.class);
        order.setDescription("Deliver as quickly as possible.");
        orderDc.setItem(order);
    }

    @Subscribe("textArea")
    protected void onTextAreaValueChange(ComponentValueChangeEvent<JmixTextArea, Boolean> changeEvent) {
        labelValue.setText(orderDc.getItem().getDescription());
    }
}
