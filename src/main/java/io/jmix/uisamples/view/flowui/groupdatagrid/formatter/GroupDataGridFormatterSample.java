package io.jmix.uisamples.view.flowui.groupdatagrid.formatter;

import io.jmix.core.MetadataTools;
import io.jmix.flowui.component.groupgrid.GroupCellValueFormatter;
import io.jmix.flowui.component.groupgrid.GroupInfo;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("group-data-grid-formatter")
@ViewDescriptor(value = "group-data-grid-formatter.xml")
public class GroupDataGridFormatterSample extends StandardView {

    @ViewComponent
    private MessageBundle messageBundle;

    @Autowired
    private MetadataTools metadataTools;

    @Install(to = "customersGroupDataGrid.group", subject = "groupCellValueFormatter")
    public String onGroupColumnFormatter(GroupCellValueFormatter.GroupCellContext<Customer> context) {
        GroupInfo groupInfo = context.getGroupInfo();
        if (groupInfo.getProperty().is("active")) {
            return messageBundle.getMessage("group.formatter.active") + " "
                    + metadataTools.format(groupInfo.getValue());
        }
        return metadataTools.format(groupInfo.getValue());
    }
}
