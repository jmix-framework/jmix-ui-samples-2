package io.jmix.uisamples.view.flowui.charts.series.line.multipleaxes;

import io.jmix.core.LoadContext;
import io.jmix.core.Metadata;
import io.jmix.core.TimeSource;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.DateValueVolume;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ViewController("multiple-axes-line-series")
@ViewDescriptor("multiple-axes-line-series.xml")
public class MultipleAxesLineSeriesSample extends StandardView {

    protected static final int DAYS_COUNT = 20;

    @Autowired
    protected Metadata metadata;
    @Autowired
    protected TimeSource timeSource;

    protected Random random = new Random();

    @Install(to = "dateValueVolumeDl", target = Target.DATA_LOADER)
    protected List<DateValueVolume> coordinateDlLoadDelegate(LoadContext<DateValueVolume> loadContext) {
        List<DateValueVolume> items = new ArrayList<>();
        Date startDate = DateUtils.addDays(timeSource.currentTimestamp(), -DAYS_COUNT);
        for (int i = 0; i < DAYS_COUNT; i++) {
            items.add(generateDateValueVolume(DateUtils.addDays(startDate, i), i));
        }

        return items;
    }

    protected DateValueVolume generateDateValueVolume(Date date, int i) {
        Long value = Math.round(random.nextDouble() * (20 + i)) + 20 + i;
        Long volume = Math.round(random.nextDouble() * (20 + i)) + i;

        return dateValueVolume(date, value, volume);
    }

    protected DateValueVolume dateValueVolume(Date date, Long value, Long volume) {
        DateValueVolume dateValueVolume = metadata.create(DateValueVolume.class);
        dateValueVolume.setDate(date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        dateValueVolume.setValue(value);
        dateValueVolume.setVolume(volume);
        return dateValueVolume;
    }
}
