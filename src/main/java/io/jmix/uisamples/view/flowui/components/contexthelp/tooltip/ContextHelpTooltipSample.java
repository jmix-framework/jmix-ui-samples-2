package io.jmix.uisamples.view.flowui.components.contexthelp.tooltip;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.IntegerField;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.SupportsTypedValue;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.theme.StyleUtility;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ViewController("context-help-tooltip")
@ViewDescriptor("context-help-tooltip.xml")
public class ContextHelpTooltipSample extends StandardView {

    // tag::manual-field[] sample-hide
    @ViewComponent
    protected TypedTextField<String> manualTooltipField;
    // end::manual-field[] sample-hide
    @ViewComponent
    protected TypedTextField<String> customTooltipField;
    // tag::position-field[] sample-hide
    @ViewComponent
    protected JmixSelect<Tooltip.TooltipPosition> position;
    // end::position-field[] sample-hide

    // tag::ui-components[] sample-hide
    @Autowired
    protected UiComponents uiComponents;
    // end::ui-components[] sample-hide

    // tag::init[] sample-hide
    @Subscribe
    protected void onInit(InitEvent event) {
        initManualTooltip();
        initPositionItems();
    }
    // end::init[] sample-hide

    // tag::manual-init[] sample-hide
    protected void initManualTooltip() {
        JmixButton helperButton = createHelperButton();
        Tooltip tooltip = manualTooltipField.getTooltip();
        helperButton.addClickListener(e -> tooltip.setOpened(!tooltip.isOpened()));

        manualTooltipField.setSuffixComponent(helperButton);
    }
    // end::manual-init[] sample-hide

    // tag::position-init[] sample-hide
    protected void initPositionItems() {
        ComponentUtils.setItemsMap(position, getPositionItemsMap());
        position.setValue(Tooltip.TooltipPosition.TOP);
    }
    // end::position-init[] sample-hide

    // tag::focus-delay[] sample-hide
    @Subscribe("focusDelay")
    protected void onFocusDelayChange(ComponentValueChangeEvent<IntegerField, Integer> event) {
        Integer value = event.getValue();

        if (value != null && value > 0 && value <= 5000) {
            customTooltipField.getTooltip().setFocusDelay(value);
        } else {
            customTooltipField.getTooltip().setFocusDelay(0);
        }
    }
    // end::focus-delay[] sample-hide

    // tag::hide-delay[] sample-hide
    @Subscribe("hideDelay")
    protected void onHideDelayChange(ComponentValueChangeEvent<IntegerField, Integer> event) {
        Integer value = event.getValue();

        if (value != null && value > 0 && value <= 5000) {
            customTooltipField.getTooltip().setHideDelay(value);
        } else {
            customTooltipField.getTooltip().setHideDelay(0);
        }
    }
    // end::hide-delay[] sample-hide

    // tag::hover-delay[] sample-hide
    @Subscribe("hoverDelay")
    protected void onHoverDelayChange(ComponentValueChangeEvent<IntegerField, Integer> event) {
        Integer value = event.getValue();

        if (value != null && value > 0 && value <= 5000) {
            customTooltipField.getTooltip().setHoverDelay(value);
        } else {
            customTooltipField.getTooltip().setHoverDelay(0);
        }
    }
    // end::hover-delay[] sample-hide

    @Subscribe("manual")
    protected void onManualChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        Tooltip tooltip = customTooltipField.getTooltip();
        tooltip.setManual(event.getValue());

        if (event.getValue()) {
            JmixButton helperButton = createHelperButton();
            helperButton.addClickListener(e -> tooltip.setOpened(!tooltip.isOpened()));

            customTooltipField.setSuffixComponent(helperButton);
        } else {
            customTooltipField.setSuffixComponent(null);
        }
    }

    // tag::position-handler[] sample-hide
    @Subscribe("position")
    protected void onPositionChange(
            ComponentValueChangeEvent<JmixSelect<Tooltip.TooltipPosition>, Tooltip.TooltipPosition> event) {
        customTooltipField.getTooltip().setPosition(event.getValue());
    }
    // end::position-handler[] sample-hide

    @Subscribe("text")
    protected void onTextChange(SupportsTypedValue.TypedValueChangeEvent<TypedTextField<String>, String> event) {
        customTooltipField.getTooltip().setText(event.getValue());
    }

    // tag::helper-button[] sample-hide
    protected JmixButton createHelperButton() {
        JmixButton helperButton = uiComponents.create(JmixButton.class);
        helperButton.setIcon(VaadinIcon.QUESTION_CIRCLE.create());
        helperButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        helperButton.addClassName(StyleUtility.Button.LINK_BUTTON);

        return helperButton;
    }
    // end::helper-button[] sample-hide

    // tag::position-items[] sample-hide
    protected Map<Tooltip.TooltipPosition, String> getPositionItemsMap() {
        return Arrays.stream(Tooltip.TooltipPosition.values())
                .collect(Collectors.toMap(Function.identity(),mode -> mode.name().replace('_', ' ')));
    }
    // end::position-items[] sample-hide
}
