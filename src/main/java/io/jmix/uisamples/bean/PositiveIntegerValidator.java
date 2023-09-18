package io.jmix.uisamples.bean;

import io.jmix.core.common.util.ParamsMap;
import io.jmix.flowui.component.validation.AbstractValidator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("uisamples_PositiveIntegerValidator")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PositiveIntegerValidator extends AbstractValidator<Integer> {

    @Override
    public void accept(Integer value) {
        if (value != null) {
            if (value <= 0) {
                fireValidationException(
                        messages.getMessage("validation.constraints.positive"),
                        ParamsMap.of("value", value)
                );
            }
        }
    }
}
