package io.jmix.uisamples.view.flowui.charts.series.radar.multiple;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.util.Map;

@ViewController("radar-series-multiple")
@ViewDescriptor("radar-series-multiple.xml")
public class RadarSeriesMultipleSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<MapDataItem> items = new ListChartItems<>(
                new MapDataItem(
                        Map.of(
                                "Sales", 4200,
                                "Administration", 3000,
                                "Information Technology", 20000,
                                "Customer Support", 35000,
                                "Development", 50000,
                                "Marketing", 18000
                        )
                ),
                new MapDataItem(
                        Map.of(
                                "Sales", 5000,
                                "Administration", 14000,
                                "Information Technology", 28000,
                                "Customer Support", 26000,
                                "Development", 42000,
                                "Marketing", 21000
                        )
                )
        );

        chart.setDataSet(
                new DataSet()
                        .withSource(
                                new DataSet.Source<MapDataItem>()
                                        .withDataProvider(items)
                                        .withValueFields(
                                                "Sales",
                                                "Administration",
                                                "Information Technology",
                                                "Customer Support",
                                                "Development",
                                                "Marketing"
                                        )
                        )
        );
    }
}
