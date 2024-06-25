package io.jmix.uisamples.view.flowui.components.twincolumn.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.twincolumn.TwinColumn;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Product;
import io.jmix.uisamples.entity.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ViewController("twin-column-dataaware")
@ViewDescriptor("twin-column-dataaware.xml")
public class TwinColumnDataawareSample extends StandardView {

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

    @Subscribe("twinColumn")
    protected void onTwinColumnValueChange(ComponentValueChangeEvent<TwinColumn<Product>, Product> event) {
        if (productDc.getItem().getTags() == null) {
            return;
        }

        spanValue.setText(getSelectedTagsInstanceName());
    }

    protected String getSelectedTagsInstanceName() {
        return productDc.getItem().getTags()
                .stream()
                .map(ProductTag::getInstanceName)
                .collect(Collectors.joining(", "));
    }
}
