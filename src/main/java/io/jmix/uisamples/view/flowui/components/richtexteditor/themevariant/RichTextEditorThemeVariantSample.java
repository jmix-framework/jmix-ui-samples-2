package io.jmix.uisamples.view.flowui.components.richtexteditor.themevariant;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.richtexteditor.RichTextEditor;
// theme-only:lumo
import io.jmix.flowui.kit.component.richtexteditor.RichTextEditorVariant;
// theme-only:lumo:end
import io.jmix.flowui.view.*;

@ViewController("rich-text-editor-theme-variant")
@ViewDescriptor("rich-text-editor-theme-variant.xml")
public class RichTextEditorThemeVariantSample extends StandardView {

    // tag::theme-variant-field[] sample-hide
    @ViewComponent
    protected RichTextEditor richTextEditor;
    // end::theme-variant-field[] sample-hide

    @Subscribe
    protected void onInit(InitEvent event) {
        richTextEditor.setValue("<p><strong>Bold</strong> <em>Italic</em> <u>Underline</u> <s>Strikethrough</s></p>");
    }
    // theme-only:lumo
    // tag::theme-variant-handlers[] sample-hide
    @Subscribe("noBorderCheckbox")
    protected void onHighlightCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (event.getValue()) {
            richTextEditor.addThemeVariants(RichTextEditorVariant.LUMO_NO_BORDER);
        } else {
            richTextEditor.removeThemeVariants(RichTextEditorVariant.LUMO_NO_BORDER);
        }
    }

    @Subscribe("compactCheckbox")
    protected void onHighlightGutterCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (event.getValue()) {
            richTextEditor.addThemeVariants(RichTextEditorVariant.LUMO_COMPACT);
        } else {
            richTextEditor.removeThemeVariants(RichTextEditorVariant.LUMO_COMPACT);
        }
    }
    // end::theme-variant-handlers[] sample-hide
    // theme-only:lumo:end

    @Subscribe("readOnlyCheckbox")
    protected void onGutterCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        richTextEditor.setReadOnly(event.getValue());
    }

    @Subscribe("enabledCheckbox")
    protected void onLineNumbersCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        richTextEditor.setEnabled(!event.getValue());
    }
}
