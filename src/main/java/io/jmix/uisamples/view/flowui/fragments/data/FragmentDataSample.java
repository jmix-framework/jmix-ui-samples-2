package io.jmix.uisamples.view.flowui.fragments.data;

import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Address;
import io.jmix.uisamples.entity.Employee;

@ViewController("fragment-data")
@ViewDescriptor("fragment-data.xml")
public class FragmentDataSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Address> addressDc;

    @Subscribe(id = "employeesDc", target = Target.DATA_CONTAINER)
    public void onEmployeesDcItemChange(final InstanceContainer.ItemChangeEvent<Employee> event) {
        if (event.getItem() != null) {
            addressDc.setItem(event.getItem().getAddress());
        }
    }
}
