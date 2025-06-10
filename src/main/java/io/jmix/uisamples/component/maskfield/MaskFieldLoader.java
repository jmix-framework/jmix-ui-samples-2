package io.jmix.uisamples.component.maskfield;

import com.vaadin.componentfactory.addons.inputmask.InputMaskOption;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowui.xml.layout.support.PrefixSuffixLoaderSupport;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MaskFieldLoader extends AbstractComponentLoader<MaskField> {

    protected PrefixSuffixLoaderSupport prefixSuffixLoaderSupport;

    @Override
    protected MaskField createComponent() {
        return factory.create(MaskField.class);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        prefixSuffixLoaderSupport().createPrefixSuffixComponents(resultComponent, element);
    }

    @Override
    public void loadComponent() {
        loadResourceString(element, "label", context.getMessageGroup(), resultComponent::setLabel);
        loadString(element, "mask", resultComponent::setMask);

        componentLoader().loadSizeAttributes(resultComponent, element);
        componentLoader().loadHelperText(resultComponent, element);
        prefixSuffixLoaderSupport().loadPrefixSuffixComponents();

        loadOptions(resultComponent::setOptions, element);
        loadDefaultOptions(resultComponent, element);
    }

    private void loadDefaultOptions(MaskField resultComponent, Element element) {
        loadBoolean(element, "lazy")
                .map(InputMaskOption::lazy)
                .ifPresent(resultComponent::addOption);

        loadBoolean(element, "overwrite")
                .map(InputMaskOption::overwrite)
                .ifPresent(resultComponent::addOption);
    }

    private void loadOptions(Consumer<List<InputMaskOption>> setter, Element element) {
        Element optionsElement = element.element("options");
        if (optionsElement == null) {
            return;
        }

        ArrayList<InputMaskOption> optionsList = new ArrayList<>();
        for (Element option : optionsElement.elements("option")) {
            optionsList.add(loadOption(option));
        }

        setter.accept(optionsList);
    }

    private InputMaskOption loadOption(Element element) {
        String key = element.attributeValue("key");

        List<InputMaskOption> options = new ArrayList<>();
        loadOptions(options::addAll, element);

        if (!options.isEmpty()) {
            return InputMaskOption.option(key, options);
        }

        String value = element.attributeValue("value");
        return InputMaskOption.option(key, value);
    }

    private PrefixSuffixLoaderSupport prefixSuffixLoaderSupport() {
        if (prefixSuffixLoaderSupport == null) {
            prefixSuffixLoaderSupport = applicationContext.getBean(PrefixSuffixLoaderSupport.class, context);
        }
        return prefixSuffixLoaderSupport;
    }
}
