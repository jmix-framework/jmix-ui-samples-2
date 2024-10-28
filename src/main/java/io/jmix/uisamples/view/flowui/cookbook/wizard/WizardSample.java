package io.jmix.uisamples.view.flowui.cookbook.wizard;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.icon.VaadinIcon;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Employee;
import io.jmix.uisamples.view.flowui.cookbook.wizard.step.FirstStep;
import io.jmix.uisamples.view.flowui.cookbook.wizard.step.SecondStep;
import io.jmix.uisamples.view.flowui.cookbook.wizard.step.ThirdStep;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("wizard")
@ViewDescriptor("wizard.xml")
public class WizardSample extends StandardView {

    @Autowired
    private DialogWindows dialogWindows;
    @Autowired
    private Fragments fragments;

    @Subscribe(id = "button", subject = "clickListener")
    public void onButtonClick(final ClickEvent<JmixButton> event) {
        dialogWindows.detail(this, Employee.class)
                .newEntity()
                .withViewClass(WizardDialog.class)
                .withViewConfigurer(this::wizardConfigurer)
                .open();
    }

    private void wizardConfigurer(WizardDialog wizardDialog) {
        wizardDialog.addStep(
                        new WizardStep<>(VaadinIcon.USER_CARD.create(), "Employee information",
                                fragments.create(wizardDialog, FirstStep.class))
                )
                .addStep(
                        new WizardStep<>(VaadinIcon.BUILDING.create(), "Address",
                                fragments.create(wizardDialog, SecondStep.class))
                )
                .addStep(
                        new WizardStep<>(VaadinIcon.CHECK_CIRCLE_O.create(), "Confirmation",
                                fragments.create(wizardDialog, ThirdStep.class))
                );
    }
}
