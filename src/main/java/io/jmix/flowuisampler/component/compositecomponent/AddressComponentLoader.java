package io.jmix.flowuisampler.component.compositecomponent;

import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowuisampler.entity.Address;
import org.dom4j.Element;

public class AddressComponentLoader extends AbstractComponentLoader<AddressComponent> {

    @Override
    protected AddressComponent createComponent() {
        return factory.create(AddressComponent.class);
    }

    @Override
    public void loadComponent() {
        loadDataContainer(resultComponent, element);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void loadDataContainer(AddressComponent resultComponent, Element element) {
        String dataContainerIsNullErrorMessage = String.format(
                "%s doesn't have data binding. Set dataContainer attribute.",
                resultComponent.getClass().getSimpleName()
        );
        String containerId = loadString(element, "dataContainer")
                .orElseThrow(() -> new GuiDevelopmentException(dataContainerIsNullErrorMessage, context));

        InstanceContainer container = getComponentContext().getViewData().getContainer(containerId);
        if (!Address.class.isAssignableFrom(container.getEntityMetaClass().getJavaClass())) {
            String improperDataBindingMessage = String.format(
                    "%s have improper data binding. The value for the " +
                            "dataContainer attribute should be associated with the Address embeddable entity.",
                    resultComponent.getClass().getSimpleName());
            throw new GuiDevelopmentException(improperDataBindingMessage, context);
        }

        resultComponent.setDataContainer(container);
    }
}
