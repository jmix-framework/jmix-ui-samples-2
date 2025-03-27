package io.jmix.uisamples.view.flowui.cookbook.customservice1;

import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Contract;


@ViewController(id = "saving-entity-by-service")
@ViewDescriptor(path = "contract-list-view.xml")
public class ContractListView extends StandardListView<Contract> {
}