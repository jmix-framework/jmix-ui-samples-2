package io.jmix.uisamples.view.flowui.fragments.events;

import io.jmix.flowui.Fragments;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("fragment-events")
@ViewDescriptor("fragment-events.xml")
public class FragmentEventsSample extends StandardView {

    @Autowired
    protected Fragments fragments;

    @ViewComponent
    protected EventsFragment declarativeFragment;

    protected EventsFragment programmaticFragment;

    @Subscribe
    public void onInit(InitEvent event) {
        programmaticFragment = fragments.create(this, EventsFragment.class);
        getContent().add(programmaticFragment);

        programmaticFragment.addExecutedEvent("View init event");
        declarativeFragment.addExecutedEvent("View init event");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        programmaticFragment.addExecutedEvent("View before show event");
        declarativeFragment.addExecutedEvent("View before show event");
    }

    @Subscribe
    public void onReady(ReadyEvent event) {
        programmaticFragment.addExecutedEvent("View ready event");
        declarativeFragment.addExecutedEvent("View ready event");
    }
}
