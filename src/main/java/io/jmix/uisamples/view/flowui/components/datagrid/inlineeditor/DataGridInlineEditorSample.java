package io.jmix.uisamples.view.flowui.components.datagrid.inlineeditor;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.grid.editor.EditorCloseEvent;
import com.vaadin.flow.component.grid.editor.EditorSaveEvent;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@ViewController("data-grid-inline-editor")
@ViewDescriptor("data-grid-inline-editor.xml")
public class DataGridInlineEditorSample extends StandardView {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @ViewComponent
    private CollectionContainer<Customer> customersDc;
    @ViewComponent
    private CollectionLoader<Customer> customersDl;
    @ViewComponent
    private JmixCheckbox immediateCheckbox;
    @ViewComponent
    private JmixButton saveButton;

    private final Set<Customer> changedCustomers = new HashSet<>();

    @Install(to = "customersDataGridBuffered.@editor", subject = "saveListener")
    private void customersDataGridBufferedEditorSaveListener(final EditorSaveEvent<Customer> event) {
        saveOrEnqueue(event.getItem());
    }

    @Install(to = "customersDataGridNonBuffered.@editor", subject = "closeListener")
    private void customersDataGridNonBufferedEditorCloseListener(final EditorCloseEvent<Customer> event) {
        saveOrEnqueue(event.getItem());
    }

    @Subscribe("immediateCheckbox")
    public void onImmediateCheckboxComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        saveButton.setEnabled(!event.getValue());
    }

    @Subscribe(id = "saveButton", subject = "clickListener")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        saveEnqueuedChanges();
    }

    private void saveOrEnqueue(Customer customer) {

        if (immediateCheckbox.getValue()) {
            saveChanges(customer);
        } else {
            changedCustomers.add(customer);
        }
    }

    private void saveChanges(Customer customer) {
        // Save changed entity
        Customer savedCustomer = dataManager.save(customer);
        // Replace the original entity in the data container with the saved one
        customersDc.replaceItem(savedCustomer);
        // Note that if the original entity is merged into DataContext (this is the
        // case when <loader readOnly="false">), the saved instance should be merged too
        notifications.show("Changes saved to the database");
    }

    private void saveEnqueuedChanges() {
        if (!changedCustomers.isEmpty()) {
            // Save enqueued changes. The returned value is ignored because all data will be reloaded.
            dataManager.saveAll(changedCustomers);
            changedCustomers.clear();
            notifications.show("Changes saved to the database");
            customersDl.load();
        }
    }
}
