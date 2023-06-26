package io.jmix.flowuisampler.view.flowui.components.emailfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.EmailField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("email-field-dataaware")
@ViewDescriptor("email-field-dataaware.xml")
public class EmailFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Label labelValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setEmail("example@jmix.io");
        customerDc.setItem(customer);
    }

    @Subscribe("emailField")
    public void onEmailFieldValueChange(ComponentValueChangeEvent<EmailField, Boolean> changeEvent) {
        labelValue.setText(customerDc.getItem().getEmail());
    }
}
