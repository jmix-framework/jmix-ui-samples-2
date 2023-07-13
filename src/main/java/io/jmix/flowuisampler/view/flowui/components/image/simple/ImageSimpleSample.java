package io.jmix.flowuisampler.view.flowui.components.image.simple;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("image-simple")
@ViewDescriptor("image-simple.xml")
public class ImageSimpleSample extends StandardView {

    protected static final String SRC_PATH = "icons/icon.png";

    @ViewComponent
    protected HorizontalLayout content;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        initImage();
    }

    protected void initImage() {
        VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
        verticalLayout.setPadding(false);
        verticalLayout.addClassName(LumoUtility.AlignItems.CENTER);

        H4 h4 = uiComponents.create(H4.class);
        h4.setText("Programmatically defined source");

        JmixImage<?> image = uiComponents.create(JmixImage.class);
        image.setSrc(SRC_PATH);
        image.setHeight("10em");
        image.setWidth("10em");

        verticalLayout.add(h4, image);

        content.add(verticalLayout);
    }
}
