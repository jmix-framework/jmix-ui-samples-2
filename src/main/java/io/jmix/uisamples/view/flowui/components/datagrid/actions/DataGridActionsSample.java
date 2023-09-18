package io.jmix.uisamples.view.flowui.components.datagrid.actions;

import io.jmix.core.MetadataTools;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-actions")
@ViewDescriptor("data-grid-actions.xml")
public class DataGridActionsSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected MetadataTools metadataTools;

    @Subscribe("customersDataGrid.greeting")
    protected void onCustomersDataGridGreetingActionPerformed(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();

        notifications.show(customer != null
                        ? "Hello, " + metadataTools.getInstanceName(customer)
                        : "No selection");
    }
}
