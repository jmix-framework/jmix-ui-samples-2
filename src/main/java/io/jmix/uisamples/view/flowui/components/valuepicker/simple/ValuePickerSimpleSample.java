package io.jmix.uisamples.view.flowui.components.valuepicker.simple;

import io.jmix.flowui.component.valuepicker.JmixValuePicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;

@ViewController("value-picker-simple")
@ViewDescriptor("value-picker-simple.xml")
public class ValuePickerSimpleSample extends StandardView {

    @ViewComponent
    protected JmixValuePicker<String> valuePicker1;
    @ViewComponent
    protected JmixValuePicker<String> valuePicker2;

    @Subscribe("valuePicker1.generate")
    protected void onValuePicker1GenerateActionPerformed(ActionPerformedEvent event) {
        generateValue(valuePicker1);
    }

    @Subscribe("valuePicker2.generate")
    protected void onValuePicker2GenerateActionPerformed(ActionPerformedEvent event) {
        generateValue(valuePicker2);
    }

    @Subscribe("valuePicker2")
    protected void onValuePicker2CustomValueChange(CustomValueSetEvent<JmixValuePicker<String>, String> event) {
        event.getSource().setValue(event.getText());
    }

    protected void generateValue(JmixValuePicker<String> valuePicker) {
        valuePicker.setValue(RandomStringUtils.secure().nextAlphabetic(5, 10));
    }
}
