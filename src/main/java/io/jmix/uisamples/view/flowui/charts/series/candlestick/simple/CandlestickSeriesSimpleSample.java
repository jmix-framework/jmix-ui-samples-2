package io.jmix.uisamples.view.flowui.charts.series.candlestick.simple;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.time.LocalDate;

@ViewController("candlestick-series-simple")
@ViewDescriptor("candlestick-series-simple.xml")
public class CandlestickSeriesSimpleSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        LocalDate now = LocalDate.now();
        ListChartItems<SimpleDataItem> items = new ListChartItems<>(
                new SimpleDataItem(new CandlestickData(now, 20.41, 34.5, 10.3, 38.5)),
                new SimpleDataItem(new CandlestickData(now.plusDays(1), 40.12, 35.32, 30.19, 50.93)),
                new SimpleDataItem(new CandlestickData(now.plusDays(2), 31.41, 38.45, 33.74, 44.85)),
                new SimpleDataItem(new CandlestickData(now.plusDays(3), 38.94, 15.3, 5.24, 42.8))
        );

        chart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<SimpleDataItem>()
                                .withDataProvider(items)
                                .withCategoryField("date")
                                .withValueFields("open", "close", "low", "high")
                )
        );
    }

    public static class CandlestickData {

        protected LocalDate date;
        protected Double open;
        protected Double close;
        protected Double low;
        protected Double high;

        public CandlestickData(LocalDate date, Double open, Double close, Double low, Double high) {
            this.date = date;
            this.open = open;
            this.close = close;
            this.low = low;
            this.high = high;
        }

        public LocalDate getDate() {
            return date;
        }

        public Double getOpen() {
            return open;
        }

        public Double getClose() {
            return close;
        }

        public Double getLow() {
            return low;
        }

        public Double getHigh() {
            return high;
        }
    }
}
