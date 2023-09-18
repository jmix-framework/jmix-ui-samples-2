package io.jmix.uisamples.view.flowui.components.entitypicker.simple;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("entity-picker-simple")
@ViewDescriptor("entity-picker-simple.xml")
public class EntityPickerSimpleSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Order> orderDc;

    @Autowired
    protected Metadata metadata;
    @Autowired
    protected MetadataTools metadataTools;
    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        // InstanceContainer initialization. It is usually done automatically if the view is
        // inherited from StandardDetailView and is used as an entity detail.
        Order order = metadata.create(Order.class);
        orderDc.setItem(order);
    }

    @Subscribe(id = "orderDc", target = Target.DATA_CONTAINER)
    protected void onOrderDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Order> event) {
        if (event.getValue() instanceof Customer customer) {
            notifications.create(event.getProperty() + " = " + metadataTools.getInstanceName(customer))
                    .withPosition(Notification.Position.BOTTOM_END)
                    .show();
        }
    }
}
