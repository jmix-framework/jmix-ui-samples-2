package io.jmix.flowuisampler.view.flowui.components.bigdecimalfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Label;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.textfield.JmixBigDecimalField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@ViewController("big-decimal-field-dataaware")
@ViewDescriptor("big-decimal-field-dataaware.xml")
public class BigDecimalFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Product> productDc;
    @ViewComponent
    protected Label labelValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Product product = metadata.create(Product.class);
        product.setPrice(BigDecimal.TEN);
        productDc.setItem(product);
    }

    @Subscribe("bigDecimalField")
    protected void onBigDecimalFieldValueChange(ComponentValueChangeEvent<JmixBigDecimalField, Boolean> changeEvent) {
        BigDecimal price = productDc.getItem().getPrice();
        labelValue.setText(price == null ? "null" : price.toString());
    }
}
