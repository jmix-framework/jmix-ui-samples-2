package io.jmix.flowuisampler.component.customer;

import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;

public class CustomComponentLoader extends AbstractComponentLoader<CustomComponent> {
    @Override
    protected CustomComponent createComponent() {
        return factory.create(CustomComponent.class);
    }

    @Override
    public void loadComponent() {
        loadId(resultComponent, element);
        loadBoolean(element, "showSaveButton");
    }
}
