package io.jmix.uisamples.view.flowui.customcomponents.vaadinaddons.inputmasksimple;

import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.componentfactory.addons.inputmask.InputMaskOption;
import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("input-mask-addon-simple")
@ViewDescriptor("input-mask-addon-simple.xml")
public class InputMaskAddonSimpleSample extends StandardView {

    @ViewComponent
    private TypedTextField<String> phoneNumberField;

    @ViewComponent
    private MessageBundle messageBundle;

    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(InitEvent event) {
        InputMask inputMask = new InputMask(
                "+{1} 000 000-0000",
                InputMaskOption.lazy(false),
                InputMaskOption.overwrite(true)
        );
        inputMask.extend(phoneNumberField);
    }

    @Subscribe("showNumberBtn")
    public void onShowNumberBtnClick(ClickEvent<JmixButton> event) {
        notifications.show(messageBundle.formatMessage("showNumberMessage", phoneNumberField.getValue()));
    }
}
