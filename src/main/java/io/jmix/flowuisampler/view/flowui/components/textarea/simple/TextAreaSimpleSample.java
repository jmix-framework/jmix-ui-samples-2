package io.jmix.flowuisampler.view.flowui.components.textarea.simple;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@ViewController("text-area-simple")
@ViewDescriptor("text-area-simple.xml")
public class TextAreaSimpleSample extends StandardView {

    @Subscribe("textArea")
    protected void onTextAreaValueChange(ComponentValueChangeEvent<JmixTextArea, String> event) {
        event.getSource().setHelperText(event.getValue().length() + "/" + 140);
    }
}
