package io.jmix.flowuisampler.view.flowui.components.datagrid.itemdetailseditor;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.bean.CustomerDetailsEditorGenerator;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-item-details-editor")
@ViewDescriptor("data-grid-item-details-editor.xml")
public class DataGridItemDetailsEditorSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;
    @ViewComponent
    protected CollectionContainer<Customer> customersDc;

    @Autowired
    protected CustomerDetailsEditorGenerator detailsEditorGenerator;
    @Autowired
    protected Metadata metadata;
    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        detailsEditorGenerator.setCustomersDataGrid(customersDataGrid);
        customersDataGrid.setItemDetailsRenderer(createCustomerDetailsRenderer());
        customersDataGrid.setDetailsVisibleOnClick(false);
    }

    protected ComponentRenderer<VerticalLayout, Customer> createCustomerDetailsRenderer() {
        return new ComponentRenderer<>(
                detailsEditorGenerator::createDetailsEditorComponent,
                detailsEditorGenerator::setEditedEntity
        );
    }

    @Subscribe("customersDataGrid.create")
    protected void onCustomersDataGridCreate(ActionPerformedEvent event) {
        if (customersDataGrid.isDetailsVisible(customersDataGrid.getSingleSelectedItem())) {
            showNotification("Close the editor before creating a new entity");
        } else {
            Customer newCustomer = metadata.create(Customer.class);
            customersDc.getMutableItems().add(newCustomer);
            customersDataGrid.select(newCustomer);
            customersDataGrid.setDetailsVisible(newCustomer, true);
        }
    }

    @Subscribe("customersDataGrid.edit")
    protected void onCustomersDataGridEdit(ActionPerformedEvent event) {
        Customer selectedCustomer = customersDataGrid.getSingleSelectedItem();

        if (selectedCustomer != null && !customersDataGrid.isDetailsVisible(selectedCustomer)) {
            customersDataGrid.setDetailsVisible(selectedCustomer, true);
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
