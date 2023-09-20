package io.jmix.uisamples.view.flowui.cookbook.compositecomponent;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.component.compositecomponent.AddressComponent;
import io.jmix.uisamples.entity.Address;
import io.jmix.uisamples.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("composite-component")
@ViewDescriptor("composite-component.xml")
public class CompositeComponentSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Address> addressDc;
    @ViewComponent
    protected InstanceContainer<Employee> employeeDc;
    @ViewComponent
    protected JmixTabSheet tabSheet;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected MessageBundle messageBundle;

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