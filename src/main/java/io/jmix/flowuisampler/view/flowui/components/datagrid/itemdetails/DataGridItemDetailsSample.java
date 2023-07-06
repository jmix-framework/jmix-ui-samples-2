package io.jmix.flowuisampler.view.flowui.components.datagrid.itemdetails;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.bean.CustomerDetailsGenerator;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-item-details")
@ViewDescriptor("data-grid-item-details.xml")
public class DataGridItemDetailsSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected CustomerDetailsGenerator detailsGenerator;

    @Subscribe
    protected void onInit(InitEvent event) {
        detailsGenerator.setReadOnlyMode(true);
        customersDataGrid.setItemDetailsRenderer(createCustomerDetailsRenderer());
    }

    protected ComponentRenderer<FormLayout, Customer> createCustomerDetailsRenderer() {
        return new ComponentRenderer<>(detailsGenerator::createCustomerDetailsRenderer, detailsGenerator::setCustomer);
    }
}
