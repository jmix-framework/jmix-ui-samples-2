package io.jmix.uisamples.view.flowui.pivottable.uiproperties;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("pivottable-ui-properties")
@ViewDescriptor("pivottable-ui-properties.xml")
public class PivotTableUiProperties extends StandardView {

    @ViewComponent
    private PivotTable<?> pivotTable;

    @Subscribe(id = "showRowTotalsCheckbox", subject = "clickListener")
    public void onShowRowTotalsCheckboxClick(final ClickEvent<JmixCheckbox> event) {
        pivotTable.setShowRowTotals(event.getSource().getValue());
    }

    @Subscribe(id = "showColumnTotalsCheckbox", subject = "clickListener")
    public void onShowColumnTotalsCheckboxClick(final ClickEvent<JmixCheckbox> event) {
        pivotTable.setShowColumnTotals(event.getSource().getValue());
    }

    @Subscribe(id = "showUiCheckbox", subject = "clickListener")
    public void onShowUiCheckboxClick(final ClickEvent<JmixCheckbox> event) {
        pivotTable.setShowUI(event.getSource().getValue());
    }

    @Subscribe(id = "disableCheckbox", subject = "clickListener")
    public void onDisableCheckboxClick(final ClickEvent<JmixCheckbox> event) {
        pivotTable.setEnabled(!event.getSource().getValue());
    }
}