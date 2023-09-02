package io.jmix.flowuisampler.component.customreusableomponent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.data.value.ContainerValueSource;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowuisampler.entity.Address;
import io.jmix.flowuisampler.entity.City;
import io.jmix.flowuisampler.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AddressComponent extends Composite<VerticalLayout> implements ApplicationContextAware {
    protected UiComponents uiComponents;
    protected DataComponents dataComponents;

    protected InstanceContainer<Address> addressInstanceContainer;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        uiComponents = applicationContext.getBean(UiComponents.class);
        dataComponents = applicationContext.getBean(DataComponents.class);
    }

    protected VerticalLayout initContent() {
        VerticalLayout content = super.initContent();

        VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
        verticalLayout.setId("verticalLayout");

        TypedTextField<String> zipField = uiComponents.create(TypedTextField.class);
        zipField.setId("zipField");
        zipField.setMaxLength(32);

        EntityComboBox<Country> countryEntityComboBox = uiComponents.create(EntityComboBox.class);
        countryEntityComboBox.setId("countryEntityComboBox");

        EntityComboBox<Country> cityEntityComboBox = uiComponents.create(EntityComboBox.class);
        cityEntityComboBox.setId("cityEntityComboBox");

        TypedTextField<String> addressLine = uiComponents.create(TypedTextField.class);
        addressLine.setId("addressLine");

        verticalLayout.add(zipField);
        verticalLayout.add(countryEntityComboBox);
        verticalLayout.add(cityEntityComboBox);
        verticalLayout.add(addressLine);
        content.add(verticalLayout);

        return content;
    }

    public void setConfigurationDc(InstanceContainer<Address> instanceContainer) {
        this.addressInstanceContainer = instanceContainer;
        assignInstanceContainerToTextFields();
        assignInstanceContainerToEntityComboBoxes();
    }

    private void assignInstanceContainerToTextFields() {
        Component zipField = getComponent("zipField");
        if (zipField instanceof TypedTextField<?> textField) {
            textField.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "zip"));
        }
        Component addressLine = getComponent("addressLine");
        if (addressLine instanceof TypedTextField<?> textField) {
            textField.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "addressLine"));
        }
    }

    private void assignInstanceContainerToEntityComboBoxes() {
        Component countryEntityComboBox = getComponent("countryEntityComboBox");
        if (countryEntityComboBox instanceof EntityComboBox<?> entityComboBox) {
            entityComboBox.setItems(loadEntities(Country.class));
            entityComboBox.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "country"));
        }
        Component cityEntityComboBox = getComponent("cityEntityComboBox");
        if (cityEntityComboBox instanceof EntityComboBox<?> entityComboBox) {
            entityComboBox.setItems(loadEntities(City.class));
            entityComboBox.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "city"));
        }
    }

    private CollectionContainer loadEntities(Class clazz) {
        CollectionContainer<?> collectionContainer = dataComponents.createCollectionContainer(clazz);

        CollectionLoader loader = dataComponents.createCollectionLoader();

        loader.setQuery("select e from %s e".formatted(clazz.getSimpleName()));
        loader.setContainer(collectionContainer);
        loader.load();

        return collectionContainer;
    }

    private Component getComponent(String componentId) {
        Component formLayout = UiComponentUtils.findComponent(getContent(), "verticalLayout").orElseThrow();
        return formLayout.getChildren()
                .filter(c -> c.getId().orElseThrow().equals(componentId))
                .findFirst()
                .orElseThrow();
    }
}
