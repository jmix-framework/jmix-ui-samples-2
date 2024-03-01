package io.jmix.uisamples.view.flowui.charts.other.nativejson;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.flowui.view.*;

@ViewController("chart-native-json")
@ViewDescriptor("chart-native-json.xml")
public class ChartNativeJsonSample extends StandardView {

    protected static final String SOURCE_ECHARTS_EXAMPLE_LINK =
            "https://echarts.apache.org/examples/en/editor.html?c=polar-roundCap&reset=1&edit=1";

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        chart.getTitle().setSublink(SOURCE_ECHARTS_EXAMPLE_LINK);
    }
}
