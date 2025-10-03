package io.jmix.uisamples.view.flowui.groupdatagrid.styling;

import io.jmix.flowui.component.groupgrid.GroupInfo;
import io.jmix.flowui.component.groupgrid.GroupProperty;
import io.jmix.flowui.view.*;
import io.jmix.groupgridflowui.component.DataGridColumn;
import io.jmix.groupgridflowui.component.GroupDataGrid;
import io.jmix.uisamples.entity.Customer;

@ViewController("group-data-grid-styling")
@ViewDescriptor(value = "group-data-grid-styling.xml")
public class GroupDataGridStylingSample extends StandardView {

    @ViewComponent
    private GroupDataGrid<Customer> customersGroupDataGrid;

    @Subscribe
    private void onInit(InitEvent event) {
        DataGridColumn<Customer> group = customersGroupDataGrid.getColumnByKey("group");
        if (group != null) {
            group.setPartNameGenerator(customer ->
                    customer.getGrade() == null ? "" : "group-column-cell " + customer.getGrade().toString().toLowerCase());
        }
    }

    @Install(to = "customersGroupDataGrid", subject = "groupPartNameGenerator")
    private String onGroupPartNameGenerator(GroupInfo groupInfo) {
        GroupProperty property = groupInfo.getProperty();
        if (property.is("grade")) {
            return "group-grade" + " " + groupInfo.getValue().toString().toLowerCase();
        }
        return "";
    }
}
