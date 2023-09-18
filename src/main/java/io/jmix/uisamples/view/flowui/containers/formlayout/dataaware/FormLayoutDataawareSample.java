package io.jmix.uisamples.view.flowui.containers.formlayout.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

@ViewController("form-layout-dataaware")
@ViewDescriptor("form-layout-dataaware.xml")
public class FormLayoutDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;

    @Subscribe("customerSelect")
    protected void onCustomerSelectValueChange(ComponentValueChangeEvent<JmixSelect<Customer>, Customer> event) {
        customerDc.setItem(event.getValue());
    }
}
