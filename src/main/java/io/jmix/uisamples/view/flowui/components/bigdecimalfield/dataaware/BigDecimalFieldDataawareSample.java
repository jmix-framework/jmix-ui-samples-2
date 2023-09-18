package io.jmix.uisamples.view.flowui.components.bigdecimalfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.textfield.JmixBigDecimalField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@ViewController("big-decimal-field-dataaware")
@ViewDescriptor("big-decimal-field-dataaware.xml")
public class BigDecimalFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Product> productDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Product product = metadata.create(Product.class);
        product.setPrice(BigDecimal.TEN);
        productDc.setItem(product);
    }

    @Subscribe("bigDecimalField")
    protected void onBigDecimalFieldValueChange(ComponentValueChangeEvent<JmixBigDecimalField, BigDecimal> changeEvent) {
        BigDecimal price = productDc.getItem().getPrice();
        spanValue.setText(price == null ? "null" : price.toString());
    }
}
