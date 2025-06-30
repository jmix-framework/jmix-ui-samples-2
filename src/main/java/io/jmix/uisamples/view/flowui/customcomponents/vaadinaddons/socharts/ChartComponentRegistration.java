package io.jmix.uisamples.view.flowui.customcomponents.vaadinaddons.socharts;

import com.storedobject.chart.SOChart;
import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import io.jmix.uisamples.component.chart.ChartLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChartComponentRegistration {

    @Bean
    public ComponentRegistration soChart() {
        return ComponentRegistrationBuilder.create(SOChart.class)
                .withComponentLoader("sochart", ChartLoader.class)
                .build();
    }
}
