package io.jmix.uisamples.bean;

import com.vaadin.flow.server.VaadinSession;
import io.jmix.core.CoreProperties;
import io.jmix.core.JmixModules;
import io.jmix.core.Resources;
import io.jmix.flowui.exception.UiExceptionHandlers;
import io.jmix.flowui.sys.JmixServiceInitListener;
import io.jmix.flowui.view.ViewRegistry;

public class UiSamplesServiceInitListener extends JmixServiceInitListener {

    public UiSamplesServiceInitListener(ViewRegistry viewRegistry,
                                        UiExceptionHandlers uiExceptionHandlers,
                                        CoreProperties coreProperties,
                                        JmixModules modules,
                                        Resources resources) {
        super(viewRegistry, uiExceptionHandlers, coreProperties, modules, resources);
    }

    @Override
    protected void initCookieLocale(VaadinSession session) {
        // do nothing
        // locale has been handled by io.jmix.uisamples.bean.SessionManager
    }
}
