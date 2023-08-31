package io.jmix.flowuisampler.view.flowui.cookbook.address;

import io.jmix.flowui.UiComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.address.AddressComponent;
import io.jmix.flowuisampler.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("address-screen")
@ViewDescriptor("address-screen.xml")
public class AddressScreen extends StandardView {
    @ViewComponent
    protected InstanceContainer<Address> addressDc;
    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        AddressComponent component = uiComponents.create(AddressComponent.class);
        component.setConfigurationDc(addressDc);
        getContent().add(component);
    }
}
