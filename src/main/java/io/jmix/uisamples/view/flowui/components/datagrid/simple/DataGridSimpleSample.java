package io.jmix.uisamples.view.flowui.components.datagrid.simple;

import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;

import java.util.Collection;
import java.util.Collections;

@ViewController("data-grid-simple")
@ViewDescriptor("data-grid-simple.xml")
public class DataGridSimpleSample extends StandardView {

    @ViewComponent
    protected JmixCheckboxGroup<String> dataGridSettingsGroup;
    @ViewComponent
    protected DataGrid<Order> dataGrid;

    @Subscribe
    protected void onInit(InitEvent event) {
        initSettingsGroup();
    }

    protected void initSettingsGroup() {
        dataGridSettingsGroup.setItems("Sortable", "Resizable", "Column reordering allowed");
        dataGridSettingsGroup.setTypedValue(Collections.singletonList("Sortable"));
    }

    @Subscribe("dataGridSettingsGroup")
    protected void onDataGridSettingsGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<String>, Collection<String>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        dataGrid.getAllColumns().forEach(col -> {
            col.setSortable(false);
            col.setResizable(false);
        });
        dataGrid.setColumnReorderingAllowed(false);

        event.getValue()
                .forEach(this::applyGridSettings);
    }

    protected void applyGridSettings(String setting) {
        switch (setting) {
            case "Sortable" -> dataGrid.getAllColumns().forEach(col -> col.setSortable(true));
            case "Resizable" -> dataGrid.getAllColumns().forEach(col -> col.setResizable(true));
            case "Column reordering allowed" -> dataGrid.setColumnReorderingAllowed(true);
        }
    }
}
