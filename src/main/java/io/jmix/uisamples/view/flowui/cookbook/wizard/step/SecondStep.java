package io.jmix.uisamples.view.flowui.cookbook.wizard.step;

import com.vaadin.flow.component.Component;
import io.jmix.flowui.component.ComponentContainer;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.model.ViewData;
import io.jmix.uisamples.component.addresscomponent.AddressComponent;
import io.jmix.uisamples.view.flowui.cookbook.wizard.WizardStepFragment;

import java.util.Collection;
import java.util.Optional;

public class SecondStep extends Fragment<AddressComponent>
        implements WizardStepFragment, ComponentContainer {

    @Override
    public void setupData(ViewData viewData) {
        getContent().setDataContainer(viewData.getContainer("addressDc"));
    }

    @Override
    public Optional<Component> findOwnComponent(String id) {
        return getContent().getContent().findOwnComponent(id);
    }

    @Override
    public Collection<Component> getOwnComponents() {
        return getContent().getContent().getOwnComponents();
    }
}
