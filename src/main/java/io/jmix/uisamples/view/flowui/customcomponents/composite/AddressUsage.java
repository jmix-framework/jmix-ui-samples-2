package io.jmix.uisamples.view.flowui.customcomponents.composite;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.component.addresscomponent.AddressComponent;
import io.jmix.uisamples.entity.Address;
import io.jmix.uisamples.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("composite-component")
@ViewDescriptor("address-usage.xml")
public class AddressUsage extends StandardView {

    @ViewComponent
    protected MessageBundle messageBundle;
    @ViewComponent
    protected InstanceContainer<Address> addressDc;
    @ViewComponent
    protected InstanceContainer<Employee> employeeDc;
    @ViewComponent
    protected JmixTabSheet tabSheet;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        initAddress();
    }

    protected void initAddress() {
        AddressComponent addressComponent = uiComponents.create(AddressComponent.class);
        addressComponent.setDataContainer(addressDc);

        tabSheet.add(messageBundle.getMessage("programmaticTab.label"), addressComponent);
    }

    @Subscribe("employeeSelect")
    protected void onEmployeeSelectValueChange(ComponentValueChangeEvent<JmixSelect<Employee>, Employee> event) {
        employeeDc.setItem(event.getValue());
    }
}
