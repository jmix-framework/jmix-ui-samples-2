package io.jmix.uisamples.view.flowui.cookbook.sidepanellayoutdetail;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.sidepanellayout.SidePanelLayout;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.sidepanellayout.SidePanelAfterOpenEvent;
import io.jmix.flowui.kit.component.sidepanellayout.SidePanelCloseEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("side-panel-layout-detail")
@ViewDescriptor("side-panel-layout-detail.xml")
public class SidePanelLayoutDetailSample extends StandardView {

    @Autowired
    private Fragments fragments;

    @ViewComponent
    private VerticalLayout sidePanelContent;
    @ViewComponent
    private DataGrid<Customer> customersDataGrid;
    @ViewComponent
    private SidePanelLayout sidePanelLayout;

    @ViewComponent
    private CollectionContainer<Customer> customersDc;
    @ViewComponent
    private CollectionLoader<Customer> customersDl;

    @Subscribe("customersDataGrid.edit")
    public void onCustomersDataGridEdit(final ActionPerformedEvent event) {
        Customer item = customersDataGrid.getSingleSelectedItem();

        CustomerDetailFragment editorFragment = createEditorFragment()
                .withEditedItem(item)
                .withSaveListener(saveEvent -> customersDc.replaceItem(saveEvent.getItem()));

        sidePanelContent.add(editorFragment);
        sidePanelLayout.openSidePanel();
    }

    @Subscribe("customersDataGrid.create")
    public void onCustomersDataGridCreate(final ActionPerformedEvent event) {
        CustomerDetailFragment editorFragment = createEditorFragment()
                .withNewItem()
                .withSaveListener(saveEvent -> customersDl.load());

        sidePanelContent.add(editorFragment);
        sidePanelLayout.openSidePanel();
    }

    @Subscribe(id = "sidePanelLayout", subject = "addSidePanelAfterOpenListener")
    public void onSidePanelLayoutAfterOpened(SidePanelAfterOpenEvent event) {
        CustomerDetailFragment fragment = (CustomerDetailFragment) sidePanelContent.getComponentAt(0);
        fragment.focusFirstField();
    }

    @Subscribe(id = "sidePanelLayout", subject = "addSidePanelCloseListener")
    public void onSidePanelLayoutClosed(SidePanelCloseEvent event) {
        sidePanelContent.removeAll();
    }

    private CustomerDetailFragment createEditorFragment() {
        return fragments.create(this, CustomerDetailFragment.class)
                .withSidePanelLayout(sidePanelLayout);
    }
}
