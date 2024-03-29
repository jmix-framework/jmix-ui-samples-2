package io.jmix.uisamples.view.flowui.components.passwordfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("password-field-dataaware")
@ViewDescriptor("password-field-dataaware.xml")
public class PasswordFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<ProductTag> productTagDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        ProductTag productTag = metadata.create(ProductTag.class);
        productTag.setName("Secret name");
        productTagDc.setItem(productTag);
    }

    @Subscribe("passwordField")
    protected void onPasswordFieldValueChange(ComponentValueChangeEvent<PasswordField, String> changeEvent) {
        spanValue.setText(productTagDc.getItem().getName());
    }
}
