package io.jmix.flowuisampler.view.flowui.components.image.resources;

import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.view.*;

import java.io.InputStream;

@ViewController("image-resources")
@ViewDescriptor("image-resources.xml")
public class ImageResourcesSample extends StandardView {

    protected static final String SRC_PATH = "/META-INF/resources/icons/image-sample.svg";

    @ViewComponent
    protected JmixImage<?> imageStreamResource;

    @Subscribe
    protected void onInit(InitEvent event) {
        StreamResource imageResource = new StreamResource("image-sample.svg", this::getResource);
        imageStreamResource.setSrc(imageResource);
    }

    protected InputStream getResource() {
        return getClass().getResourceAsStream(SRC_PATH);
    }
}
