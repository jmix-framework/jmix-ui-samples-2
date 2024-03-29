package io.jmix.uisamples.view.flowui.components.checkbox.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("checkbox-dataaware")
@ViewDescriptor("checkbox-dataaware.xml")
public class CheckboxDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setActive(true);
        customerDc.setItem(customer);
    }

    @Subscribe("activeCheckbox")
    protected void onActiveCheckboxValueChange(ComponentValueChangeEvent<Checkbox, Boolean> changeEvent) {
        spanValue.setText(customerDc.getItem().isActive().toString());
    }
}
