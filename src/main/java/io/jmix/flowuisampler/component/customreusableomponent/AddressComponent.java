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
import io.jmix.flowuisampler.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AddressComponent extends Composite<VerticalLayout> implements ApplicationContextAware {
    @Autowired
    protected DataComponents dataComponents;
    @Autowired
    protected FetchPlans fetchPlans;

    protected InstanceContainer<Address> addressInstanceContainer;
    protected UiComponents uiComponents;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        uiComponents = applicationContext.getBean(UiComponents.class);
    }

    public VerticalLayout initContent() {
        VerticalLayout content = super.initContent();

        VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
        verticalLayout.setId("verticalLayout");

        //noinspection unchecked
        TypedTextField<String> zipField = uiComponents.create(TypedTextField.class);
        zipField.setId("zipField");

        //noinspection unchecked
        EntityComboBox<Country> countryEntityComboBox = uiComponents.create(EntityComboBox.class);
        countryEntityComboBox.setId("countryEntityComboBox");

        verticalLayout.add(zipField);
        verticalLayout.add(countryEntityComboBox);
        content.add(verticalLayout);

        return content;
    }

    public void setConfigurationDc(InstanceContainer<Address> instanceContainer) {
        this.addressInstanceContainer = instanceContainer;
        assignInstanceContainerToZipField();
        assignInstanceContainerToCountryEntityComboBox();
    }

    @SuppressWarnings({"unchecked"})
    private void assignInstanceContainerToCountryEntityComboBox() {
        Component component = getComponent("countryEntityComboBox");
        if (component instanceof EntityComboBox<?> entityComboBox) {
            entityComboBox.setItems(loadCountries());
            entityComboBox.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "country"));
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private CollectionContainer loadCountries() {
        FetchPlan fetchPlan = fetchPlans.builder(Country.class)
                .addFetchPlan(FetchPlan.LOCAL)
                .build();

        String query = "select e from sampler_Country e";

        CollectionContainer<?> collectionContainer = dataComponents.createCollectionContainer(Country.class);
        collectionContainer.setFetchPlan(fetchPlan);

        CollectionLoader loader = dataComponents.createCollectionLoader();

        loader.setQuery(query);
        loader.setContainer(collectionContainer);
        loader.load();

        return collectionContainer;
    }

    private void assignInstanceContainerToZipField() {
        Component component = getComponent("zipField");
        if (component instanceof TypedTextField<?> nameField) {
            nameField.setValueSource(new ContainerValueSource<>(addressInstanceContainer, "zip"));
        }
    }

    private Component getComponent(String componentId) {
        Component formLayout = UiComponentUtils.findComponent(getContent(), "verticalLayout").orElseThrow();
        return formLayout.getChildren()
                .filter(c -> c.getId().orElseThrow().equals(componentId))
                .findFirst()
                .orElseThrow();
    }
}
