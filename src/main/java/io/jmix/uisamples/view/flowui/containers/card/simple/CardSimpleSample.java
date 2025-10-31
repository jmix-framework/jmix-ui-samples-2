package io.jmix.uisamples.view.flowui.containers.card.simple;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import io.jmix.core.Messages;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("card-simple")
@ViewDescriptor("card-simple.xml")
public class CardSimpleSample extends StandardView {

    @Autowired
    private Messages messages;

    @Subscribe("visitSiteButton")
    public void onVisitSiteButtonClick(final ClickEvent<JmixButton> event) {
        UI.getCurrent().getPage().open(messages.getMessage("websiteUrl"));
    }

    @Subscribe("learnMoreButton")
    public void onLearnMoreButtonClick(final ClickEvent<JmixButton> event) {
        UI.getCurrent().getPage().open(messages.getMessage("docUrl"));
    }
}
