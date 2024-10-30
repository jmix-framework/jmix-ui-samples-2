package io.jmix.uisamples.view.flowui.pivottable.derivedproperties;

import io.jmix.core.Messages;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.DerivedProperties;
import io.jmix.pivottableflowui.kit.component.model.JsFunction;
import io.jmix.uisamples.entity.TemperatureData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@ViewController("pivottable-derived-properties")
@ViewDescriptor("pivottable-derived-properties.xml")
public class PivotTableDerivedProperties extends StandardView {

    @Autowired
    private Messages messages;
    @ViewComponent
    private MessageBundle messageBundle;
    @ViewComponent
    private PivotTable<TemperatureData> pivotTable;

    @Subscribe
    public void onInit(final InitEvent event) {
        String function = """
                function(record) {
                    return pivotTable.$jQuery.pivotUtilities.numberFormat()(record.%s * 1.8 + 32);
                }
                """;
        DerivedProperties derivedProperties = new DerivedProperties();
        derivedProperties.setProperties(Map.of(messageBundle.getMessage("temperature.fahrenheit"),
                new JsFunction(String.format(function, messages.getMessage(
                        TemperatureData.class, "TemperatureData.temperature")))));
        pivotTable.setDerivedProperties(derivedProperties);
    }
}