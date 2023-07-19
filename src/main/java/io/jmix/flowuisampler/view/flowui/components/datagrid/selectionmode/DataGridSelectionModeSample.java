package io.jmix.flowuisampler.view.flowui.components.datagrid.selectionmode;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.grid.Grid;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@ViewController("data-grid-selection-mode")
@ViewDescriptor("data-grid-selection-mode.xml")
public class DataGridSelectionModeSample extends StandardView {

    @ViewComponent
    protected JmixSelect<Grid.SelectionMode> selectionModeSelect;
    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected MetadataTools metadataTools;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(selectionModeSelect, getSelectionModeItemsMap());
        selectionModeSelect.setValue(Grid.SelectionMode.NONE);
    }

    @Subscribe("selectionModeSelect")
    protected void onSelectionModeValueChange(
            ComponentValueChangeEvent<JmixSelect<Grid.SelectionMode>, Grid.SelectionMode> event) {
        customersDataGrid.setSelectionMode(event.getValue());
    }

    @Subscribe("customersDataGrid.greetAll")
    protected void onCustomersDataGridGreetAllActionPerformed(ActionPerformedEvent event) {
        Set<Customer> customers = customersDataGrid.getSelectedItems();

        if (customers.isEmpty()) {
            notifications.show("No selection");
        } else {
            String greetString = customers.stream()
                    .map(metadataTools::getInstanceName)
                    .collect(Collectors.joining(", "));

            notifications.show("Hello, " + greetString);
        }
    }

    @Subscribe("customersDataGrid.greetOne")
    protected void onCustomersDataGridGreetOneActionPerformed(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();
        notifications.show(customer != null
                ? "Hello, " + metadataTools.getInstanceName(customer)
                : "No selection");
    }

    protected Map<Grid.SelectionMode, String> getSelectionModeItemsMap() {
        return Arrays.stream(Grid.SelectionMode.values())
                .collect(Collectors.toMap(Function.identity(), mode -> mode.name().replace('_', ' ')));
    }
}
