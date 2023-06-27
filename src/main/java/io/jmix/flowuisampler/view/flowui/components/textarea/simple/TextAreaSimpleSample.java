package io.jmix.flowuisampler.view.flowui.components.textarea.simple;

import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.view.*;

@ViewController("text-area-simple")
@ViewDescriptor("text-area-simple.xml")
public class TextAreaSimpleSample extends StandardView {

    @ViewComponent
    protected JmixTextArea textArea;

    @Subscribe
    protected void onInit(InitEvent event) {
        int charLimit = 140;

        textArea.setMaxLength(charLimit);
        textArea.addValueChangeListener(e -> e.getSource().setHelperText(e.getValue().length() + "/" + charLimit));
        textArea.setValue("Great job. This is excellent!");
    }
}
