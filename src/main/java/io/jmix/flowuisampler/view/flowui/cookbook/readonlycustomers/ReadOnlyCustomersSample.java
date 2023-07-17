package io.jmix.flowuisampler.view.flowui.cookbook.readonlycustomers;

import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import io.jmix.flowuisampler.entity.ReadOnlyCustomer;
import io.jmix.flowuisampler.view.entity.customer.CustomerDetailView;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("read-only-customers")
@ViewDescriptor("read-only-customers.xml")
public class ReadOnlyCustomersSample extends StandardView {

    @ViewComponent
    protected CollectionLoader<ReadOnlyCustomer> readOnlyCustomersDl;
    @ViewComponent
    protected DataGrid<ReadOnlyCustomer> readOnlyCustomersDataGrid;

    @Autowired
    protected DialogWindows screenBuilders;
    @Autowired
    protected DataManager dataManager;

    @Subscribe("readOnlyCustomersDataGrid.create")
    protected void onReadOnlyCustomersDataGridCreate(ActionPerformedEvent event) {
        screenBuilders.detail(this, Customer.class)
                .newEntity()
                .withViewClass(CustomerDetailView.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.SAVE)) {
                        readOnlyCustomersDl.load();
                    }
                })
                .open();
    }

    @Subscribe("readOnlyCustomersDataGrid.edit")
    protected void onReadOnlyCustomersDataGridEdit(ActionPerformedEvent event) {
        ReadOnlyCustomer readOnlyCustomer = readOnlyCustomersDataGrid.getSingleSelectedItem();
        if (readOnlyCustomer == null) {
            return;
        }

        Customer customer = dataManager.load(Customer.class).id(readOnlyCustomer.getId()).one();

        screenBuilders.detail(this, Customer.class)
                .editEntity(customer)
                .withViewClass(CustomerDetailView.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.SAVE)) {
                        readOnlyCustomersDl.load();
                    }
                })
                .open();
    }

    @Subscribe("readOnlyCustomersDataGrid.remove")
    protected void onReadOnlyCustomersDataGridRemove(ActionPerformedEvent event) {
        ReadOnlyCustomer readOnlyCustomer = readOnlyCustomersDataGrid.getSingleSelectedItem();
        if (readOnlyCustomer == null) {
            return;
        }

        dataManager.remove(Id.of(readOnlyCustomer.getId(), Customer.class));
        readOnlyCustomersDl.load();
    }
}
