package io.jmix.uisamples.view.flowui.cookbook.sidepanellayoutdetail;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.component.sidepanellayout.SidePanelLayout;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewValidation;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@FragmentDescriptor("customer-detail-fragment.xml")
public class CustomerDetailFragment extends Fragment<VerticalLayout> {

    @Autowired
    private ViewValidation viewValidation;
    @Autowired
    private DataManager dataManager;

    @ViewComponent
    private InstanceContainer<Customer> customerDc;

    private SidePanelLayout sidePanelLayout;

    public CustomerDetailFragment withSidePanelLayout(SidePanelLayout sidePanelLayout) {
        this.sidePanelLayout = sidePanelLayout;
        return this;
    }

    public CustomerDetailFragment withNewItem() {
        customerDc.setItem(dataManager.create(Customer.class));
        return this;
    }

    public CustomerDetailFragment withEditedItem(Customer customer) {
        customerDc.setItem(dataManager.load(Id.of(customer)).one());
        return this;
    }

    public CustomerDetailFragment withSaveListener(ComponentEventListener<SaveEvent> listener) {
        addListener(SaveEvent.class, listener);
        return this;
    }

    public void focusFirstField() {
        UiComponentUtils.getComponents(getContent()).stream()
                .filter(c -> c instanceof TextField)
                .findFirst()
                .ifPresent(c -> ((TextField) c).focus());
    }

    public ValidationErrors validate() {
        return viewValidation.validateUiComponents(getContent());
    }

    @Subscribe(id = "saveButton", subject = "clickListener")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        ValidationErrors errors = validate();
        if (errors.isEmpty()) {
            Customer saved = dataManager.save(customerDc.getItem());

            fireEvent(new SaveEvent(this, event.isFromClient(), saved));
            sidePanelLayout.closeSidePanel();
        } else {
            viewValidation.showValidationErrors(errors);
        }
    }

    @Subscribe(id = "cancelButton", subject = "clickListener")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        sidePanelLayout.closeSidePanel();
    }

    @Subscribe(id = "closeButton", subject = "clickListener")
    public void onCloseButtonClick(final ClickEvent<JmixButton> event) {
        sidePanelLayout.closeSidePanel();
    }

    public static class SaveEvent extends ComponentEvent<CustomerDetailFragment> {

        protected Customer item;

        public SaveEvent(CustomerDetailFragment source,
                         boolean fromClient,
                         Customer item) {
            super(source, fromClient);

            this.item = item;
        }

        public Customer getItem() {
            return item;
        }
    }
}
