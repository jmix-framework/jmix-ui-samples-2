package io.jmix.uisamples.view.flowui.components.button.image;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;

import java.io.InputStream;

@ViewController("button-image")
@ViewDescriptor("button-image.xml")
public class ButtonImageSample extends StandardView {

    private static final String LOGO_SRC_PATH = "/META-INF/resources/icons/jmix-logo.png";

    @ViewComponent
    private JmixButton logoButton;

    @Subscribe
    public void onInit(InitEvent event) {
        StreamResource logoResource = new StreamResource("jmix-logo.png", this::getLogoResource);
        Image logo = new Image(logoResource, "jmix-logo");
        logo.setWidth("100px");
        logoButton.setIcon(logo);
    }

    private InputStream getLogoResource() {
        return getClass().getResourceAsStream(LOGO_SRC_PATH);
    }
}
