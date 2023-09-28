package io.jmix.uisamples.component.slider;

import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;

public class SliderLoader extends AbstractComponentLoader<Slider> {

    @Override
    protected Slider createComponent() {
        return factory.create(Slider.class);
    }

    @Override
    public void loadComponent() {
        loadInteger(element, "min", resultComponent::setMin);
        loadInteger(element, "max", resultComponent::setMax);
        loadInteger(element, "value", resultComponent::setValue);

        componentLoader().loadSizeAttributes(resultComponent, element);
    }
}
