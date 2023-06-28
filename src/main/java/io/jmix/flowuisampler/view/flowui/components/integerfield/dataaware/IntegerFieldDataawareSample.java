package io.jmix.flowuisampler.view.flowui.components.integerfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.IntegerField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("integer-field-dataaware")
@ViewDescriptor("integer-field-dataaware.xml")
public class IntegerFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setAge(26);
        customerDc.setItem(customer);
    }

    @Subscribe("integerField")
    protected void onIntegerFieldValueChange(ComponentValueChangeEvent<IntegerField, Integer> changeEvent) {
        spanValue.setText(String.valueOf(customerDc.getItem().getAge()));
    }
}
