package io.jmix.uisamples.view.flowui.customcomponents.composite;

import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.uisamples.component.addresscomponent.AddressComponent;
import io.jmix.uisamples.component.addresscomponent.AddressComponentLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompositeComponentConfiguration {

    @Bean
    public ComponentRegistration address() {
        return ComponentRegistrationBuilder.create(AddressComponent.class)
                .withComponentLoader("address", AddressComponentLoader.class)
                .build();
    }
}
