package io.jmix.uisamples.view.flowui.cookbook.copyaction;

import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.view.entity.customer.CustomerDetailView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("copy-action")
@ViewDescriptor("copy-action.xml")
public class CopyActionSample extends StandardView {

    @ViewComponent
    protected CollectionContainer<Customer> customersDc;
    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected MetadataTools metadataTools;
    @Autowired
    protected DataManager dataManager;
    @Autowired
    protected DialogWindows dialogWindows;

    @Subscribe("customersDataGrid.copy")
    protected void onCustomersDataGridCopy(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();

        Customer customerCopy = createCopy(customer);

        Customer savedCopy = dataManager.save(customerCopy);
        customersDc.getMutableItems().add(0, savedCopy);
    }

    @Subscribe("customersDataGrid.copyAndEdit")
    protected void onCustomersTableCopyAndEdit(ActionPerformedEvent event) {
        Customer customer = customersDataGrid.getSingleSelectedItem();

        Customer customerCopy = createCopy(customer);

        dialogWindows.detail(customersDataGrid)
                .withViewClass(CustomerDetailView.class)
                .newEntity(customerCopy)
                .open();
    }

    protected Customer createCopy(Customer customer) {
        Customer customerCopy = metadataTools.copy(customer);
        customerCopy.setId(UuidProvider.createUuid());
        customerCopy.setName(StringUtils.abbreviate("Copy of " + customer.getName(), 50));
        return customerCopy;
    }
}
