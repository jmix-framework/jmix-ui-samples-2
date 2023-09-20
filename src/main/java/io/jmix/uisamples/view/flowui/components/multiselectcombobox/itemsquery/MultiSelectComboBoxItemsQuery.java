package io.jmix.uisamples.view.flowui.components.multiselectcombobox.itemsquery;

import com.vaadin.flow.data.provider.Query;
import io.jmix.core.DataManager;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.stream.Stream;

@ViewController("multi-select-combo-box-items-query")
@ViewDescriptor("multi-select-combo-box-items-query.xml")
public class MultiSelectComboBoxItemsQuery extends StandardView {

    @Autowired
    protected DataManager dataManager;

    protected Collection<Customer> customers;

    @Subscribe
    protected void onInit(InitEvent event) {
        customers = dataManager.load(Customer.class).all().list();
    }

    @Install(to = "programmaticMultiSelectComboBox", subject = "itemsFetchCallback")
    protected Stream<Customer> programmaticMultiSelectComboBoxItemsFetchCallback(Query<Customer, String> query) {
        String enteredValue = query.getFilter()
                .orElse("");

        return customers.stream()
                .filter(customer -> customer.getName() != null &&
                        customer.getName().toLowerCase().contains(enteredValue.toLowerCase()))
                .skip(query.getOffset())
                .limit(query.getLimit());
    }
}
