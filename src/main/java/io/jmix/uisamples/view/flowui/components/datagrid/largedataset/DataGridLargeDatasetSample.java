package io.jmix.uisamples.view.flowui.components.datagrid.largedataset;

import io.jmix.core.LoadContext;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@ViewController("data-grid-large-dataset")
@ViewDescriptor("data-grid-large-dataset.xml")
public class DataGridLargeDatasetSample extends StandardView {

    protected static final int COUNT = 1000;

    @ViewComponent
    protected CollectionContainer<Customer> customersDc;

    @Autowired
    protected Metadata metadata;

    protected Collection<Customer> items;

    @Subscribe
    protected void onInit(InitEvent event) {
        items = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            Customer customer = metadata.create(Customer.class);
            customer.setName("First Name " + i);
            customer.setLastName("Last Name " + i);
            items.add(customer);
        }
    }

    @Install(to = "customersDl", target = Target.DATA_LOADER)
    protected Collection<Customer> customersDlLoadDelegate(LoadContext<Customer> loadContext) {
        return items;
    }
}
