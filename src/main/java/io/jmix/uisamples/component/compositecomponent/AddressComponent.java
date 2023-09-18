package io.jmix.uisamples.component.compositecomponent;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import io.jmix.core.DataManager;
import io.jmix.core.Sort;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.data.value.ContainerValueSource;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.uisamples.entity.Address;
import io.jmix.uisamples.entity.City;
import io.jmix.uisamples.entity.Country;

import java.util.List;

public class AddressComponent extends Composite<FormLayout> {

    protected final DataManager dataManager;
    private final CollectionContainer<Country> countriesContainer;
    private final CollectionContainer<City> citiesContainer;

    private final TypedTextField<String> zipField;
    private final EntityComboBox<Country> countryEntityComboBox;
    private final EntityComboBox<City> cityEntityComboBox;
    private final TypedTextField<String> addressLine;

    @SuppressWarnings({"unchecked"})
    public AddressComponent(UiComponents uiComponents, DataComponents dataComponents, DataManager dataManager) {
        this.dataManager = dataManager;

        zipField = uiComponents.create(TypedTextField.class);
        zipField.setMaxLength(32);
        zipField.setLabel("Zip");

        countryEntityComboBox = uiComponents.create(EntityComboBox.class);
        countryEntityComboBox.setLabel("Country");

        cityEntityComboBox = uiComponents.create(EntityComboBox.class);
        cityEntityComboBox.setLabel("City");

        addressLine = uiComponents.create(TypedTextField.class);
        addressLine.setLabel("Address Line");

        countriesContainer = dataComponents.createCollectionContainer(Country.class);
        countriesContainer.addItemChangeListener(countryItemChangeEvent -> loadCities());
        countryEntityComboBox.setItems(countriesContainer);

        citiesContainer = dataComponents.createCollectionContainer(City.class);
        cityEntityComboBox.setItems(citiesContainer);
    }

    private void loadCountries() {
        List<Country> countries = dataManager.load(Country.class).all().sort(Sort.by("name")).list();
        countriesContainer.setItems(countries);
    }

    private void loadCities() {
        List<City> cities = dataManager.load(City.class)
                .query("e.country = ?1", countriesContainer.getItemOrNull())
                .list();
        citiesContainer.setItems(cities);
    }

    @Override
    protected FormLayout initContent() {
        FormLayout content = super.initContent();
        content.add(zipField, countryEntityComboBox, cityEntityComboBox, addressLine);
        return content;
    }

    public void setDataContainer(InstanceContainer<Address> instanceContainer) {
        zipField.setValueSource(new ContainerValueSource<>(instanceContainer, "zip"));
        addressLine.setValueSource(new ContainerValueSource<>(instanceContainer, "addressLine"));
        countryEntityComboBox.setValueSource(new ContainerValueSource<>(instanceContainer, "country"));
        cityEntityComboBox.setValueSource(new ContainerValueSource<>(instanceContainer, "city"));

        loadCountries();
        loadCities();
    }
}
