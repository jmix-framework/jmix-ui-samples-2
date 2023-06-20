package io.jmix.flowuisampler.view.flowui.components.avatar;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.Resources;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.UUID;

@ViewController("avatar")
@ViewDescriptor("avatar.xml")
public class AvatarSample extends StandardView {

    @ViewComponent
    protected Avatar avatarWithImage;

    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) {
        InputStream image = resources.getResourceAsStream("META-INF/resources/icons/homer-simpson.png");
        avatarWithImage.setImageResource(new StreamResource(UUID.randomUUID().toString(), () -> image));
    }
}
