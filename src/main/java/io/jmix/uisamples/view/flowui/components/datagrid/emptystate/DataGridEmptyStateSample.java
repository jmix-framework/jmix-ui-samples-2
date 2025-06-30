package io.jmix.uisamples.view.flowui.components.datagrid.emptystate;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.action.Action;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

@ViewController("data-grid-empty-state")
@ViewDescriptor("data-grid-empty-state.xml")
public class DataGridEmptyStateSample extends StandardView {

    @ViewComponent
    private CollectionContainer<Customer> customersDc;
    @ViewComponent
    private CollectionLoader<Customer> customersDl;

    @ViewComponent("customersDataGrid.unload")
    private Action unloadAction;

    @Subscribe("customersDataGrid.unload")
    public void onCustomersDataGridUnloadActionPerformed(ActionPerformedEvent event) {
        customersDc.getMutableItems().clear();
        unloadAction.setEnabled(false);
    }

    @Subscribe("loadBtn")
    public void onLoadBtnClick(ClickEvent<JmixButton> event) {
        customersDl.load();
        unloadAction.setEnabled(true);
    }
}
