package io.jmix.uisamples.view.flowui.components.datagrid.editorevents;

import com.vaadin.flow.component.grid.editor.EditorCancelEvent;
import com.vaadin.flow.component.grid.editor.EditorCloseEvent;
import com.vaadin.flow.component.grid.editor.EditorOpenEvent;
import com.vaadin.flow.component.grid.editor.EditorSaveEvent;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.Install;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-editor-events")
@ViewDescriptor("data-grid-editor-events.xml")
public class DataGridEditorEventsSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Install(to = "customersDataGrid.@editor", subject = "openListener")
    protected void onCustomersDataGridEditorOpened(EditorOpenEvent<Customer> event) {
        showNotification("Editor opened");
    }

    @Install(to = "customersDataGrid.@editor", subject = "cancelListener")
    protected void onCustomersDataGridEditorCanceled(EditorCancelEvent<Customer> event) {
        showNotification("Edit canceled");
    }

    @Install(to = "customersDataGrid.@editor", subject = "closeListener")
    protected void onCustomersDataGridEditorClosed(EditorCloseEvent<Customer> event) {
        showNotification("Editor closed");
    }

    @Install(to = "customersDataGrid.@editor", subject = "saveListener")
    protected void onCustomersDataGridEditorSaved(EditorSaveEvent<Customer> event) {
        showNotification("Changes saved");
    }

    protected void showNotification(String message) {
        notifications.create(message)
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
