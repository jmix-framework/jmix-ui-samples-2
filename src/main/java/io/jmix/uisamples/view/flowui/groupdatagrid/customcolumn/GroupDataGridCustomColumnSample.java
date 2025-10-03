package io.jmix.uisamples.view.flowui.groupdatagrid.customcolumn;

import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import io.jmix.flowui.component.groupgrid.data.GroupDataGridItems;
import io.jmix.flowui.view.*;
import io.jmix.groupgridflowui.component.GroupDataGrid;
import io.jmix.groupgridflowui.data.BaseGroupPropertyDescriptor;
import io.jmix.uisamples.entity.Customer;

import java.util.List;

@ViewController("group-data-grid-custom-column")
@ViewDescriptor(value = "group-data-grid-custom-column.xml")
public class GroupDataGridCustomColumnSample extends StandardView {

    @ViewComponent
    private GroupDataGrid<Customer> customersGroupDataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        GroupDataGridItems<Customer> items = customersGroupDataGrid.getItems();

        if (items != null) {
            items.addGroupPropertyDescriptor(
                    new BaseGroupPropertyDescriptor<Customer>("fullName",
                            context -> context.getItem().getName() + " " + context.getItem().getLastName())
                            .withSortProperties(List.of("name", "lastName")));

            customersGroupDataGrid.groupByKeys("fullName");
        }
    }

    @Supply(to = "customersGroupDataGrid.fullName", subject = "renderer")
    protected Renderer<Customer> supplyRendererToFullNameColumn() {
        return new TextRenderer<>(item -> item.getName() + " " + item.getLastName());
    }
}
