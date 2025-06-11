package io.jmix.uisamples.component.maskfield;

import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.componentfactory.addons.inputmask.InputMaskOption;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasHelper;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasSize;
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

    public String getValue() {
        return root.getTypedValue();
    }

    public void setValue(String value) {
        root.setTypedValue(value);
    }

    private UiComponents uiComponents() {
        if (uiComponents == null) {
            uiComponents = applicationContext.getBean(UiComponents.class);
        }

        return uiComponents;
    }
}
