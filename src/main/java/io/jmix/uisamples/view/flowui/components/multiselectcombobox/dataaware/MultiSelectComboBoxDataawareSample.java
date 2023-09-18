package io.jmix.uisamples.view.flowui.components.multiselectcombobox.dataaware;

import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.multiselectcombobox.JmixMultiSelectComboBox;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Product;
import io.jmix.uisamples.entity.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ViewController("multi-select-combo-box-dataaware")
@ViewDescriptor("multi-select-combo-box-dataaware.xml")
public class MultiSelectComboBoxDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Product> productDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Product product = metadata.create(Product.class);
        productDc.setItem(product);
    }

    @Subscribe("multiSelectComboBox")
    protected void onMultiSelectComboBoxFieldValueChange(
            TypedValueChangeEvent<JmixMultiSelectComboBox<Product>, Product> changeEvent) {
        spanValue.setText(getSelectedTagsInstanceName());
    }

    protected String getSelectedTagsInstanceName() {
        return productDc.getItem().getTags()
                .stream()
                .map(ProductTag::getInstanceName)
                .collect(Collectors.joining(", "));
    }
}
