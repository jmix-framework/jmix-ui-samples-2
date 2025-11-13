package io.jmix.uisamples.view.flowui.cookbook.iframepdfdynamic;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.server.streams.DownloadHandler;
import io.jmix.core.Resources;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("iframe-pdf-dynamic")
@ViewDescriptor("iframe-pdf-dynamic.xml")
public class IframePdfDynamicSample extends StandardView {

    private static final String TUTORIAL_PDF_PATH = "pdf/tutorial.pdf";
    private static final String DOCUMENTATION_PDF_PATH = "pdf/documentation.pdf";

    @ViewComponent
    private IFrame iframe;

    @Autowired
    private Resources resources;

    @Subscribe
    public void onInit(InitEvent event) {
        // any other file can be provided in this way
        iframe.setSrc(DownloadHandler.forServletResource(TUTORIAL_PDF_PATH));
    }

    @Subscribe("newResourceBtn")
    public void onNewResourceBtnClick(ClickEvent<JmixButton> event) {
        iframe.setSrc(DownloadHandler.forServletResource(DOCUMENTATION_PDF_PATH));
    }
}
