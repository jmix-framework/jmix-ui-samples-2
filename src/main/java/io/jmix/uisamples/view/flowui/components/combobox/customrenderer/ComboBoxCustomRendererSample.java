package io.jmix.uisamples.view.flowui.components.combobox.customrenderer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("combobox-custom-renderer")
@ViewDescriptor("combobox-custom-renderer.xml")
public class ComboBoxCustomRendererSample extends StandardView {

    @ViewComponent
    protected JmixComboBox<VaadinIcon> iconsComboBox;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        iconsComboBox.setItems(VaadinIcon.values());
        iconsComboBox.setRenderer(new ComponentRenderer<Component, VaadinIcon>(vaadinIcon -> {
            HorizontalLayout contentBox = uiComponents.create(HorizontalLayout.class);
            contentBox.setPadding(false);

            contentBox.add(vaadinIcon.create());
            contentBox.add(vaadinIcon.name());

            return contentBox;
        }));
    }
}
