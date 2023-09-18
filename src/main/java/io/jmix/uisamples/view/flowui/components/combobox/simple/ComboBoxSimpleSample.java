package io.jmix.uisamples.view.flowui.components.combobox.simple;

import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("combobox-simple")
@ViewDescriptor("combobox-simple.xml")
public class ComboBoxSimpleSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        // InstanceContainer initialization. It is usually done automatically if the view is
        // inherited from StandardDetailView and is used as an entity detail.
        Customer customer = metadata.create(Customer.class);
        customerDc.setItem(customer);
    }
}
