package io.jmix.uisamples.view.flowui.cookbook.iframepdf;

import io.jmix.securityflowui.security.FlowuiVaadinWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class IframeCustomSecurity extends FlowuiVaadinWebSecurity {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.headers(headers ->
                headers.contentSecurityPolicy(
                        secPolicy -> secPolicy.policyDirectives("frame-ancestors demo.jmix.io")
                )
        );
    }
}
