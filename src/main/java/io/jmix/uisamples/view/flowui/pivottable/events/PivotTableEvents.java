package io.jmix.uisamples.view.flowui.pivottable.events;

import io.jmix.core.Messages;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.AggregationMode;
import io.jmix.pivottableflowui.kit.component.model.Renderer;
import io.jmix.uisamples.entity.TemperatureData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@ViewController("pivottable-events")
@ViewDescriptor("pivottable-events.xml")
public class PivotTableEvents extends StandardView {

    @ViewComponent
    protected PivotTable<TemperatureData> pivotTable;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        pivotTable.addCellClickListener(e -> {
            List<TemperatureData> items = e.getDetail().getItems();
            StringBuilder notificationMessage = new StringBuilder();
            items.forEach(temperatureData -> {
                notificationMessage.append("Entity id: ")
                        .append(temperatureData.getId())
                        .append(", Celsius: ")
                        .append(temperatureData.getTemperature())
                        .append("\n");
            });
            notificationMessage.deleteCharAt(notificationMessage.length() - 1);
            notifications.show("Cell click event (items in the cell)", notificationMessage.toString());
        });

        pivotTable.addRefreshListener(e -> {
            notifications.show("Refresh event",
                    "Renderer: " + getRendererMessage(e.getDetail().getRenderer()) +
                    "\nAggregation: " + getAggregatorMessage(e.getDetail().getAggregationMode()) +
                    "\nRows: " + getLocalizedProperties(e.getDetail().getRows()) +
                    "\nColumns: " + getLocalizedProperties(e.getDetail().getColumns()));
        });
    }

    private String getRendererMessage(Renderer renderer) {
        return messages.getMessage("pivottable.renderer." + renderer.getId());
    }

    private String getAggregatorMessage(AggregationMode aggregationMode) {
        return messages.getMessage("pivottable.aggregator." +  aggregationMode.getId());
    }

    private String getLocalizedProperties(List<String> properties) {
        if (properties == null || properties.isEmpty()) {
            return "[Empty]";
        }
        return properties.stream()
                .map(p -> pivotTable.getProperties().get(p))
                .collect(Collectors.joining(", "));
    }
}