package io.jmix.uisamples.view.flowui.cookbook.customcomponent;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.component.customcomponent.Slider;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-component")
@ViewDescriptor("custom-component.xml")
public class CustomComponentSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("slider")
    protected void onSliderChange(Slider.SlideChangedEvent event) {
        notifications.show("New value is : " + event.getValue());
    }
}
