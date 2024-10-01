package io.jmix.uisamples.view.flowui.pivottable.derivedproperties;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.core.Messages;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.DerivedProperties;
import io.jmix.pivottableflowui.kit.component.model.JsFunction;
import io.jmix.uisamples.entity.TemperatureData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@ViewController("pivottable-derived-properties")
@ViewDescriptor("pivottable-derived-properties.xml")
public class PivotTableDerivedProperties extends StandardView {

    @ViewComponent
    protected PivotTable<?> pivotTable;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        String function = """
                function(record) {
                    return $.pivotUtilities.numberFormat()(record.%s * 1.8 + 32);
                }
                """;
        DerivedProperties derivedProperties = new DerivedProperties();
        HashMap<String, JsFunction> derivedPropertiesFunctions = new HashMap<>();
        derivedPropertiesFunctions.put(
                messages.getMessage(getClass(), "temperature.fahrenheit"),
                new JsFunction(String.format(function, messages.getMessage(
                        TemperatureData.class, "TemperatureData.temperature"))));
        derivedProperties.setProperties(derivedPropertiesFunctions);
        pivotTable.setDerivedProperties(derivedProperties);
    }
}