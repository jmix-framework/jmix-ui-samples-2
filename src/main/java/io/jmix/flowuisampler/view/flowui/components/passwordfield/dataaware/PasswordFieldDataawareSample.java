package io.jmix.flowuisampler.view.flowui.components.passwordfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.PasswordField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("password-field-dataaware")
@ViewDescriptor("password-field-dataaware.xml")
public class PasswordFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<ProductTag> productTagDc;
    @ViewComponent
    protected Label labelValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        ProductTag productTag = metadata.create(ProductTag.class);
        productTag.setName("Secret name");
        productTagDc.setItem(productTag);
    }

    @Subscribe("passwordField")
    protected void onPasswordFieldValueChange(ComponentValueChangeEvent<PasswordField, Boolean> changeEvent) {
        labelValue.setText(productTagDc.getItem().getName());
    }
}
