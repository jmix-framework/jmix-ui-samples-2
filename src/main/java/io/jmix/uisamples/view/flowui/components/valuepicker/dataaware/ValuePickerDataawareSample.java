package io.jmix.uisamples.view.flowui.components.valuepicker.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.valuepicker.JmixValuePicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("value-picker-dataaware")
@ViewDescriptor("value-picker-dataaware.xml")
public class ValuePickerDataawareSample extends StandardView {

    // tag::basics-container[] sample-hide
    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    // end::basics-container[] sample-hide
    @ViewComponent
    protected Span spanValue;

    // tag::basics-data[] sample-hide
    @Autowired
    protected Metadata metadata;

    protected List<String> names = List.of("Katherine", "John", "Andy", "Edward", "George",
            "Philipp", "Dora", "James", "Daniel", "Michael", "Claire", "Joan", "Peter", "Martin");
    // end::basics-data[] sample-hide

    // tag::basics-init[] sample-hide
    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setName(getRandomName());
        customerDc.setItem(customer);
    }
    // end::basics-init[] sample-hide

    @Subscribe("valuePicker")
    protected void onValuePickerValueChange(ComponentValueChangeEvent<JmixValuePicker<String>, String> event) {
        spanValue.setText(customerDc.getItem().getName());
    }

    // tag::basics-actions[] sample-hide
    @Subscribe("valuePicker.generate")
    protected void onValuePickerGenerateActionPerformed(ActionPerformedEvent event) {
        customerDc.getItem().setName(getRandomName());
    }

    @Subscribe("valuePicker")
    protected void onValuePickerCustomValueChange(CustomValueSetEvent<JmixValuePicker<String>, String> event) {
        customerDc.getItem().setName(event.getText());
    }

    protected String getRandomName() {
        return names.get(RandomUtils.secure().randomInt() % names.size());
    }
    // end::basics-actions[] sample-hide
}
