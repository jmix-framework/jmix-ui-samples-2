package io.jmix.flowuisampler.view.flowui.components.combobox.customfiltering;

import com.vaadin.flow.component.combobox.ComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;

import java.util.List;

@ViewController("combobox-custom-filtering")
@ViewDescriptor("combobox-custom-filtering.xml")
public class ComboBoxCustomFilteringSample extends StandardView {

    @ViewComponent
    protected JmixComboBox<String> noFilterComboBox;
    @ViewComponent
    protected JmixComboBox<String> startsWithFilterComboBox;
    @ViewComponent
    protected JmixComboBox<String> containsFilterComboBox;

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> itemsList = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        noFilterComboBox.setItems(itemsList);
        startsWithFilterComboBox.setItems(getStartsWithFilter(), itemsList);
        containsFilterComboBox.setItems(getContainsFilter(), itemsList);
    }

    protected ComboBox.ItemFilter<String> getStartsWithFilter() {
        return (dayOfWeek, filterString) -> dayOfWeek.toLowerCase().startsWith(filterString.toLowerCase());
    }

    protected ComboBox.ItemFilter<String> getContainsFilter() {
        return (dayOfWeek, filterString) -> dayOfWeek.toLowerCase().contains(filterString.toLowerCase());
    }
}
