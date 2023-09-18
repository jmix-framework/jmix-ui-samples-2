package io.jmix.uisamples.view.flowui.components.datagrid.partnamegenerator;

import io.jmix.core.Metadata;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-part-name-generator")
@ViewDescriptor("data-grid-part-name-generator.xml")
public class DataGridPartNameGeneratorSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected Metadata metadata;

    @Install(to = "customersDataGrid.grade", subject = "partNameGenerator")
    protected String gradePartNameGenerator(final Customer customer) {
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
