package io.jmix.uisamples.view.flowui.components.combobox.customitems;

import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ViewController("combobox-custom-items")
@ViewDescriptor("combobox-custom-items.xml")
public class ComboBoxCustomItemsSample extends StandardView {

    // tag::items-map-field[] sample-hide
    @ViewComponent
    protected JmixComboBox<Integer> ageComboBox;
    // end::items-map-field[] sample-hide
    // tag::items-list-field[] sample-hide
    @ViewComponent
    protected JmixComboBox<BigDecimal> amountComboBox;
    // end::items-list-field[] sample-hide

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected InstanceContainer<Order> orderDc;

    @Autowired
    protected Metadata metadata;
    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        // InstanceContainer initialization. It is usually done automatically if the view is
        // inherited from StandardDetailView and is used as an entity detail.
        Order order = metadata.create(Order.class);
        orderDc.setItem(order);

        Customer customer = metadata.create(Customer.class);
        customerDc.setItem(customer);

        // tag::items-list-set[] sample-hide
        amountComboBox.setItems(getAmountItemsList());
        // end::items-list-set[] sample-hide
        // tag::items-map-set[] sample-hide
        ComponentUtils.setItemsMap(ageComboBox, getAgeItemsMap());
        // end::items-map-set[] sample-hide
    }

    // tag::items-list-source[] sample-hide
    protected List<BigDecimal> getAmountItemsList() {
        return List.of(
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(3000),
                BigDecimal.valueOf(4000)
        );
    }
    // end::items-list-source[] sample-hide

    // tag::items-map-source[] sample-hide
    protected Map<Integer, String> getAgeItemsMap() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        return map;
    }
    // end::items-map-source[] sample-hide

    @Subscribe(id = "orderDc", target = Target.DATA_CONTAINER)
    protected void onOrderDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Order> event) {
        itemPropertyChanged(event);
    }

    @Subscribe(id = "customerDc", target = Target.DATA_CONTAINER)
    protected void onCustomerDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Customer> event) {
        itemPropertyChanged(event);
    }

    protected void itemPropertyChanged(InstanceContainer.ItemPropertyChangeEvent<?> event) {
        String message = event.getItem().getClass().getSimpleName()
                + "." + event.getProperty()
                + " = " + event.getValue();
        notifications.show(message);
    }
}
