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

package io.jmix.flowuisampler.bean;

import com.vaadin.flow.spring.annotation.UIScope;
import io.jmix.flowuisampler.component.themeswitcher.ThemeToggle;
import org.springframework.stereotype.Component;

@UIScope
@Component("sampler_ThemeToggleBinder")
public class ThemeToggleBinder {

    protected ThemeToggle themeToggle;

    public ThemeToggle getThemeToggle() {
        return this.themeToggle;
    }

    public void setThemeToggle(ThemeToggle themeToggle) {
        this.themeToggle = themeToggle;
    }
}
