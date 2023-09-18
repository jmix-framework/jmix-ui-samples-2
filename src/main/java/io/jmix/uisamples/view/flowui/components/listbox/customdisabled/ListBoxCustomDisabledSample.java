package io.jmix.uisamples.view.flowui.components.listbox.customdisabled;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.flowui.component.listbox.JmixListBox;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.record.Product;

import java.util.Collection;
import java.util.List;

@ViewController("list-box-custom-disabled")
@ViewDescriptor("list-box-custom-disabled.xml")
public class ListBoxCustomDisabledSample extends StandardView {

    @ViewComponent
    protected JmixListBox<Product> listBox;

    @Subscribe
    protected void onInit(InitEvent event) {
        initListBox();
    }

    protected void initListBox() {
        listBox.setItems(getListBoxItems());
        listBox.setRenderer(getRenderer());

        listBox.setItemEnabledProvider(product -> product.count() > 0);
    }

    protected ComponentRenderer<Text, Product> getRenderer() {
        return new ComponentRenderer<>(product -> new Text(product.getDisplayName()));
    }

    protected Collection<Product> getListBoxItems() {
        return List.of(
                new Product("TV set", 4),
                new Product("Keyboard", 13),
                new Product("Earphones", 0),
                new Product("Telephone", 23),
                new Product("Laptop", 0)
        );
    }
}
