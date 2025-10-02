package io.jmix.uisamples.view.flowui.groupdatagrid.events;

import com.google.common.base.Joiner;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.groupgrid.GroupListDataComponent;
import io.jmix.flowui.view.*;
import io.jmix.groupgridflowui.component.event.GroupDataGridGroupItemClickEvent;
import io.jmix.groupgridflowui.component.event.GroupDataGridGroupingChangedEvent;
import io.jmix.groupgridflowui.kit.vaadin.grid.Grid;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("group-data-grid-events")
@ViewDescriptor(value = "group-data-grid-events.xml")
public class GroupDataGridEventsSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @Subscribe("customersGroupDataGrid")
    private void onGroupingChanged(GroupDataGridGroupingChangedEvent<Customer> event) {
        List<String> keys = event.getGroupingColumns().stream().map(Grid.Column::getKey).toList();

        notifications.create("Current grouping: %s".formatted(
                        keys.isEmpty() ? "no groups" : Joiner.on(", ").join(keys)))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onCollapse(GroupListDataComponent.CollapseEvent<Customer> event) {
        List<String> properties = event.getCollapsedGroups().stream().map(Object::toString).toList();

        notifications.create("Collapsed groups: " + Joiner.on(", ").join(properties))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onExpand(GroupListDataComponent.ExpandEvent<Customer> event) {
        List<String> properties = event.getExpandedGroups().stream().map(Object::toString).toList();

        notifications.create("Expanded groups: " + Joiner.on(", ").join(properties))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onGroupItemClick(GroupDataGridGroupItemClickEvent<Customer> event) {
        notifications.create("Clicked group: " + event.getGroupInfo())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
