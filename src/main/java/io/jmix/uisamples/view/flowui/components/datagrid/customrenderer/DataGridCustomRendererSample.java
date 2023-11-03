package io.jmix.uisamples.view.flowui.components.datagrid.customrenderer;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import io.jmix.core.Messages;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Supply;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("data-grid-custom-renderer")
@ViewDescriptor("data-grid-custom-renderer.xml")
public class DataGridCustomRendererSample extends StandardView {

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected Messages messages;

    @Supply(to = "customersDataGrid.active", subject = "renderer")
    protected Renderer<Customer> activeComponentRenderer() {
        return new ComponentRenderer<>(
                () -> {
                    JmixCheckbox checkbox = uiComponents.create(JmixCheckbox.class);
                    checkbox.setReadOnly(true);
                    return checkbox;
                },
                (checkbox, customer) -> checkbox.setValue(customer.isActive())
        );
    }

    @Supply(to = "customersDataGrid.grade", subject = "renderer")
    protected Renderer<Customer> statusComponentRenderer() {
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
