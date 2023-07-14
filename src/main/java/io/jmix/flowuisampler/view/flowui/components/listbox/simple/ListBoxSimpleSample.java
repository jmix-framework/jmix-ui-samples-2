package io.jmix.flowuisampler.view.flowui.components.listbox.simple;

import com.vaadin.flow.component.html.Hr;
import io.jmix.flowui.component.listbox.JmixListBox;
import io.jmix.flowui.view.*;

import java.util.List;

@ViewController("list-box-simple")
@ViewDescriptor("list-box-simple.xml")
public class ListBoxSimpleSample extends StandardView {

    @ViewComponent
    protected JmixListBox<String> listBox;
    @ViewComponent
    protected JmixListBox<String> listBox2;

    @Subscribe
    protected void onInit(InitEvent event) {
        List<String> items = List.of("Jmix", "Vaadin", "SpringBoot", "EclipseLink");
        listBox.setItems(items);
        listBox2.setItems(items);

        listBox.setValue("Jmix");
        listBox2.setValue("Jmix");

        listBox2.addComponents("Vaadin", new Hr());
    }
}
