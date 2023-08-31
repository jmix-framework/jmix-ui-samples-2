package io.jmix.flowuisampler.component.address;

import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;

public class AddressComponentLoader extends AbstractComponentLoader<AddressComponent> {
    @Override
    protected AddressComponent createComponent() {
        return factory.create(AddressComponent.class);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void loadComponent() {
        String containerId = loadString(element, "dataContainer").orElse(null);
        if (containerId == null) {
            throw new GuiDevelopmentException(
                    String.format(
                            "%s doesn't have data binding. Set dataContainer attribute.",
                            resultComponent.getClass().getSimpleName()),
                    context);
        }
        InstanceContainer container = getComponentContext().getViewData().getContainer(containerId);
        resultComponent.setConfigurationDc(container);
    }
}
