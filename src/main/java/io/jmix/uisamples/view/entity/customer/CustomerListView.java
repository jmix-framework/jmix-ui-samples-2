package io.jmix.uisamples.view.entity.customer;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

@ViewController("Customer.list")
@ViewDescriptor("customer-list-view.xml")
@LookupComponent("customersDataGrid")
@DialogMode(width = "50em")
public class CustomerListView extends StandardListView<Customer> {
}
