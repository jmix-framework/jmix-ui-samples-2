package io.jmix.uisamples.view.flowui.customcomponents.vaadinaddons.inputmaskadvanced;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.component.maskfield.MaskField;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("input-mask-addon-advanced")
@ViewDescriptor("input-mask-addon-advanced.xml")
public class InputMaskAddonAdvancedSample extends StandardView {

    @ViewComponent
    private MaskField phoneNumberField;

    @ViewComponent
    private MessageBundle messageBundle;

    @Autowired
    private Notifications notifications;

    @Subscribe("showNumberBtn")
    public void onShowNumberBtnClick(ClickEvent<JmixButton> event) {
        notifications.show(messageBundle.formatMessage("showNumberMessage", phoneNumberField.getValue()));
    }
}
