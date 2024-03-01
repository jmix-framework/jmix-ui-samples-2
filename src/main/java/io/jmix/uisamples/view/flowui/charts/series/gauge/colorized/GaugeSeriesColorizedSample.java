package io.jmix.uisamples.view.flowui.charts.series.gauge.colorized;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.kit.component.model.series.GaugeSeries;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.view.*;

import java.util.Random;

@ViewController("gauge-series-colorized")
@ViewDescriptor("gauge-series-colorized.xml")
public class GaugeSeriesColorizedSample extends StandardView {

    @ViewComponent
    protected Chart chart;

    protected Random random = new Random();

    @Subscribe("timer")
    protected void onTimerTick(Timer.TimerActionEvent event) {
        GaugeSeries gaugeSeries = chart.getSeries("gaugeSeries");

        gaugeSeries.setData(
                new GaugeSeries.DataItem()
                        .withValue((double) random.nextInt(0, 100))
        );
    }
}
