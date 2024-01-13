package io.jmix.uisamples.view.flowui.cookbook.delayeddataload;


import com.vaadin.flow.component.html.H4;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.KeyValueCollectionContainer;
import io.jmix.flowui.model.KeyValueCollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;

import java.util.Collections;
import java.util.Set;

@ViewController("delayed-data-load")
@ViewDescriptor("delayed-data-load.xml")
public class DelayedDataLoad extends StandardView {

    @ViewComponent
    private DataGrid<Order> ordersDataGrid;
    @ViewComponent
    private CollectionLoader<Order> ordersDl;
    @ViewComponent
    private KeyValueCollectionContainer orderItemsDc;
    @ViewComponent
    private KeyValueCollectionLoader orderItemsDl;
    @ViewComponent
    private H4 orderItemsHeader;
    @ViewComponent
    private Timer timer;

    @Subscribe
    public void onInit(final InitEvent event) {
        // Listen to selection changes
        ordersDataGrid.addSelectionListener(selectionEvent -> {
            refreshItems(selectionEvent.getAllSelectedItems());
        });
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        // Load Orders on view opening
        ordersDl.load();
    }

    private void refreshItems(Set<Order> selectedOrders) {
        // When the selection changes, clear the OrderItems data grid and stop the timer
        orderItemsDc.setItems(Collections.emptyList());
        timer.stop();
        // If the selection is not empty, start the timer again
        // and set the parameter for loading OrderItems
        if (!selectedOrders.isEmpty()) {
            timer.start();
            orderItemsDl.setParameter("orders", selectedOrders);
            orderItemsHeader.setText("Waiting...");
        } else {
            orderItemsHeader.setText("Order Items");
        }
    }

    @Subscribe("timer")
    public void onTimerTimerAction(final Timer.TimerActionEvent event) {
        // Load OrderItems when the timer goes off using the latest parameter value
        orderItemsDl.load();
        orderItemsHeader.setText("Order Items");
    }
}