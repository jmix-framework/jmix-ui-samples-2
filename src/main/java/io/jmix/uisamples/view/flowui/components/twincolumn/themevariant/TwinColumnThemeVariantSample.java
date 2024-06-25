package io.jmix.uisamples.view.flowui.components.twincolumn.themevariant;

import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.twincolumn.TwinColumn;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.twincolumn.TwinColumnVariant;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ViewController("twin-column-theme-variant")
@ViewDescriptor("twin-column-theme-variant.xml")
public class TwinColumnThemeVariantSample extends StandardView {

    @ViewComponent
    protected JmixCheckboxGroup<TwinColumnVariant> twinColumnThemeCheckboxGroup;
    @ViewComponent
    protected TwinColumn<Customer> customersTwinColumn;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(twinColumnThemeCheckboxGroup, getTwinColumnVariantItemsMap());
    }

    @Subscribe("twinColumnThemeCheckboxGroup")
    protected void onTwinColumnThemeCheckboxGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<TwinColumnVariant>, Collection<TwinColumnVariant>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        customersTwinColumn.getElement().getThemeList().clear();

        event.getValue().forEach(customersTwinColumn::addThemeVariants);
    }

    protected Map<TwinColumnVariant, String> getTwinColumnVariantItemsMap() {
        LinkedHashMap<TwinColumnVariant, String> map = new LinkedHashMap<>();

        map.put(TwinColumnVariant.NO_BORDER, "No border");
        map.put(TwinColumnVariant.NO_ROW_BORDER, "No row borders");
        map.put(TwinColumnVariant.CHECKMARKS, "Checkmarks");
        map.put(TwinColumnVariant.NO_SPACE_BETWEEN_ACTIONS, "No space between actions");
        return map;
    }
}
