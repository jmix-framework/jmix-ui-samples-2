package io.jmix.uisamples.view.flowui.cookbook.wizard.step;

import com.vaadin.flow.component.Component;
import io.jmix.flowui.component.ComponentContainer;
import io.jmix.flowui.component.formlayout.JmixFormLayout;
import io.jmix.flowui.data.SupportsValueSource;
import io.jmix.flowui.data.value.ContainerValueSource;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragment.FragmentUtils;
import io.jmix.flowui.model.ViewData;
import io.jmix.uisamples.view.flowui.cookbook.wizard.WizardStepFragment;

import java.util.Collection;
import java.util.Optional;

@FragmentDescriptor("first-step.xml")
public class FirstStep extends Fragment<JmixFormLayout>
        implements WizardStepFragment, ComponentContainer {

    public void setupData(ViewData viewData) {
        getOwnComponents().forEach(component -> {
            if (component instanceof SupportsValueSource<?> valueSourceComponent) {
                valueSourceComponent.setValueSource(
                        new ContainerValueSource<>(viewData.getContainer("employeeDc"),
                                FragmentUtils.getComponentId(component).orElseThrow())
                );
            }
        });
    }

    @Override
    public Optional<Component> findOwnComponent(String id) {
        return getContent().findOwnComponent(id);
    }

    @Override
    public Collection<Component> getOwnComponents() {
        return getContent().getOwnComponents();
    }
}
