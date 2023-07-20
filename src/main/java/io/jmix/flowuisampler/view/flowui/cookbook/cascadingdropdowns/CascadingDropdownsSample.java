package io.jmix.flowuisampler.view.flowui.cookbook.cascadingdropdowns;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Moon;
import io.jmix.flowuisampler.entity.Planet;

@ViewController("cascading-dropdowns")
@ViewDescriptor("cascading-dropdowns.xml")
public class CascadingDropdownsSample extends StandardView {

    @ViewComponent
    protected EntityComboBox<Moon> moonsComboBox;

    @Subscribe("planetsComboBox")
    protected void onPlanetsComboBoxValueChange(ComponentValueChangeEvent<EntityComboBox<Planet>, Planet> event) {
        moonsComboBox.clear();
    }
}
