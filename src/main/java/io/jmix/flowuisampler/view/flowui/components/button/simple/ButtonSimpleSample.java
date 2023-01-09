package io.jmix.flowuisampler.view.flowui.components.button.simple;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("button-simple")
@ViewDescriptor("button-simple.xml")
public class ButtonSimpleSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("helloButton")
    protected void onHelloButtonClick(ClickEvent<Button> event) {
        notifications.create("Hello, world!")
                .show();
    }

    @Subscribe("saveButton1")
    protected void onSaveButton1Click(ClickEvent<Button> event) {
        event.getSource().getId()
                .ifPresent(this::save);
    }

    @Subscribe("saveButton2")
    protected void onSaveButton2Click(ClickEvent<Button> event) {
        event.getSource().getId()
                .ifPresent(this::save);
    }

    public void save(String id) {
        notifications.create("Save called from " + id)
                .show();
    }
}
