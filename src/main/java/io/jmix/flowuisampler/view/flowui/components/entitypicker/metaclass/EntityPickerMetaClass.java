package io.jmix.flowuisampler.view.flowui.components.entitypicker.metaclass;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("entity-picker-meta-class")
@ViewDescriptor("entity-picker-meta-class.xml")
public class EntityPickerMetaClass extends StandardView {

    @Autowired
    protected MetadataTools metadataTools;
    @Autowired
    protected Notifications notifications;

    @Subscribe("entityPicker")
    protected void onEntityPickerValueChange(ComponentValueChangeEvent<EntityPicker<Customer>, Customer> event) {
        String str = event.getValue() == null
                ? "null"
                : metadataTools.getInstanceName(event.getValue());
        notifications.show("value = " + str);
    }
}
