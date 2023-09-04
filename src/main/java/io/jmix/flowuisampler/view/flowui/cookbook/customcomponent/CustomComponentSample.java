package io.jmix.flowuisampler.view.flowui.cookbook.customcomponent;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
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

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        initAddress();
    }

    protected void initAddress() {
        VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
        verticalLayout.setPadding(false);
        verticalLayout.setWidth("AUTO");
        verticalLayout.addClassName(LumoUtility.AlignItems.CENTER);
        verticalLayout.addClassName(LumoUtility.JustifyContent.CENTER);

        H4 h4 = uiComponents.create(H4.class);
        h4.setText("Programmatically defined component");

        AddressComponent addressComponent = uiComponents.create(AddressComponent.class);
        addressComponent.setDataContainer(addressDc);

        verticalLayout.add(h4, addressComponent);

        getContent().add(verticalLayout);
    }

    @Subscribe("employeeSelect")
    protected void onEmployeeSelectValueChange(ComponentValueChangeEvent<JmixSelect<Employee>, Employee> event) {
        employeeDc.setItem(event.getValue());
    }
}
