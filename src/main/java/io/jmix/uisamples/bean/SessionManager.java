/*
 * Copyright 2023 Haulmont.
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

package io.jmix.uisamples.bean;

import com.vaadin.flow.server.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("uisamples_SessionManager")
public class SessionManager implements VaadinServiceInitListener {

    @Value("${UI_SAMPLES_LOCALE:en}")
    protected String locale;

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().setSystemMessagesProvider((SystemMessagesProvider) systemMessagesInfo -> {
            CustomizedSystemMessages messages = new CustomizedSystemMessages();
            messages.setSessionExpiredNotificationEnabled(true);
            return messages;
        });
        event.getSource().addSessionInitListener(this::onSessionInit);
    }

    protected void onSessionInit(SessionInitEvent sessionInitEvent) {
        initLocale();
    }

    public void initLocale() {
        VaadinSession.getCurrent().setLocale(Locale.forLanguageTag(locale));
    }
}
