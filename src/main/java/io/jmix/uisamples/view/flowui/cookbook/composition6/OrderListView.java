package io.jmix.uisamples.view.flowui.cookbook.composition6;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;

@ViewController(id = "composition-order-uniqueness")
@ViewDescriptor(path = "order-list-view.xml")
@LookupComponent("ordersDataGrid")
@DialogMode(width = "64em")
public class OrderListView extends StandardListView<Order> {
}
