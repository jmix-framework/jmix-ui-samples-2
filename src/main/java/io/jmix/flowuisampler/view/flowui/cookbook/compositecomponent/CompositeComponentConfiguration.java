package io.jmix.flowuisampler.view.flowui.cookbook.compositecomponent;

import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.flowuisampler.component.compositecomponent.AddressComponent;
import io.jmix.flowuisampler.component.compositecomponent.AddressComponentLoader;
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
