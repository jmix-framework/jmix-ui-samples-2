package io.jmix.uisamples.view.flowui.charts.series.scatter.effect;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

@ViewController("effect-scatter-series")
@ViewDescriptor("effect-scatter-series.xml")
public class EffectScatterSeriesSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<SimpleDataItem> items = new ListChartItems<>(
                new SimpleDataItem(new CountryStats(28604, 77D, 17096869, "Australia")),
                new SimpleDataItem(new CountryStats(31163, 77.4, 27662440, "Canada")),
                new SimpleDataItem(new CountryStats(1516, 68D, 1154605773, "China")),
                new SimpleDataItem(new CountryStats(13670, 74.7, 10582082, "Cuba")),
                new SimpleDataItem(new CountryStats(28599, 75D, 4986705, "Finland")),
                new SimpleDataItem(new CountryStats(29476, 77.1, 56943299, "France")),
                new SimpleDataItem(new CountryStats(31476, 75.4, 78958237, "Germany")),
                new SimpleDataItem(new CountryStats(28666, 78.1, 254830, "Iceland")),
                new SimpleDataItem(new CountryStats(1777, 57.7, 870601776, "India")),
                new SimpleDataItem(new CountryStats(29550, 79.1, 122249285, "Japan")),
                new SimpleDataItem(new CountryStats(2076, 67.9, 20194354, "North Korea")),
                new SimpleDataItem(new CountryStats(12087, 72D, 42972254, "South Korea")),
                new SimpleDataItem(new CountryStats(24021, 75.4, 3397534, "New Zealand")),
                new SimpleDataItem(new CountryStats(43296, 76.8, 4240375, "Norway")),
                new SimpleDataItem(new CountryStats(10088, 70.8, 38195258, "Poland")),
                new SimpleDataItem(new CountryStats(19349, 69.6, 147568552, "Russia")),
                new SimpleDataItem(new CountryStats(10670, 67.3, 53994605, "Turkey")),
                new SimpleDataItem(new CountryStats(26424, 75.7, 57110117, "United Kingdom")),
                new SimpleDataItem(new CountryStats(37062, 75.4, 252847810, "United States"))
        );

        chart.setDataSet(
                new DataSet()
                        .withSource(
                                new DataSet.Source<SimpleDataItem>()
                                        .withDataProvider(items)
                                        .withValueFields("count", "percentage", "amount", "name")
                        )
        );
    }

    public static class CountryStats {

        protected Integer count;
        protected Double percentage;
        protected Integer amount;
        protected String name;

        public CountryStats(Integer count, Double percentage, Integer amount, String name) {
            this.count = count;
            this.percentage = percentage;
            this.amount = amount;
            this.name = name;
        }

        public Integer getCount() {
            return count;
        }

        public Double getPercentage() {
            return percentage;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getName() {
            return name;
        }
    }
}
