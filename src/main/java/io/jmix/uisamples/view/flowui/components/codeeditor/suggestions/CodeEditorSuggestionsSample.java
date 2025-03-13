package io.jmix.uisamples.view.flowui.components.codeeditor.suggestions;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.core.Resources;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorMode;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@ViewController("code-editor-suggestions")
@ViewDescriptor("code-editor-suggestions.xml")
public class CodeEditorSuggestionsSample extends StandardView {

    private static final String DEFAULT_FILE_PATH = "io/jmix/uisamples/codeeditorexample/";

    @ViewComponent
    private CodeEditor codeEditor;
    @ViewComponent
    private JmixSelect<CodeEditorMode> modeSelect;

    @Autowired
    private Resources resources;

    @Subscribe
    public void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(modeSelect, getModeItemsMap());
        modeSelect.setValue(CodeEditorMode.SQL);
    }

    @Subscribe("defaultSuggestionsCheckbox")
    public void onDefaultSuggestionsCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setDefaultSuggestionsEnabled(event.getValue());
    }

    @Subscribe("liveSuggestionsCheckbox")
    public void liveSuggestionsCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        codeEditor.setLiveSuggestionsEnabled(event.getValue());
    }

    @Subscribe("modeSelect")
    public void onModeSelectValueChange(
            ComponentValueChangeEvent<JmixSelect<CodeEditorMode>, CodeEditorMode> event) {
        codeEditor.setMode(event.getValue());
        codeEditor.setValue(resources.getResourceAsString(DEFAULT_FILE_PATH + event.getValue()));
    }

    private Map<CodeEditorMode, String> getModeItemsMap() {
        LinkedHashMap<CodeEditorMode, String> map = new LinkedHashMap<>();
        map.put(CodeEditorMode.JAVA, "Java");
        map.put(CodeEditorMode.CSS, "CSS");
        map.put(CodeEditorMode.XML, "XML");
        map.put(CodeEditorMode.JSON, "JSON");
        map.put(CodeEditorMode.JAVASCRIPT, "JavaScript");
        map.put(CodeEditorMode.HTML, "HTML");
        map.put(CodeEditorMode.TEXT, "Text");
        map.put(CodeEditorMode.GROOVY, "Groovy");
        map.put(CodeEditorMode.PROPERTIES, "Properties");
        map.put(CodeEditorMode.SQL, "SQL");
        map.put(CodeEditorMode.PYTHON, "Python");
        map.put(CodeEditorMode.SWIFT, "Swift");
        map.put(CodeEditorMode.C_CPP, "C++");
        return map;
    }
}
