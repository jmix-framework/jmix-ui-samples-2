package io.jmix.uisamples.view.flowui.kanban.hierarchicalcolumns;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;
import io.jmix.kanbanflowui.component.Kanban;
import io.jmix.kanbanflowui.kit.component.JmixKanban;
import io.jmix.uisamples.entity.KanbanTask;

@ViewController("kanban-hierarchical-columns")
@ViewDescriptor("kanban-hierarchical-columns.xml")
public class KanbanHierarchicalColumnsSample extends StandardView {

    @ViewComponent
    private Kanban<KanbanTask> kanban;

    @Subscribe("columnModeCheckbox")
    public void onColumnModeCheckboxValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        kanban.setHierarchyMode(event.getValue()
                ? JmixKanban.HierarchyMode.TABS
                : JmixKanban.HierarchyMode.COLUMNS);
    }
}
