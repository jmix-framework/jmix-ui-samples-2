package io.jmix.uisamples.view.flowui.groupdatagrid.columnattributes;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.icon.VaadinIcon;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;
import io.jmix.groupgridflowui.component.GroupDataGrid;
import io.jmix.groupgridflowui.component.GroupDataGridColumn;
import io.jmix.uisamples.entity.Customer;

@ViewController("group-data-grid-column-attributes")
@ViewDescriptor(value = "group-data-grid-column-attributes.xml")
public class GroupDataGridColumnAttributesSample extends StandardView {

    @ViewComponent
    private JmixComboBox<VaadinIcon> groupIconsField;
    @ViewComponent
    private JmixCheckbox displayItemsCountField;
    @ViewComponent
    private JmixCheckbox displayColumnsGrouperOnIconClickField;
    @ViewComponent
    private JmixCheckbox groupIconVisibleField;

    @ViewComponent
    private GroupDataGrid<Customer> customersGroupDataGrid;

    private GroupDataGridColumn<Customer> groupColumn;

    @Subscribe
    public void onInit(InitEvent event) {
        groupColumn = (GroupDataGridColumn<Customer>) customersGroupDataGrid.getColumnByKey("group");

        groupIconsField.setItems(VaadinIcon.values());
        groupIconsField.setItemLabelGenerator(Enum::name);

        groupIconVisibleField.setValue(groupColumn.isGroupIconVisible());
        displayItemsCountField.setValue(groupColumn.isDisplayItemsCount());
        displayColumnsGrouperOnIconClickField.setValue(groupColumn.isDisplayColumnsGrouperOnIconClick());
    }

    @Subscribe("groupIconsField")
    public void onGroupIconsFieldValueChange(final ComponentValueChangeEvent<JmixComboBox<VaadinIcon>, VaadinIcon> event) {
        groupColumn.setGroupIcon(event.getValue() == null ? null : event.getValue().create());
    }

    @Subscribe("groupIconVisibleField")
    public void onGroupIconVisibleFieldValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        groupColumn.setGroupIconVisible(event.getValue());
    }

    @Subscribe("displayItemsCountField")
    public void onDisplayItemsCountFieldValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        groupColumn.setDisplayItemsCount(event.getValue());
        customersGroupDataGrid.getDataCommunicator().reset();
    }

    @Subscribe("displayColumnsGrouperOnIconClickField")
    public void onDisplayColumnsGrouperValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        groupColumn.setDisplayColumnsGrouperOnIconClick(event.getValue());
    }
}
