package io.jmix.flowuisampler.view.flowui.components.valuepicker.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.valuepicker.JmixValuePicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("value-picker-dataaware")
@ViewDescriptor("value-picker-dataaware.xml")
public class ValuePickerDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Customer> customerDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    protected List<String> names = List.of("Katherine", "John", "Andy", "Edward", "George",
            "Philipp", "Dora", "James", "Daniel", "Michael", "Claire", "Joan", "Peter", "Martin");

    @Subscribe
    protected void onInit(InitEvent event) {
        Customer customer = metadata.create(Customer.class);
        customer.setName(getRandomName());
        customerDc.setItem(customer);
    }

    @Subscribe("valuePicker")
    protected void onValuePickerValueChange(ComponentValueChangeEvent<JmixValuePicker<String>, String> event) {
        spanValue.setText(customerDc.getItem().getName());
    }

    @Subscribe("valuePicker.generate")
    protected void onValuePickerGenerateActionPerformed(ActionPerformedEvent event) {
        customerDc.getItem().setName(getRandomName());
    }

    @Subscribe("valuePicker")
    protected void onValuePickerCustomValueChange(CustomValueSetEvent<JmixValuePicker<String>, String> event) {
        customerDc.getItem().setName(event.getText());
    }

    protected String getRandomName() {
        return names.get(RandomUtils.nextInt() % names.size());
    }
}
