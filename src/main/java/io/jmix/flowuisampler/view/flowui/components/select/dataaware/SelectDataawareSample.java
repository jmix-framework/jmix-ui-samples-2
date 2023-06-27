package io.jmix.flowuisampler.view.flowui.components.select.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Paragraph;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import io.jmix.flowuisampler.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("select-dataaware")
@ViewDescriptor("select-dataaware.xml")
public class SelectDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Paragraph pValue;

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
        pValue.setText(customerDc.getItem().getGrade().name());
    }
}
