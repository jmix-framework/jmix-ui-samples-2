package io.jmix.uisamples.view.flowui.containers.sidepanellayout;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.sidepanellayout.SidePanelLayout;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.sidepanellayout.SidePanelPosition;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

@ViewController("side-panel-layout")
@ViewDescriptor("side-panel-layout.xml")
public class SidePanelLayoutSample extends StandardView {

    @ViewComponent
    private JmixSelect<SidePanelPosition> positionSelect;
    @ViewComponent
    private SidePanelLayout sidePanelLayout;
    @ViewComponent
    private TypedTextField<String> emailField;
    @ViewComponent
    private TypedTextField<String> lastNameField;
    @ViewComponent
    private TypedTextField<String> firstNameField;

    @Subscribe
    public void onInit(InitEvent event) {
        positionSelect.setItems(SidePanelPosition.values());
        positionSelect.setValue(sidePanelLayout.getSidePanelPosition());
        positionSelect.addValueChangeListener(e -> sidePanelLayout.setSidePanelPosition(e.getValue()));
        positionSelect.setItemLabelGenerator(Enum::name);
    }

    @Subscribe(id = "toggleSidePanelButton", subject = "clickListener")
    public void onToggleSidePanelButtonClick(ClickEvent<JmixButton> event) {
        sidePanelLayout.toggleSidePanel();
    }

    @Subscribe("overlayCheckbox")
    public void onOverlayCheckboxComponentValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        sidePanelLayout.setSidePanelOverlay(event.getValue());
    }

    @Subscribe("modalCheckbox")
    public void onModalCheckboxComponentValueChange(final ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        sidePanelLayout.setModal(event.getValue());
    }

    @Subscribe(id = "customersDc", target = Target.DATA_CONTAINER)
    public void onCustomersDcItemChange(final InstanceContainer.ItemChangeEvent<Customer> event) {
        emailField.setTypedValue(event.getItem() == null ? null : event.getItem().getEmail());
        lastNameField.setTypedValue(event.getItem() == null ? null : event.getItem().getLastName());
        firstNameField.setTypedValue(event.getItem() == null ? null : event.getItem().getName());
    }
}
