package io.jmix.uisamples.view.flowui.components.avatar;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.server.streams.DownloadHandler;
import io.jmix.core.Resources;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("avatar")
@ViewDescriptor("avatar.xml")
public class AvatarSample extends StandardView {

    @ViewComponent
    protected Avatar avatarWithImage;

    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) {
        avatarWithImage.setImageHandler(
                DownloadHandler.forClassResource(getClass(), "/META-INF/resources/icons/homer-simpson.png")
        );
    }
}
