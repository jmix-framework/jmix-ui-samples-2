package io.jmix.uisamples.view.flowui.fragments.facets.dataloadcoordinator;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.uisamples.entity.Order;

@FragmentDescriptor("data-load-coordinator-fragment.xml")
public class DataLoadCoordinatorFragment extends Fragment<VerticalLayout> {

    @ViewComponent
    private CollectionContainer<Order> ordersDc;

    @ViewComponent
    private Span onFragmentReadyItemCount;
    @ViewComponent
    private Span onHostInitItemCount;
    @ViewComponent
    private Span onHostBeforeShowItemCount;
    @ViewComponent
    private Span onHostReadyItemCount;

    @Subscribe
    public void onReady(ReadyEvent event) {
        onFragmentReadyItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostInit(View.InitEvent event) {
        onHostInitItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(View.BeforeShowEvent event) {
        onHostBeforeShowItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostReady(View.ReadyEvent event) {
        onHostReadyItemCount.setText(String.valueOf(ordersDc.getItems().size()));
    }
}
