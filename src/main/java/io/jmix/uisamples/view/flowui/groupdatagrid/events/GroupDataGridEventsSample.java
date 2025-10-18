package io.jmix.uisamples.view.flowui.groupdatagrid.events;

import com.google.common.base.Joiner;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.groupgrid.GroupListDataComponent;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.groupgridflowui.component.event.GroupItemClickEvent;
import io.jmix.groupgridflowui.component.event.GroupingChangedEvent;
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
    private void onGroupingChanged(GroupingChangedEvent<Customer> event) {
        List<String> keys = event.getGroupingColumns().stream().map(Grid.Column::getKey).toList();

        notifications.create("Current grouping: %s".formatted(
                        keys.isEmpty() ? "no groups" : Joiner.on(", ").join(keys)))
                .withPosition(Notification.Position.MIDDLE)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onCollapse(GroupListDataComponent.CollapseEvent<Customer> event) {
        List<String> properties = event.getCollapsedGroups().stream().map(Object::toString).toList();

        notifications.create("Collapsed groups: " + Joiner.on(", ").join(properties))
                .withPosition(Notification.Position.MIDDLE)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onExpand(GroupListDataComponent.ExpandEvent<Customer> event) {
        List<String> properties = event.getExpandedGroups().stream().map(Object::toString).toList();

        notifications.create("Expanded groups: " + Joiner.on(", ").join(properties))
                .withPosition(Notification.Position.MIDDLE)
                .show();
    }

    @Subscribe("customersGroupDataGrid")
    private void onGroupItemClick(GroupItemClickEvent<Customer> event) {
        notifications.create("Clicked group: " + event.getGroupInfo())
                .withPosition(Notification.Position.MIDDLE)
                .show();
    }
}
