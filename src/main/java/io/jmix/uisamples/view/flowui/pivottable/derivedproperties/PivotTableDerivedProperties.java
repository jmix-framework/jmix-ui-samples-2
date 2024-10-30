package io.jmix.uisamples.view.flowui.pivottable.derivedproperties;

import io.jmix.core.MessageTools;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.DerivedProperties;
import io.jmix.pivottableflowui.kit.component.model.JsFunction;
import io.jmix.uisamples.entity.TemperatureData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@ViewController("pivot-table-derived-properties")
@ViewDescriptor("pivot-table-derived-properties.xml")
public class PivotTableDerivedProperties extends StandardView {

    @ViewComponent
    private PivotTable<TemperatureData> pivotTable;
    @ViewComponent
    private MessageBundle messageBundle;

    @Autowired
    private Metadata metadata;
    @Autowired
    private MessageTools messageTools;

    @Subscribe
    public void onInit(final InitEvent event) {
        MetaClass metaClass = metadata.getClass(TemperatureData.class);
        String temperatureCaption = messageTools.getPropertyCaption(metaClass, "temperature");

        String formattedFunction = """
                function(record) {
                    return pivotTable.$jQuery.pivotUtilities.numberFormat()(record.%s * 1.8 + 32);
                }
                """.formatted(temperatureCaption);

        DerivedProperties derivedProperties = new DerivedProperties();
        Map<String, JsFunction> propertiesMap = Map.of(
                messageBundle.getMessage("temperature.fahrenheit"),
                new JsFunction(formattedFunction)
        );

        derivedProperties.setProperties(propertiesMap);
        pivotTable.setDerivedProperties(derivedProperties);
    }
}
