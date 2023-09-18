package io.jmix.uisamples.view.flowui.components.select.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("select-dataaware")
@ViewDescriptor("select-dataaware.xml")
public class SelectDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setGrade(CustomerGrade.PREMIUM);
        customerDc.setItem(customer);
    }

    @Subscribe("select")
    protected void onSelectValueChange(
            ComponentValueChangeEvent<JmixSelect<CustomerGrade>, CustomerGrade> changeEvent) {
        spanValue.setText(customerDc.getItem().getGrade().name());
    }
}
