package io.jmix.flowuisampler.view.flowui.cookbook.custom;

import io.jmix.flowui.UiComponents;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.customer.CustomComponent;
import io.jmix.flowuisampler.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-screen")
@ViewDescriptor("custom-screen.xml")
public class CustomScreen extends StandardView {
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
