package io.jmix.uisamples.view.flowui.cookbook.wizard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;

public record WizardStep<T extends Component & WizardStepFragment>(Icon icon, String text, T content) {
}
