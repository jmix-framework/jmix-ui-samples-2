package io.jmix.uisamples.component.maskfield;

import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.componentfactory.addons.inputmask.InputMaskOption;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.shared.HasPrefix;
import com.vaadin.flow.component.shared.HasSuffix;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.textfield.TypedTextField;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MaskField extends Composite<TypedTextField<String>>
        implements ApplicationContextAware, HasLabel, HasSize, HasHelper, HasSuffix, HasPrefix {

    private UiComponents uiComponents;
    private ApplicationContext applicationContext;

    private TypedTextField<String> root;

    private String mask;
    private InputMask inputMask;
    private List<InputMaskOption> options = Collections.emptyList();

    @Override
    protected TypedTextField<String> initContent() {
        root = uiComponents().create(TypedTextField.class);
        return root;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setMask(String mask) {
        if (inputMask != null) {
            inputMask.remove();
            this.mask = null;
        }

        inputMask = new InputMask(mask, options.toArray(new InputMaskOption[0]));
        this.mask = mask;
        inputMask.extend(root);
    }

    public void setOptions(List<InputMaskOption> options) {
        if (inputMask != null) {
            inputMask.remove();
        }

        inputMask = new InputMask(mask, options.toArray(new InputMaskOption[0]));
        this.options = options;
        inputMask.extend(root);
    }

    public void addOption(InputMaskOption option) {
        if (options.isEmpty()) {
            options = new ArrayList<>();
        }

        options.add(option);
        setOptions(options);
    }

    @Override
    public String getLabel() {
        return getContent().getLabel();
    }

    @Override
    public void setLabel(String label) {
        getContent().setLabel(label);
    }

    public String getValue() {
        return root.getTypedValue();
    }

    public void setValue(String value) {
        root.setTypedValue(value);
    }

    @Override
    public void setWidth(String width) {
        root.setWidth(width);
    }

    @Override
    public void setWidth(float width, Unit unit) {
        root.setWidth(width, unit);
    }

    @Override
    public void setMinWidth(String minWidth) {
        root.setMinWidth(minWidth);
    }

    @Override
    public void setMinWidth(float minWidth, Unit unit) {
        root.setMinWidth(minWidth, unit);
    }

    @Override
    public void setMaxWidth(String maxWidth) {
        root.setMaxWidth(maxWidth);
    }

    @Override
    public void setMaxWidth(float maxWidth, Unit unit) {
        root.setMaxWidth(maxWidth, unit);
    }

    @Override
    public String getWidth() {
        return root.getWidth();
    }

    @Override
    public String getMinWidth() {
        return root.getMinWidth();
    }

    @Override
    public String getMaxWidth() {
        return root.getMaxWidth();
    }

    @Override
    public Optional<Unit> getWidthUnit() {
        return root.getWidthUnit();
    }

    @Override
    public void setHeight(String height) {
        root.setHeight(height);
    }

    @Override
    public void setHeight(float height, Unit unit) {
        root.setHeight(height, unit);
    }

    @Override
    public void setMinHeight(String minHeight) {
        root.setMinHeight(minHeight);
    }

    @Override
    public void setMinHeight(float minHeight, Unit unit) {
        root.setMinHeight(minHeight, unit);
    }

    @Override
    public void setMaxHeight(String maxHeight) {
        root.setMaxHeight(maxHeight);
    }

    @Override
    public void setMaxHeight(float maxHeight, Unit unit) {
        root.setMaxHeight(maxHeight, unit);
    }

    @Override
    public String getHeight() {
        return root.getHeight();
    }

    @Override
    public String getMinHeight() {
        return root.getMinHeight();
    }

    @Override
    public String getMaxHeight() {
        return root.getMaxHeight();
    }

    @Override
    public Optional<Unit> getHeightUnit() {
        return root.getHeightUnit();
    }

    @Override
    public void setSizeFull() {
        root.setSizeFull();
    }

    @Override
    public void setWidthFull() {
        root.setWidthFull();
    }

    @Override
    public void setHeightFull() {
        root.setHeightFull();
    }

    @Override
    public void setSizeUndefined() {
        root.setSizeUndefined();
    }

    @Override
    public String getHelperText() {
        return root.getHelperText();
    }

    @Override
    public void setHelperText(String helperText) {
        root.setHelperText(helperText);
    }

    @Override
    public void setHelperComponent(Component component) {
        root.setHelperComponent(component);
    }

    @Override
    public Component getHelperComponent() {
        return root.getHelperComponent();
    }

    @Override
    public void setPrefixComponent(Component component) {
        root.setPrefixComponent(component);
    }

    @Override
    public Component getPrefixComponent() {
        return root.getPrefixComponent();
    }

    @Override
    public void setSuffixComponent(Component component) {
        root.setSuffixComponent(component);
    }

    @Override
    public Component getSuffixComponent() {
        return root.getSuffixComponent();
    }

    private UiComponents uiComponents() {
        if (uiComponents == null) {
            uiComponents = applicationContext.getBean(UiComponents.class);
        }

        return uiComponents;
    }
}
