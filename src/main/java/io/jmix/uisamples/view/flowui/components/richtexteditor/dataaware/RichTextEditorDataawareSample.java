package io.jmix.uisamples.view.flowui.components.richtexteditor.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Span;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.richtexteditor.RichTextEditor;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("rich-text-editor-dataaware")
@ViewDescriptor("rich-text-editor-dataaware.xml")
public class RichTextEditorDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Order> orderDc;
    @ViewComponent
    protected Span spanValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(View.InitEvent event) {
        Order order = metadata.create(Order.class);
        order.setDescription("""
                <h2>Order description</h2>
                <p><strong>Customer:</strong></p>
                <p><strong>Full name:</strong> John Doe</p>
                <p><strong>E-mail:</strong> j.doe@jmix.io</p>
                <p><br></p>
                <p><strong>Comment:</strong> Deliver as quickly as possible.</p>
                """);
        orderDc.setItem(order);
    }

    @Subscribe("richTextEditor")
    protected void onRichTextEditorValueChange(ComponentValueChangeEvent<RichTextEditor, String> changeEvent) {
        spanValue.setText(orderDc.getItem().getDescription());
    }
}
