package io.jmix.flowuisampler.view.flowui.components.codeeditor.simple;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.view.*;

@ViewController("code-editor-simple")
@ViewDescriptor("code-editor-simple.xml")
public class CodeEditorSimpleSample extends StandardView {

    @ViewComponent
    protected CodeEditor codeEditor;

    @Subscribe("highlightCheckbox")
    protected void onHighlightCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setHighlightActiveLine(event.getValue());
    }

    @Subscribe("gutterCheckbox")
    protected void onGutterCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setShowGutter(event.getValue());
    }

    @Subscribe("lineNumbersCheckbox")
    protected void onLineNumbersCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setShowLineNumbers(event.getValue());
    }

    @Subscribe("printMarginCheckbox")
    protected void onPrintMarginCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setShowPrintMargin(event.getValue());
    }
}
