package io.jmix.uisamples.view.flowui.cookbook.composition7;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;


@ViewController(id = "composition-info-panel")
@ViewDescriptor(path = "order-list-view.xml")
@LookupComponent("ordersDataGrid")
@DialogMode(width = "64em")
public class OrderListView extends StandardListView<Order> {
}