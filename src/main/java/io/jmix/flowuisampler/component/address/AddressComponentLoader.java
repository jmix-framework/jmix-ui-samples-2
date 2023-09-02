package io.jmix.flowuisampler.component.address;

import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowuisampler.entity.Address;

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
            throwGuiDevelopmentException("%s doesn't have data binding. Set dataContainer attribute.");
        }
        InstanceContainer container = getComponentContext().getViewData().getContainer(containerId);
        if (!container.getEntityMetaClass().getJavaClass().equals(Address.class)) {
            throwGuiDevelopmentException("%s have improper data binding. The value for the dataContainer " +
                    "attribute should be associated with the Address embeddable entity.");
        }
        resultComponent.setConfigurationDc(container);
    }

    private void throwGuiDevelopmentException(String description) {
        throw new GuiDevelopmentException(String.format(description, resultComponent.getClass().getSimpleName()), context);
    }

}
