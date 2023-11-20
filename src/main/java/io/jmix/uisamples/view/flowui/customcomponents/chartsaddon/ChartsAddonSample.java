package io.jmix.uisamples.view.flowui.customcomponents.chartsaddon;

import com.storedobject.chart.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tab;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.view.*;

import java.util.Random;

@ViewController("charts-addon")
@ViewDescriptor("charts-addon.xml")
public class ChartsAddonSample extends StandardView {

    @ViewComponent
    protected JmixTabSheet tabSheet;

    @Subscribe
    protected void onInit(InitEvent event) {
        Component lineChart = createLineChart();

        Tab tab = new Tab("Programmatically added line chart");
        tab.setId("lineChart");

        tabSheet.add(tab, lineChart);
    }

    protected Component createLineChart() {
        SOChart rootChart = new SOChart();
        rootChart.setWidthFull();
        rootChart.setMinHeight("30em");

        Random random = new Random();
        Data xValues = new Data();
        Data yValues = new Data();

        for (int x = 0; x < 40; x++) {
            xValues.add(x);
            yValues.add(random.nextDouble());
        }

        xValues.setName("Time per feature per day");
        yValues.setName("Percentage");

        LineChart lineChart = new LineChart(xValues, yValues);
        lineChart.setName("Percentage of time spent on a feature");

        XAxis xAxis = new XAxis(DataType.NUMBER);
        YAxis yAxis = new YAxis(DataType.NUMBER);
        RectangularCoordinate rc = new RectangularCoordinate(xAxis, yAxis);
        lineChart.plotOn(rc);

        rootChart.add(lineChart, new Title("Feature statistic"));

        return rootChart;
    }
}
