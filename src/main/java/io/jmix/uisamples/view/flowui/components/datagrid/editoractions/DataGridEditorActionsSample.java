package io.jmix.uisamples.view.flowui.components.datagrid.editoractions;

import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-editor-actions")
@ViewDescriptor("data-grid-editor-actions.xml")
public class DataGridEditorActionsSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;
    @ViewComponent
    protected CollectionContainer<Customer> customersDc;

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected Metadata metadata;

    @Subscribe("customersDataGrid.create")
    protected void onCustomersDataGridCreate(ActionPerformedEvent event) {
        if (customersDataGrid.getEditor().isOpen()) {
            showNotification("Close the editor before creating a new entity");
        } else {
            Customer newCustomer = metadata.create(Customer.class);
            customersDc.getMutableItems().add(newCustomer);
            customersDataGrid.select(newCustomer);
            customersDataGrid.getEditor().editItem(newCustomer);
        }
    }

    @Subscribe("customersDataGrid.edit")
    protected void onCustomersDataGridEdit(ActionPerformedEvent event) {
        Customer selectedCustomer = customersDataGrid.getSingleSelectedItem();

        if (selectedCustomer != null && !customersDataGrid.getEditor().isOpen()) {
            customersDataGrid.getEditor().editItem(selectedCustomer);
        } else {
            showNotification("The editor is already open");
        }
    }

    protected void showNotification(String message) {
        notifications.create(message)
                .withType(Notifications.Type.WARNING)
                .withCloseable(false)
                .show();
    }
}
