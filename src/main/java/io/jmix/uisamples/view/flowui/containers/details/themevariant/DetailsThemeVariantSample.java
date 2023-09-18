package io.jmix.uisamples.view.flowui.containers.details.themevariant;

import com.vaadin.flow.component.details.Details;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.List;

@ViewController("details-theme-variant")
@ViewDescriptor("details-theme-variant.xml")
public class DetailsThemeVariantSample extends StandardView {

    @ViewComponent
    protected Details firstDetails;
    @ViewComponent
    protected JmixCheckboxGroup<String> firstDetailsSettingsCheckboxGroup;

    @ViewComponent
    protected Details secondDetails;
    @ViewComponent
    protected JmixCheckboxGroup<String> secondDetailsSettingsCheckboxGroup;

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> settingsItems = List.of("Filled", "Reverse", "Small");

        firstDetailsSettingsCheckboxGroup.setItems(settingsItems);
        secondDetailsSettingsCheckboxGroup.setItems(settingsItems);
    }

    @Subscribe("firstDetailsSettingsCheckboxGroup")
    protected void onFirstDetailsSettingsValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        applyDetailsSettings(event.getValue(), firstDetails);
    }

    @Subscribe("secondDetailsSettingsCheckboxGroup")
    protected void onSecondDetailsSettingsValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        applyDetailsSettings(event.getValue(), secondDetails);
    }

    protected void applyDetailsSettings(Collection<String> settings, Details targetDetails) {
        targetDetails.setThemeName("");

        settings.stream()
                .map(String::toLowerCase)
                .forEach(targetDetails::addThemeName);
    }
}
