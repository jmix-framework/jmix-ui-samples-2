package io.jmix.flowuisampler.view.flowui.components.datagrid.frozencolumn;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

@ViewController("data-grid-frozen-column")
@ViewDescriptor("data-grid-frozen-column.xml")
public class DataGridFrozenColumnSample extends StandardView {

    @ViewComponent
    protected HorizontalLayout checkboxPlaceholder;
    @ViewComponent
    protected JmixSelect<Grid.Column<Customer>> columnSelect;
    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(columnSelect, getColumnItemsMap());
    }

    @Subscribe("columnSelect")
    protected void onColumnSelectChange(
            ComponentValueChangeEvent<JmixSelect<Grid.Column<Customer>>, Grid.Column<Customer>> event) {
        checkboxPlaceholder.removeAll();

        Grid.Column<Customer> column = event.getValue();
        checkboxPlaceholder.add(createFrozenCheckbox(column), createFrozenToEndCheckbox(column));
    }

    protected Map<Grid.Column<Customer>, String> getColumnItemsMap() {
        return customersDataGrid.getAllColumns().stream()
                .collect(Collectors.toMap(col -> col, col -> col.getHeaderText()));
    }

    protected JmixCheckbox createFrozenCheckbox(Grid.Column<Customer> column) {
        JmixCheckbox frozenCheckbox = uiComponents.create(JmixCheckbox.class);
        frozenCheckbox.setLabel("Frozen");
        frozenCheckbox.setValue(column.isFrozen());

        frozenCheckbox.addValueChangeListener(event -> column.setFrozen(event.getValue()));

        return frozenCheckbox;
    }

    protected JmixCheckbox createFrozenToEndCheckbox(Grid.Column<Customer> column) {
        JmixCheckbox frozenCheckbox = uiComponents.create(JmixCheckbox.class);
        frozenCheckbox.setLabel("Frozen To End");
        frozenCheckbox.setValue(column.isFrozenToEnd());

        frozenCheckbox.addValueChangeListener(event -> column.setFrozenToEnd(event.getValue()));

        return frozenCheckbox;
    }
}
