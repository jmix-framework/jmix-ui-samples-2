package io.jmix.uisamples.view.flowui.charts.series.candlestick.volume;

import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.bean.StockDataGenerator;
import io.jmix.uisamples.entity.StockData;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("candlestick-series-volume")
@ViewDescriptor("candlestick-series-volume.xml")
public class CandlestickSeriesVolumeSample extends StandardView {

    @ViewComponent
    protected CollectionContainer<StockData> stockDataDc;

    @Autowired
    protected StockDataGenerator stockDataGenerator;

    @Subscribe
    protected void onInit(InitEvent event) {
        stockDataGenerator.generateData(stockDataDc);
    }
}
