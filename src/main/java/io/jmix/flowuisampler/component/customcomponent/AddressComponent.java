package io.jmix.flowuisampler.component.customcomponent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AddressComponent extends Composite<VerticalLayout> implements ApplicationContextAware {

    protected UiComponents uiComponents;
    protected DataComponents dataComponents;

    protected InstanceContainer<Address> addressInstanceContainer;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        uiComponents = applicationContext.getBean(UiComponents.class);
        dataComponents = applicationContext.getBean(DataComponents.class);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected VerticalLayout initContent() {
        VerticalLayout content = super.initContent();

        TypedTextField<String> zipField = uiComponents.create(TypedTextField.class);
        zipField.setId("zipField");
        zipField.setMaxLength(32);
        zipField.setLabel("Zip");

        EntityComboBox<Country> countryEntityComboBox = uiComponents.create(EntityComboBox.class);
        countryEntityComboBox.setId("countryEntityComboBox");
        countryEntityComboBox.setLabel("Country");

        EntityComboBox<Country> cityEntityComboBox = uiComponents.create(EntityComboBox.class);
        cityEntityComboBox.setId("cityEntityComboBox");
        cityEntityComboBox.setLabel("City");

        TypedTextField<String> addressLine = uiComponents.create(TypedTextField.class);
        addressLine.setId("addressLine");
        addressLine.setLabel("Address Line");

        content.add(zipField, countryEntityComboBox, cityEntityComboBox, addressLine);

        return content;
    }

    public void setDataContainer(InstanceContainer<Address> instanceContainer) {
        this.addressInstanceContainer = instanceContainer;
        assignInstanceContainerToTextFields();
        assignInstanceContainerToEntityComboBoxes();
    }

    private void assignInstanceContainerToTextFields() {
        Component zipField = UiComponentUtils.findComponent(getContent(), "zipField").orElseThrow();
        if (zipField instanceof TypedTextField<?> textField) {
            textField.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "zip"));
        }

        Component addressLine = UiComponentUtils.findComponent(getContent(), "addressLine").orElseThrow();
        if (addressLine instanceof TypedTextField<?> textField) {
            textField.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "addressLine"));
        }
    }

    @SuppressWarnings({"unchecked"})
    private void assignInstanceContainerToEntityComboBoxes() {
        Component countryEntityComboBox = UiComponentUtils.findComponent(getContent(), "countryEntityComboBox").orElseThrow();
        if (countryEntityComboBox instanceof EntityComboBox<?> entityComboBox) {
            entityComboBox.setItems(loadEntities(Country.class));
            entityComboBox.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "country"));
        }

        Component cityEntityComboBox = UiComponentUtils.findComponent(getContent(), "cityEntityComboBox").orElseThrow();
        if (cityEntityComboBox instanceof EntityComboBox<?> entityComboBox) {
            entityComboBox.setItems(loadEntities(City.class));
            entityComboBox.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "city"));
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private CollectionContainer loadEntities(Class clazz) {
        CollectionContainer<?> collectionContainer = dataComponents.createCollectionContainer(clazz);

        CollectionLoader loader = dataComponents.createCollectionLoader();

        loader.setQuery("select e from %s e".formatted(clazz.getSimpleName()));
        loader.setContainer(collectionContainer);
        loader.load();

        return collectionContainer;
    }
}
