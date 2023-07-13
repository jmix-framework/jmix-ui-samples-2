package io.jmix.flowuisampler.view.flowui.components.image.themevariant;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.dom.ThemeList;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.component.image.JmixImageVariant;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@ViewController("image-theme-variant")
@ViewDescriptor("image-theme-variant.xml")
public class ImageThemeVariant extends StandardView {

    @ViewComponent
    protected JmixSelect<JmixImageVariant> themeSelect;
    @ViewComponent
    protected JmixImage<?> image;

    @Subscribe
    protected void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(themeSelect, getThemeSelectItemsMap());
        themeSelect.setValue(JmixImageVariant.CONTAIN);
    }

    @Subscribe("themeSelect")
    protected void onThemeSelectValueChange(
            ComponentValueChangeEvent<JmixSelect<JmixImageVariant>, JmixImageVariant> event) {
        ThemeList themeList = image.getElement().getThemeList();
        themeList.clear();
        themeList.add(event.getValue().getVariantName());
    }

    protected Map<JmixImageVariant, String> getThemeSelectItemsMap() {
        return Arrays.stream(JmixImageVariant.values())
                .collect(Collectors.toMap(variant -> variant, variant -> variant.name().replace('_', ' ')));
    }
}
