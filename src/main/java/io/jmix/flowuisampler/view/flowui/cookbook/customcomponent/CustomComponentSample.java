package io.jmix.flowuisampler.view.flowui.cookbook.customcomponent;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.H4;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.customcomponent.AddressComponent;
import io.jmix.flowuisampler.entity.Address;
import io.jmix.flowuisampler.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("custom-component")
@ViewDescriptor("custom-component.xml")
public class CustomComponentSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Address> addressDc;
    @ViewComponent
    protected InstanceContainer<Employee> employeeDc;

    @ViewComponent
    protected AddressComponent address1;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        initAddress();
    }

    protected void initAddress() {
        H4 h4 = uiComponents.create(H4.class);
        h4.setText("Programmatically defined component");

        AddressComponent addressComponent = uiComponents.create(AddressComponent.class);
        addressComponent.setDataContainer(addressDc);

        getContent().add(h4, addressComponent);
    }

    @Subscribe("employeeSelect")
    protected void onEmployeeSelectValueChange(ComponentValueChangeEvent<JmixSelect<Employee>, Employee> event) {
        employeeDc.setItem(event.getValue());
    }

    @Subscribe
    protected void onReady(ReadyEvent event) {
        address1.setVisible(true);
    }
}
