package io.jmix.uisamples.view.flowui.charts.series.funnel.alignment;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.util.Map;

@ViewController("funnel-alignment")
@ViewDescriptor("funnel-alignment.xml")
public class FunnelAlignmentSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<MapDataItem> items = new ListChartItems<>(
                new MapDataItem(Map.of("name", "Product A", "value", 100)),
                new MapDataItem(Map.of("name", "Product B", "value", 80)),
                new MapDataItem(Map.of("name", "Product C", "value", 60)),
                new MapDataItem(Map.of("name", "Product D", "value", 30)),
                new MapDataItem(Map.of("name", "Product E", "value", 10))
        );

        chart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<MapDataItem>()
                                .withDataProvider(items)
                                .withCategoryField("name")
                                .withValueFields("value")
                )
        );
    }
}
