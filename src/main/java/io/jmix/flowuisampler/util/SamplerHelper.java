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

import io.jmix.core.CoreProperties;
import io.jmix.core.Resources;
import io.jmix.flowui.view.ViewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Component("sampler_SamplerHelper")
public class SamplerHelper {

    @Autowired
    protected Resources resources;
    @Autowired
    protected CoreProperties coreProperties;

    @Nullable
    public String getFileContent(String src) {
        return resources.getResourceAsString(src);
    }

    public String packageToPath(String pkg) {
        return pkg.replaceAll("[.]", "/");
    }

    public String getFileName(String src) {
        Path p = Paths.get(src);
        return p.getFileName().toString();
    }

    @Nullable
    public String getFileExtension(String src) {
        String fileName = getFileName(src);
        int index = fileName.lastIndexOf(".");
        if (index >= 0) {
            return fileName.substring(index + 1);
        }
        return null;
    }

    @Nullable
    public String findMessagePack(ViewInfo info) {
        Class<?> controllerClass = info.getControllerClass();

        for (Locale locale : coreProperties.getAvailableLocales()) {
            String messagesFileName = String.format("messages_%s.properties", locale.toString());

            if (controllerClass.getResource(messagesFileName) != null) {
                return controllerClass.getPackage().getName();
            }
        }

        return null;
    }
}
