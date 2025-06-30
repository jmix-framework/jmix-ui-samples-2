package io.jmix.uisamples.view.flowui.customcomponents.vaadinaddons.inputmaskadvanced;

import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.uisamples.component.maskfield.MaskField;
import io.jmix.uisamples.component.maskfield.MaskFieldLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaskFieldRegistration {

    @Bean
    public ComponentRegistration maskField() {
        return ComponentRegistrationBuilder.create(MaskField.class)
                .withComponentLoader("maskField", MaskFieldLoader.class)
                .build();
    }
}
