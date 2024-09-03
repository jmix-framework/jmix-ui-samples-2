package io.jmix.uisamples.view.flowui.components.datagrid.contextmenu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.grid.contextmenu.GridMenuItem;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-context-menu")
@ViewDescriptor("data-grid-context-menu.xml")
public class DataGridContextMenuSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected Dialogs dialogs;
    @Autowired
    protected MetadataTools metadataTools;

    @ViewComponent
    protected GridMenuItem<Customer> customerItem;

    @Install(to = "contextMenu", subject = "dynamicContentHandler")
    public boolean contextMenuDynamicContentHandler(Customer customer) {
        if (customer == null) {
            return false;
        }

        customerItem.setText(metadataTools.getInstanceName(customer));
        return true;
    }

    @Subscribe("customersDataGrid.greeting")
    public void onCustomersDataGridGreetingActionPerformed(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();

        notifications.show(customer != null
                ? "Hello, " + metadataTools.getInstanceName(customer)
                : "No selection");
    }

    @Subscribe("customersDataGrid.getInfo")
    public void onCustomersDataGridGetInfoActionPerformed(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();

        dialogs.createMessageDialog()
                .withContent(
                        customer != null
                                ? getMessageDialogContent(customer)
                                : new Span("No selection")
                )
                .open();
    }

    protected Component getMessageDialogContent(Customer customer) {
        return new Html("""
                <div>
                    <strong>Name:</strong> %s<br/>
                    <strong>Age:</strong> %s<br/>
                    <strong>Email:</strong> %s
                </div>
                """.formatted(metadataTools.getInstanceName(customer), customer.getAge(), customer.getEmail()));
    }
}
