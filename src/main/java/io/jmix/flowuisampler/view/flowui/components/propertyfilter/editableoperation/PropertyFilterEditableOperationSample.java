package io.jmix.flowuisampler.view.flowui.components.propertyfilter.editableoperation;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("property-filter-editable-operation")
@ViewDescriptor("property-filter-editable-operation.xml")
public class PropertyFilterEditableOperationSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("propertyFilter")
    public void onPropertyFilterOperationChange(PropertyFilter.OperationChangeEvent<Customer> event) {
        notifications.create("Prev: " + event.getPreviousOperation().name() + ", New: " + event.getNewOperation().name())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
