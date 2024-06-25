package io.jmix.uisamples.view.flowui.fragments.programmatic;

import io.jmix.flowui.Fragments;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.view.flowui.fragments.simple.ButtonSimpleFragment;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("fragment-programmatically-defined")
@ViewDescriptor("fragment-programmatically-defined.xml")
public class FragmentProgrammaticallyDefinedSample extends StandardView {

    @Autowired
    protected Fragments fragments;

    @Subscribe
    public void onInit(InitEvent event) {
        ButtonSimpleFragment fragment = fragments.create(this, ButtonSimpleFragment.class);
        getContent().add(fragment);
    }
}
