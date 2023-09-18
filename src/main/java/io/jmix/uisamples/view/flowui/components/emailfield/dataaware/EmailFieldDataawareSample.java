package io.jmix.uisamples.view.flowui.components.emailfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("email-field-dataaware")
@ViewDescriptor("email-field-dataaware.xml")
public class EmailFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setEmail("example@jmix.io");
        customerDc.setItem(customer);
    }

    @Subscribe("emailField")
    protected void onEmailFieldValueChange(ComponentValueChangeEvent<EmailField, String> changeEvent) {
        spanValue.setText(customerDc.getItem().getEmail());
    }
}
