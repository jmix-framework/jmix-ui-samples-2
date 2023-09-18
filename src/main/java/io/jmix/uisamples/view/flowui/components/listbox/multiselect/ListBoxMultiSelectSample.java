package io.jmix.uisamples.view.flowui.components.listbox.multiselect;

import io.jmix.flowui.component.listbox.JmixListBox;
import io.jmix.flowui.component.listbox.JmixMultiSelectListBox;
import io.jmix.flowui.view.*;

import java.util.List;

@ViewController("list-box-multi-select")
@ViewDescriptor("list-box-multi-select.xml")
public class ListBoxMultiSelectSample extends StandardView {

    @ViewComponent
    protected JmixListBox<String> listBox;
    @ViewComponent
    protected JmixMultiSelectListBox<String> multiSelectListBox;

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> items = List.of("CSS", "HTML", "Java", "JavaScript", "JSON", "Kotlin", "XML");
        listBox.setItems(items);
        multiSelectListBox.setItems(items);

        listBox.setValue("Java");
        multiSelectListBox.select("Java", "Kotlin");
    }
}
