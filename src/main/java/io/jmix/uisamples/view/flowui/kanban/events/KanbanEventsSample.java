package io.jmix.uisamples.view.flowui.kanban.events;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.kanbanflowui.component.Kanban;
import io.jmix.kanbanflowui.kit.component.event.*;
import io.jmix.uisamples.entity.KanbanTask;
import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@ViewController("kanban-events")
@ViewDescriptor("kanban-events.xml")
public class KanbanEventsSample extends StandardView {

    @SuppressWarnings("rawtypes")
    @ViewComponent
    private JmixCheckboxGroup<Class<? extends ComponentEvent>> eventsCheckboxGroup;
    @ViewComponent
    private Kanban<KanbanTask> kanban;

    @Autowired
    private Notifications notifications;

    @SuppressWarnings("rawtypes")
    private final Set<Class<? extends ComponentEvent>> firedEvents = new HashSet<>();

    @Subscribe
    public void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(eventsCheckboxGroup,
                Maps.of(
                        KanbanColumnClickEvent.class, "Column click",
                        KanbanColumnDoubleClickEvent.class, "Column double-click",
                        KanbanTaskClickEvent.class, "Task click",
                        KanbanTaskDoubleClickEvent.class, "Task double-click",
                        KanbanColumnCollapseEvent.class, "Column collapse",
                        KanbanColumnExpandEvent.class, "Column expand",
                        KanbanColumnReorderEvent.class, "Column reordered",
                        KanbanColumnShowEvent.class, "Column show",
                        KanbanColumnHideEvent.class, "Column hide",
                        KanbanDragStartEvent.class, "Drag start",
                        KanbanDragEndEvent.class, "Drag end"
                )
        );
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnClick(final KanbanColumnClickEvent event) {
        showNotification("Column clicked: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnDoubleClick(final KanbanColumnDoubleClickEvent event) {
        showNotification("Column double-clicked: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanTaskClick(final KanbanTaskClickEvent<KanbanTask> event) {
        showNotification("Task clicked: " + event.getItem().getName());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanTaskDoubleClick(final KanbanTaskDoubleClickEvent<KanbanTask> event) {
        showNotification("Task double-clicked: " + event.getItem().getName());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnCollapse(final KanbanColumnCollapseEvent event) {
        showNotification("Column collapsed: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnExpand(final KanbanColumnExpandEvent event) {
        showNotification("Column expanded: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnReorder(final KanbanColumnReorderEvent event) {
        showNotification("Column reordered: " + event.getColumn().getLabel() + ". New index: " + event.getNewIndex());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnShow(final KanbanColumnShowEvent event) {
        showNotification("Column showed: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanColumnHide(final KanbanColumnHideEvent event) {
        showNotification("Column hidden: " + event.getColumn().getLabel());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanDragEnd(final KanbanDragEndEvent<KanbanTask> event) {
        showNotification("Task dropped: " + event.getItem().getName());
        addExecuted(event.getClass());
    }

    @Subscribe("kanban")
    public void onKanbanKanbanDragStart(final KanbanDragStartEvent<KanbanTask> event) {
        showNotification("Task dragged: " + event.getItem().getName());
        addExecuted(event.getClass());
    }

    @Subscribe("showAllBtn")
    public void onShowAllBtnClick(final ClickEvent<JmixButton> event) {
        kanban.showAllColumns();
    }

    @Subscribe("resetExecutedBtn")
    public void onResetExecutedBtnClick(final ClickEvent<JmixButton> event) {
        firedEvents.clear();
        eventsCheckboxGroup.clear();
    }

    private void showNotification(String message) {
        notifications.create(message)
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @SuppressWarnings("rawtypes")
    private void addExecuted(Class<? extends ComponentEvent> eventClass) {
        firedEvents.add(eventClass);
        eventsCheckboxGroup.setValue(firedEvents);
    }
}
