package io.jmix.flowuisampler.view.flowui.components.combobox.userinput;

import com.google.common.collect.Lists;
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

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> items = Lists.newArrayList("One", "Two", "Tree");
        comboBox.setItems(items);

        comboBox.addCustomValueSetListener(valueChangeEvent -> {
            String customValue = valueChangeEvent.getDetail();
            items.add(customValue);

            comboBox.setItems(items);
            comboBox.setValue(customValue);

            notifications.show(customValue + " added");
        });
    }
}
