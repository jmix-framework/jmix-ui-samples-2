package io.jmix.flowuisampler.view.flowui.components.badge.icons;

import com.vaadin.flow.component.icon.Icon;
import io.jmix.flowui.view.*;

@ViewController("badge-icons")
@ViewDescriptor("badge-icons.xml")
public class BadgeIconsSample extends StandardView {

    @ViewComponent
    protected Icon checkIcon;
    @ViewComponent
    protected Icon closeIcon;

    @Subscribe
    protected void onInit(InitEvent event) {
        checkIcon.getElement().getThemeList().add("badge success");
        checkIcon.getStyle().set("padding", "var(--lumo-space-xs)");

        closeIcon.getElement().getThemeList().add("badge error");
        closeIcon.getStyle().set("padding", "var(--lumo-space-xs)");
    }
}
