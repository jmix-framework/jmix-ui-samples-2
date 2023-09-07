package io.jmix.flowuisampler.view.flowui.cookbook.customcomponent;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
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
    protected JmixTabSheet tabSheet;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

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
