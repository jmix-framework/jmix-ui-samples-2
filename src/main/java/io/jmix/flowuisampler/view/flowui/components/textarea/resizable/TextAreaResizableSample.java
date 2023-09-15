package io.jmix.flowuisampler.view.flowui.components.textarea.resizable;

import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.view.*;

@ViewController("text-area-resizable")
@ViewDescriptor("text-area-resizable.xml")
public class TextAreaResizableSample extends StandardView {

    @ViewComponent
    protected JmixTextArea resizableBothTextArea;
    @ViewComponent
    protected JmixTextArea resizableVerticalTextArea;
    @ViewComponent
    protected JmixTextArea resizableHorizontalTextArea;

    @Subscribe
    protected void onInit(InitEvent event) {
        resizableBothTextArea.getStyle().set("resize", "both");
        resizableBothTextArea.getStyle().set("overflow", "auto");

        resizableVerticalTextArea.getStyle().set("resize", "vertical");
        resizableVerticalTextArea.getStyle().set("overflow", "auto");

        resizableHorizontalTextArea.getStyle().set("resize", "horizontal");
        resizableHorizontalTextArea.getStyle().set("overflow", "auto");
    }
}
