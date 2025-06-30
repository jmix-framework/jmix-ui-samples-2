package io.jmix.uisamples.view.flowui.components.switchcomponent.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.checkbox.Switch;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("switch-dataaware")
@ViewDescriptor("switch-dataaware.xml")
public class SwitchDataawareSample extends StandardView {

    @ViewComponent
    private InstanceContainer<Customer> customerDc;
    @ViewComponent
    private Span spanValue;

    @Autowired
    private Metadata metadata;

    @Subscribe
    public void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setActive(true);
        customerDc.setItem(customer);
    }

    @Subscribe("activeSwitch")
    private void onActiveSwitchValueChange(ComponentValueChangeEvent<Switch, Boolean> changeEvent) {
        spanValue.setText(customerDc.getItem().isActive().toString());
    }
}
