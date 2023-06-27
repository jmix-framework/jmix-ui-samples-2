package io.jmix.flowuisampler.view.flowui.components.checkbox.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Paragraph;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("checkbox-dataaware")
@ViewDescriptor("checkbox-dataaware.xml")
public class CheckboxDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Paragraph pValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setActive(true);
        customerDc.setItem(customer);
    }

    @Subscribe("activeCheckbox")
    public void onActiveCheckboxValueChange(ComponentValueChangeEvent<Checkbox, Boolean> changeEvent) {
        pValue.setText(customerDc.getItem().isActive().toString());
    }
}
