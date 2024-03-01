package io.jmix.uisamples.view.flowui.charts.series.radar.simple;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.util.Map;

@ViewController("radar-series-simple")
@ViewDescriptor("radar-series-simple.xml")
public class RadarSeriesSimpleSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<MapDataItem> items = new ListChartItems<>(
                new MapDataItem(
                        Map.of(
                                "Russia", 256.9,
                                "Ireland", 131.1,
                                "Germany", 115.8,
                                "Australia", 109.9,
                                "Austria", 108.3,
                                "UK", 65.0,
                                "Belgium", 40.0
                        )
                )
        );

        chart.setDataSet(
                new DataSet()
                        .withSource(
                                new DataSet.Source<MapDataItem>()
                                        .withDataProvider(items)
                                        .withValueFields(
                                                "Russia",
                                                "Ireland",
                                                "Germany",
                                                "Australia",
                                                "Austria",
                                                "UK",
                                                "Belgium"
                                        )
                        )
        );
    }
}
