package io.jmix.uisamples.view.flowui.components.datagrid.varcolumns;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.JmixIntegerField;
import io.jmix.flowui.data.grid.ContainerDataGridItems;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.KeyValueCollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@ViewController("data-grid-var-columns")
@ViewDescriptor("data-grid-var-columns.xml")
public class DataGridVariableColumnsSample extends StandardView {

    @ViewComponent
    protected VerticalLayout box;
    @ViewComponent
    protected JmixIntegerField columnCountField;

    @Autowired
    protected DataComponents dataComponents;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected Notifications notifications;

    @Subscribe("createDataGridBtn")
    protected void onCreateDataGridBtnClick(ClickEvent<JmixButton> event) {
        box.removeAll();
        Integer columnCount = columnCountField.getValue();

        if (columnCount == null || columnCount < 1 || columnCount > 10) {
            notifications.create("Column count must be between 1 and 10")
                    .withType(Notifications.Type.WARNING)
                    .withCloseable(false)
                    .show();
            return;
        }

        KeyValueCollectionContainer container = createDataContainer(columnCount);
        DataGrid<KeyValueEntity> dataGrid = createDataGrid(columnCount, container);
        box.add(dataGrid);
    }

    protected DataGrid<KeyValueEntity> createDataGrid(Integer columnCount, KeyValueCollectionContainer container) {
        DataGrid<KeyValueEntity> dataGrid = uiComponents.create(DataGrid.class);
        dataGrid.setWidthFull();
        dataGrid.setMinHeight("12em");

        for (int col = 1; col <= columnCount; col++) {
            dataGrid.addColumn("prop" + col, container.getEntityMetaClass().getPropertyPath("prop" + col))
                    .setHeader("Prop" + col);
        }
        dataGrid.setItems(new ContainerDataGridItems(container));
        return dataGrid;
    }

    protected KeyValueCollectionContainer createDataContainer(Integer columnCount) {
        KeyValueCollectionContainer container = dataComponents.createKeyValueCollectionContainer();

        for (int col = 1; col <= columnCount; col++) {
            container.addProperty("prop" + col, String.class);
        }

        container.setItems(loadData(columnCount));
        return container;
    }

    protected Collection<KeyValueEntity> loadData(Integer columnCount) {
        Collection<KeyValueEntity> list = new ArrayList<>();

        for (int row = 0; row < 5; row++) {
            KeyValueEntity entity = new KeyValueEntity();

            for (int col = 1; col <= columnCount; col++) {
                entity.setValue("prop" + col, "value" + row + col);
            }
            list.add(entity);
        }

        return list;
    }
}
