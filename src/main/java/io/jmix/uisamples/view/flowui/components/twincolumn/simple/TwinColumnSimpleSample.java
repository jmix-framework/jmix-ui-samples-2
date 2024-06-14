package io.jmix.uisamples.view.flowui.components.twincolumn.simple;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.twincolumn.TwinColumn;
import io.jmix.flowui.view.*;

@ViewController("twin-column-simple")
@ViewDescriptor("twin-column-simple.xml")
public class TwinColumnSimpleSample extends StandardView {

    @ViewComponent
    protected TwinColumn<String> twinColumn;

    @Subscribe
    public void onInit(InitEvent event) {
        twinColumn.setItems(
                "Whole Wheat Bread",
                "Chicken Breasts",
                "Brown Rice",
                "Milk",
                "Canned Black Beans",
                "Tomato Sauce",
                "Extra Virgin Olive Oil",
                "Bananas"
        );
    }

    @Subscribe("reorderableCheckbox")
    public void onReorderableCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        twinColumn.setReorderable(event.getValue());
    }

    @Subscribe("selectAllButtonsCheckbox")
    public void onSelectAllButtonsCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        twinColumn.setSelectAllButtonsVisible(event.getValue());
    }
}
