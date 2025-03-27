package io.jmix.uisamples.view.flowui.cookbook.customservice1;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.Copier;
import io.jmix.core.SaveContext;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Contract;
import io.jmix.uisamples.entity.ContractStatus;
import io.jmix.uisamples.view.sys.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Route(value = "contracts/:id", layout = MainView.class)
@ViewController(id = "Contract.detail")
@ViewDescriptor(path = "contract-detail-view.xml")
@EditedEntityContainer("contractDc")
public class ContractDetailView extends StandardDetailView<Contract> {

    @Autowired
    private Copier copier;
    @Autowired
    private ContractService contractService;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private JmixButton approveButton;
    @ViewComponent
    private JmixButton reviseButton;
    @ViewComponent
    private JmixButton rejectButton;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "approveButton", subject = "clickListener")
    public void onApproveButtonClick(final ClickEvent<JmixButton> event) {
        changeContractStatus(ContractStatus.APPROVED);
    }

    @Subscribe(id = "reviseButton", subject = "clickListener")
    public void onReviseButtonClick(final ClickEvent<JmixButton> event) {
        changeContractStatus(ContractStatus.DRAFT);
    }

    @Subscribe(id = "rejectButton", subject = "clickListener")
    public void onRejectButtonClick(final ClickEvent<JmixButton> event) {
        changeContractStatus(ContractStatus.REJECTED);
    }

    private void changeContractStatus(ContractStatus status) {
        // Make a copy
        Contract contractCopy = copier.copy(getEditedEntity());

        // Pass the copy to the service
        Contract changedContract = contractService.changeStatus(contractCopy, status);

        // Merge the saved entity to DataContext
        var ignored = dataContext.merge(changedContract);

        notifications.create("Status changed").withPosition(Notification.Position.TOP_END).show();
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        // Make a copy
        Contract contractCopy = copier.copy(getEditedEntity());

        // Pass the copy to the service
        Contract savedContract = contractService.saveContract(contractCopy);

        notifications.create("Changes saved").withPosition(Notification.Position.TOP_END).show();

        // Return the saved entity. It will be merged to DataContext automatically.
        return Set.of(savedContract);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        setShowSaveNotification(false);
        updateButtons(getEditedEntity().getStatus());
    }

    @Subscribe(id = "contractDc", target = Target.DATA_CONTAINER)
    public void onContractDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<Contract> event) {
        if (event.getProperty().equals("status")) {
            updateButtons((ContractStatus) event.getValue());
        }
    }

    private void updateButtons(ContractStatus status) {
        approveButton.setEnabled(status != ContractStatus.APPROVED);
        reviseButton.setEnabled(status != ContractStatus.DRAFT);
        rejectButton.setEnabled(status != ContractStatus.REJECTED);
    }
}