package io.jmix.uisamples.view.flowui.components.html.htmlcomponent;

import com.vaadin.flow.component.Html;
import io.jmix.core.Resources;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

@ViewController("html-component")
@ViewDescriptor("html-component.xml")
public class HtmlComponentSample extends StandardView {

    protected static final String SRC_PATH = "META-INF/resources/html/html-component.html";

    @ViewComponent
    protected JmixTabSheet tabSheet;

    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) {
        InputStream resourceAsStream = resources.getResourceAsStream(SRC_PATH);

        Html html = new Html(resourceAsStream);
        tabSheet.add("Programmatically added component", html);
    }
}
