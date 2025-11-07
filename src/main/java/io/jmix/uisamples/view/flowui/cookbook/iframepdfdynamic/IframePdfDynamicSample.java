package io.jmix.uisamples.view.flowui.cookbook.iframepdfdynamic;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.Resources;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("iframe-pdf-dynamic")
@ViewDescriptor("iframe-pdf-dynamic.xml")
public class IframePdfDynamicSample extends StandardView {

    private static final String PDF_BASE_PACKAGE = "/META-INF/resources/pdf";
    private static final String TUTORIAL_PDF_PATH = PDF_BASE_PACKAGE + "/tutorial.pdf";
    private static final String DOCUMENTATION_PDF_PATH = PDF_BASE_PACKAGE + "/documentation.pdf";

    @ViewComponent
    private IFrame iframe;

    @Autowired
    private Resources resources;

    @Subscribe
    public void onInit(InitEvent event) {
        // any other file can be provided in this way
        StreamResource streamResource =
                new StreamResource("tutorial.pdf", () -> resources.getResourceAsStream(TUTORIAL_PDF_PATH));
        iframe.setSrc(streamResource);
    }

    @Subscribe("newResourceBtn")
    public void onNewResourceBtnClick(ClickEvent<JmixButton> event) {
        StreamResource streamResource =
                new StreamResource("documentation.pdf", () -> resources.getResourceAsStream(DOCUMENTATION_PDF_PATH));
        iframe.setSrc(streamResource);
    }
}
