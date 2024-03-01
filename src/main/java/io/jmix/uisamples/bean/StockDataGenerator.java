package io.jmix.uisamples.bean;

import io.jmix.core.Metadata;
import io.jmix.core.TimeSource;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.uisamples.entity.StockData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component("uisamples_StockDataGenerator")
public class StockDataGenerator {

    protected static final int DAYS_COUNT = 100;

    protected Metadata metadata;
    protected TimeSource timeSource;

    protected final Random random = new Random();

    public StockDataGenerator(TimeSource timeSource, Metadata metadata) {
        this.timeSource = timeSource;
        this.metadata = metadata;
    }

    public void generateData(CollectionContainer<StockData> stockChartDc) {
        List<StockData> items = new ArrayList<>();
        for (int i = 0; i < DAYS_COUNT; i++) {
            items.add(generateStockData(LocalDate.now().plusDays(i), i));
        }
        stockChartDc.setItems(items);
    }

    protected StockData generateStockData(LocalDate date, int i) {
        long open = Math.round(random.nextDouble() * 30 + 100);
        long close = open + Math.round(random.nextDouble() * 15 - random.nextDouble() * 10);

        long low = Math.min(open, close) - Math.round(random.nextDouble() * 5);
        long high = Math.max(open, close) + Math.round(random.nextDouble() * 5);

        long volume = Math.round(random.nextDouble() * (1000 + i)) + 100 + i;
        long value = Math.round(random.nextDouble() * 30 + 100);

        return stockData(date, value, volume, open, close, high, low);
    }

    protected StockData stockData(LocalDate date, Long value, Long volume,
                                  Long open, Long close, Long high, Long low) {
        StockData dateValueVolume = metadata.create(StockData.class);
        dateValueVolume.setDate(date);
        dateValueVolume.setValue(value);
        dateValueVolume.setVolume(volume);
        dateValueVolume.setOpen(open);
        dateValueVolume.setClose(close);
        dateValueVolume.setHigh(high);
        dateValueVolume.setLow(low);
        return dateValueVolume;
    }
}
