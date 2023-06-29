package io.jmix.flowuisampler.view.flowui.components.codeeditor.modetheme;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.core.Resources;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorMode;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorTheme;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ViewController("code-editor-mode-theme")
@ViewDescriptor("code-editor-mode-theme.xml")
public class CodeEditorModeThemeSample extends StandardView {

    protected static final String DEFAULT_FILE_PATH = "io/jmix/flowuisampler/codeeditorexample/";

    @ViewComponent
    protected CodeEditor codeEditor;

    @ViewComponent
    protected JmixSelect<CodeEditorMode> modeComboBox;
    @ViewComponent
    protected JmixSelect<CodeEditorTheme> themeComboBox;

    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(modeComboBox, getModeItemsMap());
        ComponentUtils.setItemsMap(themeComboBox, getThemeItemsMap());
    }

    @Subscribe("modeComboBox")
    protected void onModeComboBoxValueChange(
            ComponentValueChangeEvent<JmixSelect<CodeEditorMode>, CodeEditorMode> event) {
        codeEditor.setMode(event.getValue());
        codeEditor.setValue(resources.getResourceAsString(DEFAULT_FILE_PATH + event.getValue()));
    }

    @Subscribe("themeComboBox")
    protected void onThemeComboBoxValueChange(
            ComponentValueChangeEvent<JmixSelect<CodeEditorTheme>, CodeEditorTheme> event) {
        codeEditor.setTheme(event.getValue());
    }

    protected Map<CodeEditorMode, String> getModeItemsMap() {
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

    protected Map<CodeEditorTheme, String> getThemeItemsMap() {
        return Arrays.stream(CodeEditorTheme.values())
                .collect(Collectors.toMap(mode -> mode, CodeEditorTheme::name));
    }
}
