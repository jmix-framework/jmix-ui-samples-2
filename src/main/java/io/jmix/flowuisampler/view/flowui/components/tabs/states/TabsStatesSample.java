package io.jmix.flowuisampler.view.flowui.components.tabs.states;

import com.vaadin.flow.component.tabs.Tabs;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@ViewController("tabs-states")
@ViewDescriptor("tabs-states.xml")
public class TabsStatesSample extends StandardView {

    @Subscribe("tabs")
    protected void onTabsSelectChangeEvent(Tabs.SelectedChangeEvent event) {
        event.getPreviousTab().setLabel("Unselected");
        event.getSelectedTab().setLabel("Selected");
    }
}
