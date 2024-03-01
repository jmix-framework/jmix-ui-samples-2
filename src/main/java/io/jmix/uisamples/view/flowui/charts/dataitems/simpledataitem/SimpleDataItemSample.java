package io.jmix.uisamples.view.flowui.charts.dataitems.simpledataitem;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

@ViewController("simple-data-item")
@ViewDescriptor("simple-data-item.xml")
public class SimpleDataItemSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<SimpleDataItem> chartItems = new ListChartItems<>(
                new SimpleDataItem(new ValueDescription(75, "Sky")),
                new SimpleDataItem(new ValueDescription(7, "Shady side of pyramid")),
                new SimpleDataItem(new ValueDescription(18, "Sunny side of pyramid"))
        );

        chart.withDataSet(
                new DataSet().withSource(
                        new DataSet.Source<SimpleDataItem>()
                                .withDataProvider(chartItems)
                                .withCategoryField("description")
                                .withValueField("value")
                )
        );
    }

    public static class ValueDescription {
        protected Integer value;
        protected String description;

        public ValueDescription(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        public Integer getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }
}
