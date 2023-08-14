package io.jmix.flowuisampler.component.customer.sample;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.customer.CustomComponent;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "SampleScreen")
@ViewController("SampleScreen")
@ViewDescriptor("sample-screen.xml")
public class SampleScreen extends StandardView {
    @ViewComponent
    protected CollectionContainer<Customer> customerDc;
    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        CustomComponent component = uiComponents.create(CustomComponent.class);
        component.setConfigurationDc(customerDc);
        getContent().add(component);
    }
}
