package io.jmix.uisamples.view.flowui.components.datagrid.themevariant;

import com.vaadin.flow.component.grid.GridVariant;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.ComponentUtils;
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

@ViewController("data-grid-theme-variant")
@ViewDescriptor("data-grid-theme-variant.xml")
public class DataGridThemeVariantSample extends StandardView {

    // tag::theme-variant-fields[] sample-hide
    @ViewComponent
    private JmixCheckboxGroup<GridVariant> gridSettingsCheckboxGroup;
    @ViewComponent
    private DataGrid<Customer> ordersDataGrid;
    // end::theme-variant-fields[] sample-hide
    // sample-hide:start
    @Autowired
    private ThemeManager themeManager;
    // sample-hide:end

    @Subscribe
    public void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(gridSettingsCheckboxGroup, getGridThemeVariantItemsMap());
    }

    // tag::theme-variant-handler[] sample-hide
    @Subscribe("gridSettingsCheckboxGroup")
    public void onGridSettingsCheckboxGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<GridVariant>, Collection<GridVariant>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        ordersDataGrid.getElement().getThemeList().clear();

        event.getValue().forEach(ordersDataGrid::addThemeVariants);
    }
    // end::theme-variant-handler[] sample-hide

    private Map<GridVariant, String> getGridThemeVariantItemsMap() {
        LinkedHashMap<GridVariant, String> map = new LinkedHashMap<>();

        map.put(GridVariant.NO_BORDER, "No border");
        map.put(GridVariant.NO_ROW_BORDERS, "No row borders");
        map.put(GridVariant.COLUMN_BORDERS, "Column borders");
        map.put(GridVariant.ROW_STRIPES, "Row stripes");
        if (themeManager.getCurrentTheme() == AppTheme.LUMO) {            // sample-hide
            // theme-only:lumo
            map.put(GridVariant.LUMO_COMPACT, "Compact");
            // theme-only:lumo:end
        }                                                                // sample-hide
        map.put(GridVariant.WRAP_CELL_CONTENT, "Wrap cell content");
        return map;
    }
}
