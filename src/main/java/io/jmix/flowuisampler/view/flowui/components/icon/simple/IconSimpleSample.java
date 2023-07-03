package io.jmix.flowuisampler.view.flowui.components.icon.simple;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;

@ViewController("icon-simple")
@ViewDescriptor("icon-simple.xml")
public class IconSimpleSample extends StandardView {

    @ViewComponent
    protected TypedTextField<String> field;
    @ViewComponent
    protected HorizontalLayout content;

    @Subscribe
    protected void onInit(InitEvent event) {
        field.setSuffixComponent(VaadinIcon.QUESTION_CIRCLE.create());

        Icon icon = ComponentUtils.parseIcon("lumo:user");
        content.add(icon);
    }
}
