package io.jmix.flowuisampler.view.entity.customer;

import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;

@ViewController("sampler_Customer.list")
@ViewDescriptor("customer-list-view.xml")
@LookupComponent("customersDataGrid")
@DialogMode(width = "50em")
public class CustomerListView extends StandardListView<Customer> {
}
