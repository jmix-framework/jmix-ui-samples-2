package io.jmix.flowuisampler.view.flowui.components.tabs.orientation;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.tabs.Tabs;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;

@ViewController("tabs-orientation")
@ViewDescriptor("tabs-orientation.xml")
public class TabsOrientationSample extends StandardView {

    @ViewComponent
    protected Tabs tabs;

    @Subscribe("orientationCheckbox")
    protected void onOrientationCheckboxValueChane(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        tabs.setOrientation(event.getValue() ? Tabs.Orientation.VERTICAL : Tabs.Orientation.HORIZONTAL);
    }
}
