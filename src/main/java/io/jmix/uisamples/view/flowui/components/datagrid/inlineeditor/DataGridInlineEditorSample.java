package io.jmix.uisamples.view.flowui.components.datagrid.inlineeditor;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-inline-editor")
@ViewDescriptor("data-grid-inline-editor.xml")
public class DataGridInlineEditorSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Notifications notifications;
    @Autowired
    protected DataManager dataManager;

    @Subscribe("editorBufferedCheckbox")
    protected void onEditorBufferedCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (!event.isFromClient()) {
            return;
        }

        if (customersDataGrid.getEditor().isOpen()) {
            notifications.create("Please close Editor before changing its mode")
                    .withType(Notifications.Type.WARNING)
                    .withCloseable(false)
                    .show();

            event.getSource().setValue(event.getOldValue());
        } else {
            customersDataGrid.getEditor().setBuffered(event.getValue());

            customersDataGrid.getColumnByKey("bufferedEditorColumn").setVisible(event.getValue());
            customersDataGrid.getColumnByKey("nonBufferedEditorColumn").setVisible(!event.getValue());
        }
    }
}
