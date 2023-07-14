package io.jmix.flowuisampler.view.flowui.components.multiselectcombobox.simple;

import io.jmix.flowui.component.multiselectcombobox.JmixMultiSelectComboBox;
import io.jmix.flowui.view.*;

@ViewController("multi-select-combo-box-simple")
@ViewDescriptor("multi-select-combo-box-simple.xml")
public class MultiSelectComboBoxSimpleSample extends StandardView {

    @ViewComponent
    protected JmixMultiSelectComboBox<String> multiSelectComboBox;

    @Subscribe
    protected void onInit(InitEvent event) {
        multiSelectComboBox.setItems("CSS", "HTML", "Java", "JavaScript", "JSON", "Kotlin", "XML");
        multiSelectComboBox.select("Java", "Kotlin");
    }
}
