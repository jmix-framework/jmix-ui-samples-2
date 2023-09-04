package io.jmix.flowuisampler.view.flowui.cookbook.customcomponent;

import io.jmix.flowui.UiComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.customcomponent.AddressComponent;
import io.jmix.flowuisampler.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-component")
@ViewDescriptor("custom-component.xml")
public class CustomComponentSample extends StandardView {
    @ViewComponent
    protected InstanceContainer<Address> addressDc;
    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        AddressComponent component = uiComponents.create(AddressComponent.class);
        component.setDataContainer(addressDc);
        getContent().add(component);
    }
}
