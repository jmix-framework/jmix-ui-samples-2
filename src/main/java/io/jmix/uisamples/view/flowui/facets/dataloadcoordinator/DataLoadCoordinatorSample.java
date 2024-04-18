package io.jmix.uisamples.view.flowui.facets.dataloadcoordinator;

import com.vaadin.flow.component.html.Span;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;

@ViewController("data-load-coordinator")
@ViewDescriptor("data-load-coordinator.xml")
public class DataLoadCoordinatorSample extends StandardView {

    @ViewComponent
    protected CollectionContainer<Order> ordersDc;

    @ViewComponent
    protected Span onInitItemCount;
    @ViewComponent
    protected Span onBeforeShowItemCount;
    @ViewComponent
    protected Span onReadyItemCount;

    @Subscribe
    public void onInit(InitEvent event) {
        onInitItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        onBeforeShowItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }

    @Subscribe
    public void onReady(ReadyEvent event) {
        onReadyItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }
}
