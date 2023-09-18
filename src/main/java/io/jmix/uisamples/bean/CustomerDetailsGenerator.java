package io.jmix.uisamples.bean;

import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import io.jmix.core.MessageTools;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.ComponentGenerationContext;
import io.jmix.flowui.component.UiComponentsGenerator;
import io.jmix.flowui.data.SupportsValueSource;
import io.jmix.flowui.data.value.ContainerValueSource;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component("uisamples_CustomerDetailsGenerator")
public class CustomerDetailsGenerator {

    protected UiComponents uiComponents;
    protected UiComponentsGenerator componentsGenerator;
    protected DataComponents dataComponents;
    protected MessageTools messageTools;

    protected MetaClass metaClass;

    protected boolean readOnlyMode;

    public CustomerDetailsGenerator(UiComponents uiComponents,
                                    Metadata metadata,
                                    UiComponentsGenerator componentsGenerator,
                                    DataComponents dataComponents,
                                    MessageTools messageTools) {
        this.uiComponents = uiComponents;
        this.componentsGenerator = componentsGenerator;
        this.dataComponents = dataComponents;
        this.messageTools = messageTools;

        this.metaClass = metadata.getClass(Customer.class);
    }

    public FormLayout createCustomerDetailsRenderer() {
        FormLayout formLayout = uiComponents.create(FormLayout.class);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));

        generateComponent(formLayout, metaClass.getProperty("name"));
        generateComponent(formLayout, metaClass.getProperty("lastName"));
        generateComponent(formLayout, metaClass.getProperty("grade"));
        generateComponent(formLayout, metaClass.getProperty("active"));
        generateComponent(formLayout, metaClass.getProperty("email"));
        generateComponent(formLayout, metaClass.getProperty("age"));

        return formLayout;
    }

    protected void generateComponent(FormLayout formLayout, MetaProperty metaProperty) {
        ComponentGenerationContext componentGenerationContext =
                new ComponentGenerationContext(metaClass, metaProperty.getName());

        com.vaadin.flow.component.Component generatedComponent =
                componentsGenerator.generate(componentGenerationContext);
        generatedComponent.setId(metaProperty.getName() + "Field");

        formLayout.add(generatedComponent);
    }

    public void setCustomer(FormLayout formLayout, Customer customer) {
        InstanceContainer<Customer> instanceContainer = dataComponents.createInstanceContainer(Customer.class);
        instanceContainer.setItem(customer);

        formLayout.getChildren()
                .forEach(component -> setValueToComponent(component, instanceContainer));
    }

    protected void setValueToComponent(com.vaadin.flow.component.Component component,
                                       InstanceContainer<Customer> instanceContainer) {
        if (component.getId().isEmpty()) {
            return;
        }

        String id = component.getId().get();
        String propertyName = id.replace("Field", "");

        if (component instanceof SupportsValueSource<?> valueSourceComponent) {
            valueSourceComponent.setValueSource(new ContainerValueSource<>(instanceContainer, propertyName));
        }

        if (component instanceof HasLabel hasLabelComponent) {
            MetaClass metaClass = instanceContainer.getEntityMetaClass();
            hasLabelComponent.setLabel(messageTools.getPropertyCaption(metaClass, propertyName));
        }

        if (component instanceof HasValue<?, ?> hasValueComponent) {
            hasValueComponent.setReadOnly(readOnlyMode);
        }
    }

    public void setReadOnlyMode(boolean readOnlyMode) {
        this.readOnlyMode = readOnlyMode;
    }
}
