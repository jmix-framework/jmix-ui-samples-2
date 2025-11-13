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

    private static final String LOGO_SRC_PATH = "icons/jmix-logo.png";

    @Autowired
    private Notifications notifications;

    @ViewComponent
    private JmixButton logoButton;

    @Subscribe
    public void onInit(InitEvent event) {
        Image logo = new Image(DownloadHandler.forServletResource(LOGO_SRC_PATH), "jmix-logo");
        logo.setWidth("100px");
        logoButton.setIcon(logo);
    }

    @Subscribe("logoButton")
    public void onLogoButtonClick(ClickEvent<JmixButton> event) {
        notifications.show("Jmix!");
    }
}
