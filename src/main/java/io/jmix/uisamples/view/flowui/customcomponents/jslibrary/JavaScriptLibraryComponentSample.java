package io.jmix.uisamples.view.flowui.customcomponents.jslibrary;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.component.slider.Slider;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-component-js-library")
@ViewDescriptor("custom-component-js-library.xml")
public class JavaScriptLibraryComponentSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("slider")
    protected void onSliderChange(Slider.SlideChangedEvent event) {
        notifications.create("New value is : " + event.getValue())
                .withPosition(Notification.Position.TOP_CENTER)
                .show();
    }
}
