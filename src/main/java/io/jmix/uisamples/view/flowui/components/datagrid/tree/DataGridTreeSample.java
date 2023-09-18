package io.jmix.uisamples.view.flowui.components.datagrid.tree;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.treegrid.CollapseEvent;
import com.vaadin.flow.component.treegrid.ExpandEvent;
import io.jmix.core.MessageTools;
import io.jmix.core.MetadataTools;
import io.jmix.core.metamodel.model.MetaPropertyPath;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ViewController("data-grid-tree")
@ViewDescriptor("data-grid-tree.xml")
public class DataGridTreeSample extends StandardView {

    @ViewComponent
    protected JmixSelect<String> columnSelect;
    @ViewComponent
    protected TreeDataGrid<Task> taskDataGrid;

    @Autowired
    protected MessageTools messageTools;
    @Autowired
    protected MetadataTools metadataTools;
    @Autowired
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        initColumnSelect();
    }

    @Subscribe("taskDataGrid")
    protected void onCollapse(CollapseEvent<Task, TreeDataGrid<Task>> event) {
        notifications.create("Collapsed Items: " + mapItemsToString(event.getItems()))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("taskDataGrid")
    protected void onExpand(ExpandEvent<Task, TreeDataGrid<Task>> event) {
        notifications.create("Expanded Items: " + mapItemsToString(event.getItems()))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    protected void initColumnSelect() {
        ComponentUtils.setItemsMap(columnSelect, getColumnSelectItemsMap());
        columnSelect.setValue(taskDataGrid.getColumnByKey("name").getKey());
        columnSelect.addValueChangeListener(this::onColumnSelectValueChange);
    }

    protected void onColumnSelectValueChange(ComponentValueChangeEvent<Select<String>, String> event) {
        Grid.Column<Task> prevColumn = taskDataGrid.getColumnByKey(event.getOldValue());
        Grid.Column<Task> newColumn = taskDataGrid.getColumnByKey(event.getValue());

        int prevColumnIdx = taskDataGrid.getColumns().indexOf(prevColumn);
        int newColumnIdx = taskDataGrid.getColumns().indexOf(newColumn);

        MetaPropertyPath prevColumnMpp = taskDataGrid.getColumnMetaPropertyPath(prevColumn);
        MetaPropertyPath newColumnMpp = taskDataGrid.getColumnMetaPropertyPath(newColumn);

        taskDataGrid.removeColumn(prevColumn);
        taskDataGrid.setColumnPosition(taskDataGrid.addColumn(prevColumnMpp), prevColumnIdx);

        taskDataGrid.removeColumn(newColumn);
        taskDataGrid.setColumnPosition(taskDataGrid.addHierarchyColumn(newColumnMpp), newColumnIdx);
    }

    protected Map<String, String> getColumnSelectItemsMap() {
        List<Grid.Column<Task>> columns = taskDataGrid.getColumns();

        return columns.stream()
                .collect(Collectors.toMap(
                        Grid.Column::getKey,
                        this::columnMapper,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    protected String columnMapper(Grid.Column<Task> column) {
        MetaPropertyPath propertyPath = taskDataGrid.getColumnMetaPropertyPath(column);

        return propertyPath != null
                ? messageTools.getPropertyCaption(propertyPath.getMetaProperty())
                : column.getKey();
    }

    protected String mapItemsToString(Collection<Task> items) {
        return items.stream()
                .map(metadataTools::getInstanceName)
                .collect(Collectors.joining(", "));
    }
}
