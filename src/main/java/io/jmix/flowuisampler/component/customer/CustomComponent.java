package io.jmix.flowuisampler.component.customer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.data.value.ContainerValueSource;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CustomComponent extends Composite<VerticalLayout> implements ApplicationContextAware {
    protected InstanceContainer<Customer> customerDc;
    protected UiComponents uiComponents;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        uiComponents = applicationContext.getBean(UiComponents.class);
    }

    public VerticalLayout initContent() {
        VerticalLayout content = super.initContent();

        FormLayout formLayout = uiComponents.create(FormLayout.class);
        formLayout.setId("formLayout");

        //noinspection unchecked
        TypedTextField<String> nameField = uiComponents.create(TypedTextField.class);
        nameField.setId("nameField");

        formLayout.add(nameField);
        content.add(formLayout);

        return content;
    }

    public void setConfigurationDc(InstanceContainer<Customer> configurationDc) {
        this.customerDc = configurationDc;
        Component formLayout = UiComponentUtils.findComponent(getContent(), "formLayout").orElseThrow();
        Component component = formLayout.getChildren()
                .filter(c -> c.getId().orElseThrow().equals("nameField"))
                .findFirst()
                .orElseThrow();
        if (component instanceof TypedTextField<?> nameField) {
            nameField.setValueSource(new ContainerValueSource<>(customerDc, "name"));
        }
    }
}