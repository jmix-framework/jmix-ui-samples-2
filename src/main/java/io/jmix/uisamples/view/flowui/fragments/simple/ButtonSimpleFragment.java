package io.jmix.uisamples.view.flowui.fragments.simple;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.details.JmixDetails;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragment.FragmentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;

@FragmentDescriptor("button-simple-fragment.xml")
public class ButtonSimpleFragment extends Fragment<JmixDetails> {

    @Autowired
    protected Notifications notifications;

    @Subscribe("helloButton")
    public void onHelloButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Hello, world!");
    }

    @Subscribe("saveButton1")
    public void onSaveButton1Click(ClickEvent<JmixButton> event) {
        FragmentUtils.getComponentId(event.getSource())
                .ifPresent(this::save);
    }

    @Subscribe("saveButton2")
    public void onSaveButton2Click(ClickEvent<JmixButton> event) {
        FragmentUtils.getComponentId(event.getSource())
                .ifPresent(this::save);
    }

    protected void save(String id) {
        notifications.show("Save called from " + id);
    }
}
