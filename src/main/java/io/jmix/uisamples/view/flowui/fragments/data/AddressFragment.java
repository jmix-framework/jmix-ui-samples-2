package io.jmix.uisamples.view.flowui.fragments.data;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.formlayout.JmixFormLayout;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.uisamples.entity.City;
import io.jmix.uisamples.entity.Country;

@FragmentDescriptor("address-fragment.xml")
public class AddressFragment extends Fragment<JmixFormLayout> {

    @ViewComponent
    protected EntityComboBox<City> cityComboBox;
    @ViewComponent
    protected CollectionLoader<Country> countryDl;
    @ViewComponent
    protected CollectionLoader<City> cityDl;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(View.BeforeShowEvent event) {
        countryDl.load();
    }

    @Subscribe(id = "countryDc", target = Target.DATA_CONTAINER)
    public void onCountryDcItemChange(final InstanceContainer.ItemChangeEvent<Country> event) {
        cityDl.setParameter("country", event.getItem());
        cityDl.load();
    }

    @Subscribe("countryComboBox")
    public void onCountryComboBoxValueChange(ComponentValueChangeEvent<EntityComboBox<Country>, Country> event) {
        if (event.isFromClient()) {
            cityComboBox.clear();
        }
    }
}
