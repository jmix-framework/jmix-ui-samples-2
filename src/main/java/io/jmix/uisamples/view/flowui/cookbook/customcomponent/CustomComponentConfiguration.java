package io.jmix.uisamples.view.flowui.cookbook.customcomponent;

import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.uisamples.component.customcomponent.Slider;
import io.jmix.uisamples.component.customcomponent.SliderLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomComponentConfiguration {

    @Bean
    public ComponentRegistration slider() {
        return ComponentRegistrationBuilder.create(Slider.class)
                .withComponentLoader("slider", SliderLoader.class)
                .build();
    }
}