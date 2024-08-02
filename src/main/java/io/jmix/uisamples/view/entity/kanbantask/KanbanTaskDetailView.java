package io.jmix.uisamples.view.entity.kanbantask;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Input;
import io.jmix.flowui.component.valuepicker.JmixMultiValuePicker;
import io.jmix.flowui.kit.component.valuepicker.MultiValuePicker;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.KanbanTask;

import java.util.Collection;
import java.util.List;

@ViewController("KanbanTask.detail")
@ViewDescriptor("kanban-task-detail-view.xml")
@EditedEntityContainer("kanbanTaskDc")
public class KanbanTaskDetailView extends StandardDetailView<KanbanTask> {

    @ViewComponent
    private JmixMultiValuePicker<String> tagsField;
    @ViewComponent
    private Input colorField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (getEditedEntity().getColor() != null) {
            colorField.setValue(getEditedEntity().getColor());
        }

        if (getEditedEntity().getTags() != null) {
            tagsField.setValue(List.of(getEditedEntity().getTags().split(",")));
        }
        colorField.addValueChangeListener(this::onColorFieldComponentValueChange);
        tagsField.addValueChangeListener(this::onTagsFieldComponentValueChange);
    }

    private void onColorFieldComponentValueChange(final ComponentValueChangeEvent<Input, String> event) {
        getEditedEntity().setColor(event.getValue());
    }

    private void onTagsFieldComponentValueChange(
            final ComponentValueChangeEvent<MultiValuePicker<String>, Collection<String>> event) {
        getEditedEntity().setTags(String.join(",", event.getValue()));
    }
}
