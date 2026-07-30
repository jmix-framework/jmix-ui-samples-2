package io.jmix.uisamples.view.flowui.components.button.image;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.streams.DownloadHandler;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("button-image")
@ViewDescriptor("button-image.xml")
public class ButtonImageSample extends StandardView {

    // tag::image-button-constant[] sample-hide
    private static final String LOGO_SRC_PATH = "/META-INF/resources/icons/jmix-logo.png";
    // end::image-button-constant[] sample-hide

    @Autowired
    private Notifications notifications;

    // tag::image-button-field[] sample-hide
    @ViewComponent
    private JmixButton logoButton;
    // end::image-button-field[] sample-hide

    // tag::image-button-init[] sample-hide
    @Subscribe
    public void onInit(InitEvent event) {
        Image logo = new Image(DownloadHandler.forClassResource(getClass(), LOGO_SRC_PATH), "jmix-logo");
        logo.setWidth("100px");
        logoButton.setIcon(logo);
    }
    // end::image-button-init[] sample-hide

    @Subscribe("logoButton")
    public void onLogoButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Jmix!");
    }
}
