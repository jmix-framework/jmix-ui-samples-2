package io.jmix.uisamples.view.flowui.components.textfield.dataaware;

import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("text-field-dataaware")
@ViewDescriptor("text-field-dataaware.xml")
public class TextFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setName("John");
        customerDc.setItem(customer);
    }

    @Subscribe("textField")
    protected void onTextFieldValueChange(TypedValueChangeEvent<TypedTextField<String>, String> changeEvent) {
        spanValue.setText(customerDc.getItem().getName());
    }
}
