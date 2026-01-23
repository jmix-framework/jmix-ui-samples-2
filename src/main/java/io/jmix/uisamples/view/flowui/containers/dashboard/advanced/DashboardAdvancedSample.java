package io.jmix.uisamples.view.flowui.containers.dashboard.advanced;

import com.vaadin.flow.component.html.Span;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.component.model.series.GaugeSeries;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.TransportCount;

@ViewController("dashboard-advanced")
@ViewDescriptor("dashboard-advanced.xml")
public class DashboardAdvancedSample extends StandardView {

    public static int PLAN = 40000;

    @ViewComponent
    private Chart planChart;
    @ViewComponent
    private Chart percentagesChart;

    @ViewComponent
    private Span carsCount;
    @ViewComponent
    private Span motorcyclesCount;
    @ViewComponent
    private Span bicyclesCount;
    @ViewComponent
    private Span totalCount;
    @ViewComponent
    private Span carsPercentage;
    @ViewComponent
    private Span motorcyclesPercentage;
    @ViewComponent
    private Span bicyclesPercentage;

    @ViewComponent
    private CollectionContainer<TransportCount> transportDc;


    @Subscribe(id = "transportDc", target = Target.DATA_CONTAINER)
    public void onTransportDcCollectionChangeChange(CollectionContainer.CollectionChangeEvent<TransportCount> event) {
        recalculateCells();
    }

    private void recalculateCells() {
        TransportCountHolder transportCountHolder = new TransportCountHolder();

        transportDc.getItems()
                .forEach(transportCountHolder::add);

        GaugeSeries gaugeSeries = planChart.getSeries("gaugeSeries");
        gaugeSeries.setData(
                new GaugeSeries.DataItem()
                        .withValue((double) (transportCountHolder.getTotal() * 100 / PLAN))
        );

        percentagesChart.getDataSet()
                .setSource(new DataSet.Source<>()
                        .withDataProvider(new ListChartItems<>(
                                new SimpleDataItem(
                                        new ValueDescription(transportCountHolder.getCarsPercentage(), "Cars")
                                ),
                                new SimpleDataItem(
                                        new ValueDescription(transportCountHolder.getMotorcyclesPercentage(), "Motorcycles")
                                ),
                                new SimpleDataItem(
                                        new ValueDescription(transportCountHolder.getBicyclesPercentage(), "Bicycles"
                                        ))
                        ))
                        .withCategoryField("description")
                        .withValueField("value")
                );

        carsCount.setText(String.valueOf(transportCountHolder.getCars()));
        motorcyclesCount.setText(String.valueOf(transportCountHolder.getMotorcycles()));
        bicyclesCount.setText(String.valueOf(transportCountHolder.getBicycles()));
        totalCount.setText(String.valueOf(transportCountHolder.getTotal()));

        carsPercentage.setText(transportCountHolder.getCarsPercentage().intValue() + "%");
        motorcyclesPercentage.setText(transportCountHolder.getMotorcyclesPercentage().intValue() + "%");
        bicyclesPercentage.setText(transportCountHolder.getBicyclesPercentage().intValue() + "%");
    }

    private static class TransportCountHolder {

        private Integer cars = 0;
        private Integer motorcycles = 0;
        private Integer bicycles = 0;

        public void add(TransportCount transportCount) {
            this.cars += transportCount.getCars();
            this.motorcycles += transportCount.getMotorcycles();
            this.bicycles += transportCount.getBicycles();
        }

        public Integer getCars() {
            return cars;
        }

        public Double getCarsPercentage() {
            return cars * 100.0 / getTotal();
        }

        public Integer getMotorcycles() {
            return motorcycles;
        }

        public Double getMotorcyclesPercentage() {
            return motorcycles * 100.0 / getTotal();
        }

        public Integer getBicycles() {
            return bicycles;
        }

        public Double getBicyclesPercentage() {
            return bicycles * 100.0 / getTotal();
        }

        public Integer getTotal() {
            return cars + motorcycles + bicycles;
        }
    }

    public static final class ValueDescription {

        private final Double value;
        private final String description;

        private ValueDescription(Double value, String description) {
            this.value = value;
            this.description = description;
        }

        public Double getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }
}
