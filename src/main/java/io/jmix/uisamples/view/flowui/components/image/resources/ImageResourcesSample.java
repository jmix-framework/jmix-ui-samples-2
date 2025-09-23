package io.jmix.uisamples.view.flowui.components.image.resources;

import com.vaadin.flow.server.streams.DownloadHandler;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.view.*;

@ViewController("image-resources")
@ViewDescriptor("image-resources.xml")
public class ImageResourcesSample extends StandardView {

    protected static final String SRC_PATH = "/icons/image-sample.svg";

    @ViewComponent
    protected JmixImage<?> imageStreamResource;

    @Subscribe
    protected void onInit(InitEvent event) {
        imageStreamResource.setSrc(DownloadHandler.forServletResource(SRC_PATH));
    }
}
