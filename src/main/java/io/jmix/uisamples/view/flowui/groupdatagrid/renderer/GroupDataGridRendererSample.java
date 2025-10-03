package io.jmix.uisamples.view.flowui.groupdatagrid.renderer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.Renderer;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.view.*;
import io.jmix.groupgridflowui.component.renderer.GroupDataGridColumnComponentRenderer;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("group-data-grid-renderer")
@ViewDescriptor(value = "group-data-grid-renderer.xml")
public class GroupDataGridRendererSample extends StandardView {

    @Autowired
    private MetadataTools metadataTools;

    @Supply(to = "customersGroupDataGrid.group", subject = "renderer")
    public Renderer<Customer> supplyRendererToGroupColumn() {
        return new GroupDataGridColumnComponentRenderer<>(groupInfo ->
                groupInfo.getProperty().is("grade")
                        ? createGradeLayout(groupInfo.getValue())
                        : new Span(metadataTools.format(groupInfo.getValue())));
    }

    protected Component createGradeLayout(CustomerGrade grade) {
        HorizontalLayout root = new HorizontalLayout();
        root.setPadding(false);
        root.add(new Span(metadataTools.format(grade)));

        Icon icon = VaadinIcon.DIAMOND.create();
        icon.getStyle().set("color", getGradeColor(grade));
        root.add(icon);
        return root;
    }

    private String getGradeColor(CustomerGrade grade) {
        return switch (grade) {
            case HIGH -> "#1F3A93";
            case PREMIUM -> "#ae0caf";
            default -> "#D7D7D7";
        };
    }
}
