package io.jmix.uisamples.view.flowui.components.datagrid.customrenderer;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.core.MessageTools;
import io.jmix.core.Messages;
import io.jmix.core.Metadata;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-custom-renderer")
@ViewDescriptor("data-grid-custom-renderer.xml")
public class DataGridCustomRendererSample extends StandardView {

    @ViewComponent
    protected DataGrid<Customer> customersDataGrid;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected Messages messages;
    @Autowired
    protected MessageTools messageTools;
    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        customersDataGrid.addColumn(createStatusComponentRenderer())
                .setHeader(messageTools.getPropertyCaption(metadata.getClass(Customer.class), "grade"));
    }

    protected ComponentRenderer<Span, Customer> createStatusComponentRenderer() {
        return new ComponentRenderer<>(this::createGradeComponent, this::gradeComponentUpdater);
    }

    protected Span createGradeComponent() {
        Span span = uiComponents.create(Span.class);
        span.getElement().getThemeList().add("badge");

        return span;
    }

    protected void gradeComponentUpdater(Span span, Customer customer) {
        if (customer.getGrade() != null) {
            span.setText(messages.getMessage(CustomerGrade.class, customer.getGrade().name()));

            switch (customer.getGrade()) {
                case STANDARD -> span.getElement().getThemeList().add("contrast");
                case HIGH -> span.getElement().getThemeList().add("success");
                case PREMIUM -> span.getElement().getThemeList().add("primary");
            }
        } else {
            span.setText("No data");
        }
    }
}
