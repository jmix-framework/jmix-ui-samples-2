package io.jmix.uisamples.view.flowui.kanban.columnactions;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.kanbanflowui.component.Kanban;
import io.jmix.uisamples.entity.KanbanTask;

@ViewController("kanban-column-actions")
@ViewDescriptor("kanban-column-actions.xml")
public class KanbanColumnActionsSample extends StandardView {

    @ViewComponent
    private Kanban<KanbanTask> kanban;

    @Subscribe("collapseAllBtn")
    public void onCollapseAllBtnClick(final ClickEvent<JmixButton> event) {
        kanban.getColumns().forEach(kanbanColumn -> {
            if (!kanbanColumn.isCollapsed() && kanbanColumn.isCollapsible()) {
                kanbanColumn.setCollapsed(true);
            }
        });
    }

    @Subscribe("expandAllBtn")
    public void onExpandAllBtnClick(final ClickEvent<JmixButton> event) {
        kanban.expandAllColumns();
    }

    @Subscribe("hideAllBtn")
    public void onHideAllBtnClick(final ClickEvent<JmixButton> event) {
        kanban.getColumns().forEach(kanbanColumn -> {
            if (kanbanColumn.isVisible()) {
                kanbanColumn.setVisible(false);
            }
        });
    }

    @Subscribe("showAllBtn")
    public void onShowAllBtnClick(final ClickEvent<JmixButton> event) {
        kanban.showAllColumns();
    }
}
