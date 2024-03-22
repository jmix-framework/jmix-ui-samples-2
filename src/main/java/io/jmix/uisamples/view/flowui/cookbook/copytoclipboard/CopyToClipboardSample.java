package io.jmix.uisamples.view.flowui.cookbook.copytoclipboard;

import com.google.common.base.Strings;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.dom.Element;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("copy-to-clipboard")
@ViewDescriptor("copy-to-clipboard.xml")
public class CopyToClipboardSample extends StandardView {

    @ViewComponent
    protected TypedTextField<String> textField;

    @Autowired
    protected Notifications notifications;

    @Subscribe("copyButton")
    public void onCopyButtonClick(ClickEvent<JmixButton> event) {
        Element buttonElement = event.getSource().getElement();
        String valueToCopy = Strings.nullToEmpty(textField.getTypedValue());

        buttonElement.executeJs(getCopyToClipboardScript(), valueToCopy)
                .then(successResult -> notifications.create("Text copied!")
                                .withPosition(Notification.Position.BOTTOM_END)
                                .withThemeVariant(NotificationVariant.LUMO_SUCCESS)
                                .show(),
                        errorResult -> notifications.create("Copy failed!")
                                .withPosition(Notification.Position.BOTTOM_END)
                                .withThemeVariant(NotificationVariant.LUMO_ERROR)
                                .show());
    }

    protected String getCopyToClipboardScript() {
        return """
                  const textarea = document.createElement("textarea");
                  textarea.value = $0;
                  
                  textarea.style.position = "absolute";
                  textarea.style.opacity = "0";
                  
                  document.body.appendChild(textarea);
                  textarea.select();
                  document.execCommand("copy");
                  document.body.removeChild(textarea);
                """;
    }
}
