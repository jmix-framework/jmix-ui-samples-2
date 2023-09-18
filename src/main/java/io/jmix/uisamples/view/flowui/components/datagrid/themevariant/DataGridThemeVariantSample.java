package io.jmix.uisamples.view.flowui.components.datagrid.themevariant;

import com.vaadin.flow.component.grid.GridVariant;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ViewController("data-grid-theme-variant")
@ViewDescriptor("data-grid-theme-variant.xml")
public class DataGridThemeVariantSample extends StandardView {

    @ViewComponent
    protected JmixCheckboxGroup<GridVariant> gridSettingsCheckboxGroup;
    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(gridSettingsCheckboxGroup, getGridThemeVariantItemsMap());
    }

    @Subscribe("gridSettingsCheckboxGroup")
    protected void onGridSettingsCheckboxGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<GridVariant>, Collection<GridVariant>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        customersDataGrid.getElement().getThemeList().clear();

        event.getValue().forEach(customersDataGrid::addThemeVariants);
    }

    protected Map<GridVariant, String> getGridThemeVariantItemsMap() {
        LinkedHashMap<GridVariant, String> map = new LinkedHashMap<>();

        map.put(GridVariant.LUMO_NO_BORDER, "No border");
        map.put(GridVariant.LUMO_NO_ROW_BORDERS, "No row borders");
        map.put(GridVariant.LUMO_COLUMN_BORDERS, "Column borders");
        map.put(GridVariant.LUMO_ROW_STRIPES, "Row stripes");
        map.put(GridVariant.LUMO_COMPACT, "Compact");
        map.put(GridVariant.LUMO_WRAP_CELL_CONTENT, "Wrap cell content");
        return map;
    }
}
