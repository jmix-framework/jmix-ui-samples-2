package io.jmix.flowuisampler.view.flowui.dialogsandnotifications.dialogs.message;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("message-dialog")
@ViewDescriptor("message-dialog.xml")
public class MessageDialogSample extends StandardView {

    @Autowired
    protected Dialogs dialogs;

    @Subscribe("textButton")
    protected void onTextButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createMessageDialog()
                .withHeader("Confirmation")
                .withText("You clicked the button")
                .open();
    }

    @Subscribe("htmlButton")
    protected void onHtmlButtonClick(ClickEvent<JmixButton> event) {
        Html htmlContent = new Html("<p>Here starts a paragraph. A new line starts after this.<br />" +
                "<b>This text is bold.</b> <i>This text is italic.</i></p>");

        dialogs.createMessageDialog()
                .withHeader("HTML Message")
                .withContent(htmlContent)
                .open();
    }

    @Subscribe("customSettingsButton")
    protected void onCustomSettingsButtonClick(ClickEvent<JmixButton> event) {
        Html htmlContent = new Html("<div><p>Settings applied:</p>" +
                "<ul>" +
                "<li>CloseOnEsc</li>" +
                "<li>CloseOutsizeClick</li>" +
                "<li>Resizable</li>" +
                "</ul></div>");

        dialogs.createMessageDialog()
                .withHeader("Custom Settings")
                .withContent(htmlContent)
                .withCloseOnEsc(true)
                .withCloseOnOutsideClick(true)
                .withResizable(true)
                .open();
    }
}
