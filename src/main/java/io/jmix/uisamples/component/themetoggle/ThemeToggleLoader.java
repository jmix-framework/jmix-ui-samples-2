package io.jmix.uisamples.component.themetoggle;

import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;

public class ThemeToggleLoader extends AbstractComponentLoader<ThemeToggle> {

    @Override
    protected ThemeToggle createComponent() {
        return factory.create(ThemeToggle.class);
    }

    @Override
    public void loadComponent() {
        loadBoolean(element, "autofocus", resultComponent::setAutofocus);

        componentLoader().loadText(resultComponent, element);
        componentLoader().loadTitle(resultComponent, element, context);
        componentLoader().loadTooltip(resultComponent, element);
        componentLoader().loadWhiteSpace(resultComponent, element);
        componentLoader().loadEnabled(resultComponent, element);
        componentLoader().loadClassNames(resultComponent, element);
        componentLoader().loadTabIndex(resultComponent, element);
        componentLoader().loadThemeNames(resultComponent, element);
        componentLoader().loadSizeAttributes(resultComponent, element);
    }
}
