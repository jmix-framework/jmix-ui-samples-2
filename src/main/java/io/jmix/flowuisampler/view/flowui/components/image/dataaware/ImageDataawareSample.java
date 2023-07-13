package io.jmix.flowuisampler.view.flowui.components.image.dataaware;

import io.jmix.core.Metadata;
import io.jmix.core.Resources;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ViewController("image-dataaware")
@ViewDescriptor("image-dataaware.xml")
public class ImageDataawareSample extends StandardView {

    protected static final String SRC_PATH = "META-INF/resources/icons/icon.png";

    @ViewComponent
    protected InstanceContainer<Picture> pictureDc;

    @Autowired
    protected Metadata metadata;
    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) throws IOException {
        Picture picture = metadata.create(Picture.class);
        picture.setContent(resources.getResource(SRC_PATH).getContentAsByteArray());
        pictureDc.setItem(picture);
    }
}
