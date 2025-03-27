package io.jmix.uisamples.view.flowui.cookbook.customservice1;

import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Contract;


@ViewController(id = "saving-entity-by-service")
@ViewDescriptor(path = "contract-list-view.xml")
public class ContractListView extends StandardListView<Contract> {

    @ViewComponent
    private CollectionLoader<Contract> contractsDl;

    @Install(to = "contractsDataGrid.createAction", subject = "afterCloseHandler")
    private void contractsDataGridCreateActionAfterCloseHandler(final DialogWindow.AfterCloseEvent<ContractListView> afterCloseEvent) {
        contractsDl.load();
    }

    @Install(to = "contractsDataGrid.editAction", subject = "afterCloseHandler")
    private void contractsDataGridEditActionAfterCloseHandler(final DialogWindow.AfterCloseEvent<ContractListView> afterCloseEvent) {
        contractsDl.load();
    }
}