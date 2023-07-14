package io.jmix.flowuisampler.view.flowui.components.multiselectcombobox.themevariant;

import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.multiselectcombobox.JmixMultiSelectComboBox;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Day;

import java.util.Collection;
import java.util.List;

@ViewController("multi-select-combo-box-theme-variant")
@ViewDescriptor("multi-select-combo-box-theme-variant.xml")
public class MultiSelectComboBoxThemeVariantSample extends StandardView {

    @ViewComponent
    protected JmixMultiSelectComboBox<Day> testMultiSelectComboBox;
    @ViewComponent
    protected JmixCheckboxGroup<String> settingsCheckboxGroup;

    @Subscribe
    protected void onInit(InitEvent event) {
        settingsCheckboxGroup.setItems(getSettingsCheckboxGroupItems());
    }

    @Subscribe("settingsCheckboxGroup")
    protected void onSettingsValueChange(TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        testMultiSelectComboBox.setLabel("");
        testMultiSelectComboBox.setPlaceholder("");
        testMultiSelectComboBox.setHelperText("");
        testMultiSelectComboBox.setThemeName("");
        testMultiSelectComboBox.setReadOnly(false);

        event.getValue().stream()
                .map(String::toLowerCase)
                .forEach(this::applyTestComboBoxTheme);
    }

    protected void applyTestComboBoxTheme(String command) {
        switch (command) {
            case "label" -> testMultiSelectComboBox.setLabel("Label");
            case "placeholder" -> testMultiSelectComboBox.setPlaceholder("Placeholder");
            case "helper-text" -> testMultiSelectComboBox.setHelperText("Helper text");
            case "readonly" -> testMultiSelectComboBox.setReadOnly(true);
            default -> testMultiSelectComboBox.addThemeName(command);
        }
    }

    protected List<String> getSettingsCheckboxGroupItems() {
        return List.of("Label", "Placeholder", "Helper-Text", "Readonly", "Small", "Align-Left", "Align-Center",
                "Align-Right", "Helper-Above-Field");
    }
}
