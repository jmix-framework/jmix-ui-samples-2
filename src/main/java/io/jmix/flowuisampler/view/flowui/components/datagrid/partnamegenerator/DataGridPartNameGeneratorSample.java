package io.jmix.flowuisampler.view.flowui.components.datagrid.partnamegenerator;

import com.vaadin.flow.component.grid.Grid;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

@ViewController("data-grid-part-name-generator")
@ViewDescriptor("data-grid-part-name-generator.xml")
public class DataGridPartNameGeneratorSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Grid.Column<Customer> gradeColumn = customersDataGrid.getColumnByKey("grade");

        if (gradeColumn != null) {
            gradeColumn.setPartNameGenerator(this::gradePartNameGenerator);
        }
    }

    @Nullable
    protected String gradePartNameGenerator(Customer customer) {
        if (customer.getGrade() == null) {
            return null;
        }

        return switch (customer.getGrade()) {
            case STANDARD -> "standard-grade";
            case HIGH -> "high-grade";
            case PREMIUM -> "premium-grade";
        };
    }
}
