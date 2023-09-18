package io.jmix.uisamples.view.flowui.dialogsandnotifications.dialogs.input;

import com.google.common.base.Strings;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.action.inputdialog.InputDialogAction;
import io.jmix.flowui.app.inputdialog.DialogActions;
import io.jmix.flowui.app.inputdialog.DialogOutcome;
import io.jmix.flowui.app.inputdialog.InputDialog;
import io.jmix.flowui.app.inputdialog.InputParameter;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.validation.ValidationErrors;
import io.jmix.flowui.kit.action.ActionVariant;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("input-dialog")
@ViewDescriptor("input-dialog.xml")
public class InputDialogSample extends StandardView {

    @Autowired
    protected Dialogs dialogs;
    @Autowired
    protected Notifications notifications;
    @Autowired
    protected MetadataTools metadataTools;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected DataManager dataManager;

    @Subscribe("standardDialogButton")
    protected void onStandardDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createInputDialog(this)
                .withHeader("Enter values")
                .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
                .withParameters(
                        InputParameter.stringParameter("name")
                                .withLabel("Name")
                                .withRequired(true),
                        InputParameter.doubleParameter("quantity")
                                .withLabel("Quantity")
                                .withDefaultValue(1.0),
                        InputParameter.entityParameter("customer", Customer.class)
                                .withLabel("Customer"),
                        InputParameter.enumParameter("grade", CustomerGrade.class)
                                .withLabel("Grade")
                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (!closeEvent.closedWith(DialogOutcome.OK)) {
                        return;
                    }

                    String name = closeEvent.getValue("name");
                    Double quantity = closeEvent.getValue("quantity");
                    Customer customer = closeEvent.getValue("customer");
                    CustomerGrade grade = closeEvent.getValue("grade");

                    String htmlContent = "<h4>Entered Values</h4>" +
                            "<strong>Name:</strong> " + name +
                            "<br/><strong>Quantity:</strong> " + quantity +
                            "<br/><strong>Customer:</strong> " + metadataTools.format(customer) +
                            "<br/><strong>Grade:</strong> " + metadataTools.format(grade);
                    Html notificationContent = new Html("<div>" + htmlContent + "</div>");
                    notifications.show(notificationContent);
                })
                .open();
    }

    @Subscribe("customParameterDialogButton")
    protected void onCustomParameterDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createInputDialog(this)
                .withHeader("Enter values")
                .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
                .withParameters(
                        InputParameter.stringParameter("name")
                                .withLabel("Name"),
                        InputParameter.parameter("customer")
                                .withLabel("Customer")
                                .withField(() -> {
                                    JmixComboBox<Customer> field = uiComponents.create(JmixComboBox.class);
                                    field.setItems(dataManager.load(Customer.class).all().list());
                                    field.setWidthFull();
                                    return field;
                                })
                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (!closeEvent.closedWith(DialogOutcome.OK)) {
                        return;
                    }

                    String name = closeEvent.getValue("name");
                    Customer customer = closeEvent.getValue("customer");

                    String htmlContent = "<h4>Entered Values</h4>" +
                            "<strong>Name:</strong> " + name +
                            "<br/><strong>Quantity:</strong> " + metadataTools.format(customer);
                    Html notificationContent = new Html("<div>" + htmlContent + "</div>");
                    notifications.show(notificationContent);
                })
                .open();
    }

    @Subscribe("customActionsDialogButton")
    protected void onCustomActionsDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createInputDialog(this)
                .withHeader("Enter values")
                .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
                .withParameters(
                        InputParameter.stringParameter("name")
                                .withLabel("Name")
                )
                .withActions(
                        InputDialogAction.action("confirm")
                                .withText("Confirm")
                                .withVariant(ActionVariant.PRIMARY)
                                .withHandler(actionEvent -> {
                                    InputDialogAction inputDialogAction = ((InputDialogAction) actionEvent.getSource());
                                    InputDialog inputDialog = inputDialogAction.getInputDialog();

                                    String name = inputDialog.getValue("name");
                                    inputDialog.closeWithDefaultAction();

                                    String htmlContent = "<h4>Entered Values</h4>" +
                                            "<strong>Name:</strong> " + name;
                                    Html notificationContent = new Html("<div>" + htmlContent + "</div>");
                                    notifications.show(notificationContent);
                                }),
                        InputDialogAction.action("refuse")
                                .withText("Refuse")
                                .withValidationRequired(false)
                                .withHandler(actionEvent -> {
                                    InputDialogAction inputDialogAction = ((InputDialogAction) actionEvent.getSource());
                                    InputDialog inputDialog = inputDialogAction.getInputDialog();
                                    inputDialog.closeWithDefaultAction();
                                })
                )
                .open();
    }

    @Subscribe("validationDialogButton")
    protected void onValidationDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createInputDialog(this)
                .withHeader("Enter values")
                .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
                .withParameters(
                        InputParameter.stringParameter("name")
                                .withLabel("Name"),
                        InputParameter.entityParameter("customer", Customer.class)
                                .withLabel("Customer")
                )
                .withValidator(context -> {
                    String name = context.getValue("name");
                    Customer customer = context.getValue("customer");
                    if (Strings.isNullOrEmpty(name) && customer == null) {
                        return ValidationErrors.of("Enter name or select a customer");
                    }
                    return ValidationErrors.none();
                })
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (!closeEvent.closedWith(DialogOutcome.OK)) {
                        return;
                    }

                    String name = closeEvent.getValue("name");
                    Customer customer = closeEvent.getValue("customer");

                    String htmlContent = "<h4>Entered Values</h4>" +
                            "<strong>Name:</strong> " + name +
                            "<br/><strong>Customer:</strong> " + metadataTools.format(customer);
                    Html notificationContent = new Html("<div>" + htmlContent + "</div>");
                    notifications.show(notificationContent);
                })
                .open();
    }
}
