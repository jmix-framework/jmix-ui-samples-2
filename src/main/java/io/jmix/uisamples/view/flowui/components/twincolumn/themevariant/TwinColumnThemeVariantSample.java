package io.jmix.uisamples.view.flowui.components.twincolumn.themevariant;

import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.twincolumn.TwinColumn;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.twincolumn.TwinColumnVariant;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
// sample-hide:start
import io.jmix.uisamples.theme.AppTheme;
import io.jmix.uisamples.theme.ThemeManager;
import org.springframework.beans.factory.annotation.Autowired;
// sample-hide:end

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ViewController("twin-column-theme-variant")
@ViewDescriptor("twin-column-theme-variant.xml")
public class TwinColumnThemeVariantSample extends StandardView {

    // tag::theme-variant-fields[] sample-hide
    @ViewComponent
    private JmixCheckboxGroup<TwinColumnVariant> twinColumnThemeCheckboxGroup;
    @ViewComponent
    private TwinColumn<Customer> customersTwinColumn;
    // end::theme-variant-fields[] sample-hide
    // sample-hide:start
    @Autowired
    private ThemeManager themeManager;
    // sample-hide:end

    // tag::theme-variant-handler[] sample-hide
    // tag::theme-variant-events[] sample-hide
    @Subscribe
    public void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(twinColumnThemeCheckboxGroup, getTwinColumnVariantItemsMap());
    }

    @Subscribe("twinColumnThemeCheckboxGroup")
    public void onTwinColumnThemeCheckboxGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<TwinColumnVariant>, Collection<TwinColumnVariant>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        customersTwinColumn.getElement().getThemeList().clear();

        event.getValue().forEach(customersTwinColumn::addThemeVariants);
    }
    // end::theme-variant-events[] sample-hide

    // tag::variant-map-start[] sample-hide
    private Map<TwinColumnVariant, String> getTwinColumnVariantItemsMap() {
        LinkedHashMap<TwinColumnVariant, String> map = new LinkedHashMap<>();

        map.put(TwinColumnVariant.NO_BORDER, "No border");
    // end::variant-map-start[] sample-hide
        if (themeManager.getCurrentTheme() == AppTheme.LUMO) {            // sample-hide
            // theme-only:lumo
        // tag::variant-map-lumo[] sample-hide
        map.put(TwinColumnVariant.LUMO_NO_ROW_BORDER, "No row borders");
        map.put(TwinColumnVariant.LUMO_CHECKMARKS, "Checkmarks");
        // end::variant-map-lumo[] sample-hide
            // theme-only:lumo:end
        }                                                                // sample-hide
        // tag::variant-map-end[] sample-hide
        map.put(TwinColumnVariant.NO_SPACE_BETWEEN_ACTIONS, "No space between actions");
        return map;
    }
        // end::variant-map-end[] sample-hide
    // end::theme-variant-handler[] sample-hide
}
