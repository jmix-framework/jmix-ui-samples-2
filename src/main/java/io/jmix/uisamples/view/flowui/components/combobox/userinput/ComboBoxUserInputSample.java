package io.jmix.uisamples.view.flowui.components.combobox.userinput;

import com.google.common.collect.Lists;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("combobox-user-input")
@ViewDescriptor("combobox-user-input.xml")
public class ComboBoxUserInputSample extends StandardView {

    @ViewComponent
    protected JmixComboBox<String> comboBox;

    @Autowired
    protected Notifications notifications;

    protected List<String> items = Lists.newArrayList("One", "Two", "Tree");

    @Subscribe
    protected void onInit(InitEvent event) {
        comboBox.setItems(items);
    }

    @Subscribe("comboBox")
    protected void onComboBoxCustomValueSet(ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String customValue = event.getDetail();
        items.add(customValue);

        comboBox.setItems(items);
        comboBox.setValue(customValue);

        notifications.show(customValue + " added");
    }
}
