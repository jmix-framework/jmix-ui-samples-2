package io.jmix.uisamples.view.flowui.containers.accordion.themevariant;

import com.vaadin.flow.component.accordion.AccordionPanel;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.List;

@ViewController("accordion-theme-variant")
@ViewDescriptor("accordion-theme-variant.xml")
public class AccordionThemeVariantSample extends StandardView {

    @ViewComponent
    protected AccordionPanel firstPanel;
    @ViewComponent
    protected JmixCheckboxGroup<String> firstPanelSettingsCheckboxGroup;

    @ViewComponent
    protected AccordionPanel secondPanel;
    @ViewComponent
    protected JmixCheckboxGroup<String> secondPanelSettingsCheckboxGroup;

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> settingsItems = List.of("Filled", "Reverse", "Small");

        firstPanelSettingsCheckboxGroup.setItems(settingsItems);
        secondPanelSettingsCheckboxGroup.setItems(settingsItems);
    }

    @Subscribe("firstPanelSettingsCheckboxGroup")
    protected void onFirstPanelSettingsValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        applyPanelSettings(event.getValue(), firstPanel);
    }

    @Subscribe("secondPanelSettingsCheckboxGroup")
    protected void onSecondPanelSettingsValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        applyPanelSettings(event.getValue(), secondPanel);
    }

    protected void applyPanelSettings(Collection<String> settings, AccordionPanel targetPanel) {
        targetPanel.setThemeName("");

        settings.stream()
                .map(String::toLowerCase)
                .forEach(targetPanel::addThemeName);
    }
}
