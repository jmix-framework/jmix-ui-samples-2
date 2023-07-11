package io.jmix.flowuisampler.view.flowui.components.entitypicker.actions;

import com.vaadin.flow.component.icon.VaadinIcon;
import io.jmix.core.Metadata;
import io.jmix.flowui.Actions;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.action.entitypicker.EntityClearAction;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.action.Action;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.action.BaseAction;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import io.jmix.flowuisampler.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("entity-picker-actions")
@ViewDescriptor("entity-picker-actions.xml")
public class EntityPickerActions extends StandardView {

    @ViewComponent
    protected EntityPicker<Customer> entityPicker;
    @ViewComponent
    protected InstanceContainer<Order> orderDc;

    @Autowired
    protected Metadata metadata;
    @Autowired
    protected Notifications notifications;
    @Autowired
    protected Actions actions;

    @Subscribe
    protected void onInit(InitEvent event) {
        initDc();
        initEntityPickerActions();
    }

    protected void initDc() {
        // InstanceContainer initialization. It is usually done automatically if the view is
        // inherited from StandardDetailView and is used as an entity detail.
        Order order = metadata.create(Order.class);
        orderDc.setItem(order);
    }

    protected void initEntityPickerActions() {
        entityPicker.addAction(createShowGradeAction());
        entityPicker.addAction(createEntityClearAction());
    }

    @Subscribe("entityPicker.greeting")
    protected void onEntityPickerGreetingActionPerformed(ActionPerformedEvent event) {
        Customer customer = entityPicker.getValue();

        if (customer != null) {
            notifications.show("Hello, " + customer.getName());
        } else {
            notifications.show("Choose a customer");
        }
    }

    protected Action createShowGradeAction() {
        return new BaseAction("showGrade")
                .withIcon(VaadinIcon.ACADEMY_CAP)
                .withHandler(actionPerformedEvent -> {
                    Customer customer = entityPicker.getValue();

                    if (customer != null) {
                        notifications.show(customer.getName() + "'s grade is " + customer.getGrade());
                    } else {
                        notifications.show("Choose a customer");
                    }
                });
    }

    protected Action createEntityClearAction() {
        Action entityClearAction = actions.create(EntityClearAction.ID);
        entityClearAction.setIcon(VaadinIcon.BAN.create());

        return entityClearAction;
    }
}
