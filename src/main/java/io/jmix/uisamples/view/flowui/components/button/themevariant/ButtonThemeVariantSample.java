package io.jmix.uisamples.view.flowui.components.button.themevariant;

import com.vaadin.flow.component.icon.VaadinIcon;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.List;

@ViewController("button-theme-variant")
@ViewDescriptor("button-theme-variant.xml")
public class ButtonThemeVariantSample extends StandardView {

    @ViewComponent
    protected JmixButton testButton;
    @ViewComponent
    protected JmixCheckboxGroup<String> settingsCheckboxGroup;

    @Subscribe
    protected void onInit(InitEvent event) {
        settingsCheckboxGroup.setItems(getSettingsCheckboxGroupItems());
    }

    @Subscribe("settingsCheckboxGroup")
    protected void onSettingsValueChange(TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        testButton.setIcon(null);
        testButton.setText("");
        testButton.setThemeName("");

        event.getValue().stream()
                .map(String::toLowerCase)
                .forEach(this::applyTestButtonTheme);
    }

    protected void applyTestButtonTheme(String command) {
        if ("text".equalsIgnoreCase(command)) {
            testButton.setText("Button text");
        } else if ("icon".equalsIgnoreCase(command)) {
            testButton.addThemeName(command);
            testButton.setIcon(VaadinIcon.SMILEY_O.create());
        } else {
            testButton.addThemeName(command);
        }
    }

    protected List<String> getSettingsCheckboxGroupItems() {
        return List.of("Text", "Primary", "Success", "Error", "Contrast",
                "Large", "Small", "Icon", "Tertiary", "Tertiary-inline");
    }
}
