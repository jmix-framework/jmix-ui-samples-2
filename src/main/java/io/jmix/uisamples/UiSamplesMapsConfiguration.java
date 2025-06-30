package io.jmix.uisamples;

import io.jmix.mapsflowui.component.projection.GeoMapProjectionRegistration;
import io.jmix.mapsflowui.component.projection.ProjectionRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UiSamplesMapsConfiguration {

    @Bean
    public ProjectionRegistration epsg27700() {
        return new GeoMapProjectionRegistration("EPSG:27700",
                       """
                        +proj=tmerc +lat_0=49 +lon_0=-2 +k=0.9996012717 +x_0=400000 +y_0=-100000 +ellps=airy
                        +towgs84=446.448,-125.157,542.06,0.15,0.247,0.842,-20.489
                        +units=m +no_defs
                        """);
    }
}
