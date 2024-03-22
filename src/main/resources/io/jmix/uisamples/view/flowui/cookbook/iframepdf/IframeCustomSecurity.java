package io.jmix.uisamples.view.flowui.cookbook.iframepdf;

import io.jmix.securityflowui.FlowuiSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class IframeCustomSecurity extends FlowuiSecurityConfiguration {

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
