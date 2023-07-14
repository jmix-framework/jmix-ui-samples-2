package io.jmix.flowuisampler.view.flowui.components.multiselectcombobox.itemlabel;

import io.jmix.flowui.view.Install;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowuisampler.entity.ProductTag;

@ViewController("multi-select-combo-box-item-label")
@ViewDescriptor("multi-select-combo-box-item-label.xml")
public class MultiSelectComboBoxItemLabelSample extends StandardView {

    @Install(to = "multiSelectComboBox", subject = "itemLabelGenerator")
    protected String multiSelectComboBoxItemLabelGenerator(ProductTag productTag) {
        return "#" + productTag.getName();
    }
}
