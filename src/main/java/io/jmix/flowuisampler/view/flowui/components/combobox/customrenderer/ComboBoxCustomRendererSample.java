package io.jmix.flowuisampler.view.flowui.components.combobox.customrenderer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@ViewController("combobox-custom-renderer")
@ViewDescriptor("combobox-custom-renderer.xml")
public class ComboBoxCustomRendererSample extends StandardView {

    @ViewComponent
    protected JmixComboBox<VaadinIcon> iconsComboBox;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(iconsComboBox, getItemsMap());
        iconsComboBox.setRenderer(new ComponentRenderer<Component, VaadinIcon>(vaadinIcon -> {
            HorizontalLayout contentBox = uiComponents.create(HorizontalLayout.class);
            contentBox.setPadding(false);

            contentBox.add(vaadinIcon.create());
            contentBox.add(vaadinIcon.name());

            return contentBox;
        }));
    }

    protected Map<VaadinIcon, String> getItemsMap() {
        return Arrays.stream(VaadinIcon.values())
                .collect(Collectors.toMap(icon -> icon, Enum::name));
    }
}
