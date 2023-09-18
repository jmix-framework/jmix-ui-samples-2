package io.jmix.uisamples.view.flowui.components.textfield.valuechange;

import com.google.common.base.Strings;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.data.value.ValueChangeMode;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ViewController("text-field-value-change")
@ViewDescriptor("text-field-value-change.xml")
public class TextFieldValueChangeSample extends StandardView {

    @ViewComponent
    protected TypedTextField<String> valueChangeModeTextField;
    @ViewComponent
    protected JmixRadioButtonGroup<ValueChangeMode> valueChangeModeRadioButtonGroup;

    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(valueChangeModeRadioButtonGroup, getValueChangeModeItemsMap());
        valueChangeModeRadioButtonGroup.setValue(ValueChangeMode.ON_CHANGE);
    }

    @Subscribe("valueChangeModeRadioButtonGroup")
    protected void onValueChangeModeRadioButtonValueChange(
            ComponentValueChangeEvent<JmixRadioButtonGroup<ValueChangeMode>, ValueChangeMode> event) {
        valueChangeModeTextField.setValueChangeMode(event.getValue());
    }

    @Subscribe("textField")
    protected void onTextFieldValueChange(TypedValueChangeEvent<TypedTextField<String>, String> event) {
        notifications.show("Text Changed: " + event.getValue());
    }

    @Subscribe("valueChangeModeTextField")
    protected void onValueChangeModeTextFieldValueChange(TypedValueChangeEvent<TypedTextField<String>, String> event) {
        TypedTextField<String> sourceTextField = event.getSource();
        sourceTextField.setHelperText(Strings.nullToEmpty(event.getValue()).length() + "/" + sourceTextField.getMaxLength());
    }

    protected Map<ValueChangeMode, String> getValueChangeModeItemsMap() {
        return Arrays.stream(ValueChangeMode.values())
                .collect(Collectors.toMap(Function.identity(), mode -> mode.name().replace('_', ' ')));
    }
}
