/*
 * Copyright 2022 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.flowuisampler.util;

import com.vaadin.flow.spring.annotation.UIScope;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorTheme;
import io.jmix.flowuisampler.view.sys.main.MainView;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("sampler_CodeEditorThemeHelper")
@UIScope
public class CodeEditorThemeHelper {

    protected Set<CodeEditor> codeEditors;

    @EventListener
    public void onThemeChangedEvent(MainView.ThemeChangedEvent event) {
        String currentTheme = event.getTheme();
        CodeEditorTheme codeEditorTheme = "dark".equalsIgnoreCase(currentTheme)
                ? CodeEditorTheme.NORD_DARK
                : CodeEditorTheme.TEXTMATE;

        if (codeEditors != null) {
            codeEditors.forEach(editor -> editor.setTheme(codeEditorTheme));
        }
    }

    public void setCodeEditors(Set<CodeEditor> codeEditors) {
        this.codeEditors = codeEditors;
    }
}
