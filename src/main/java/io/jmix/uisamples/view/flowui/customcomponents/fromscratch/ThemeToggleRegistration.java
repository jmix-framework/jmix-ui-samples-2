package io.jmix.uisamples.view.flowui.customcomponents.fromscratch;

import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.uisamples.component.themetoggle.ThemeToggle;
import io.jmix.uisamples.component.themetoggle.ThemeToggleLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThemeToggleRegistration {

    @Bean
    public ComponentRegistration themeToggle() {
        return ComponentRegistrationBuilder.create(ThemeToggle.class)
                .withComponentLoader("themeToggle", ThemeToggleLoader.class)
                .build();
    }
}
