package io.jmix.uisamples.view.flowui.components.combobox.itemsquery;

import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import io.jmix.core.DataManager;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.stream.Stream;

@ViewController("combobox-items-query")
@ViewDescriptor("combobox-items-query.xml")
public class ComboBoxItemsQuerySample extends StandardView {

    @Autowired
    protected DataManager dataManager;

    protected Collection<Customer> customers;

    @Subscribe
    protected void onInit(InitEvent event) {
        customers = dataManager.load(Customer.class).all().list();
    }

    @Supply(to = "programmaticComboBox", subject = "renderer")
    protected Renderer<Customer> comboBoxTextRenderer() {
        return new TextRenderer<>(Customer::getName);
    }

    @Install(to = "programmaticComboBox", subject = "itemsFetchCallback")
    protected Stream<Customer> programmaticComboBoxItemsFetchCallback(Query<Customer, String> query) {
        String enteredValue = query.getFilter()
                .orElse("");

        return customers.stream()
                .filter(customer -> customer.getName() != null &&
                        customer.getName().toLowerCase().contains(enteredValue.toLowerCase()))
                .skip(query.getOffset())
                .limit(query.getLimit());
    }
}
