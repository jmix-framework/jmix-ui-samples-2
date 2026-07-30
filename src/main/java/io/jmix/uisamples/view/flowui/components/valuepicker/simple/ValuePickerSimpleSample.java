package io.jmix.uisamples.view.flowui.components.valuepicker.simple;

import io.jmix.flowui.component.valuepicker.JmixValuePicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;

@ViewController("value-picker-simple")
@ViewDescriptor("value-picker-simple.xml")
public class ValuePickerSimpleSample extends StandardView {

    // tag::custom-action-field[] sample-hide
    @ViewComponent
    protected JmixValuePicker<String> valuePicker1;
    // end::custom-action-field[] sample-hide
    // tag::custom-value-field[] sample-hide
    @ViewComponent
    protected JmixValuePicker<String> valuePicker2;
    // end::custom-value-field[] sample-hide

    // tag::custom-action-handler[] sample-hide
    @Subscribe("valuePicker1.generate")
    protected void onValuePicker1GenerateActionPerformed(ActionPerformedEvent event) {
        generateValue(valuePicker1);
    }
    // end::custom-action-handler[] sample-hide

    @Subscribe("valuePicker2.generate")
    protected void onValuePicker2GenerateActionPerformed(ActionPerformedEvent event) {
        generateValue(valuePicker2);
    }

    // tag::custom-value-handler[] sample-hide
    @Subscribe("valuePicker2")
    protected void onValuePicker2CustomValueChange(CustomValueSetEvent<JmixValuePicker<String>, String> event) {
        event.getSource().setValue(event.getText());
    }
    // end::custom-value-handler[] sample-hide

    // tag::generate-value[] sample-hide
    protected void generateValue(JmixValuePicker<String> valuePicker) {
        valuePicker.setValue(RandomStringUtils.secure().nextAlphabetic(5, 10));
    }
    // end::generate-value[] sample-hide
}
