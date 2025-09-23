package io.jmix.uisamples.view.flowui.containers.board.chart;

import com.vaadin.flow.component.html.Span;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionContainer.CollectionChangeEvent;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.TransportCount;

@ViewController("board-chart")
@ViewDescriptor("board-chart.xml")
public class BoardChartSample extends StandardView {

    @ViewComponent
    private CollectionContainer<TransportCount> transportDc;

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
    private Span totalPercentage;

    private TransportCountHolder transportCountHolder;

    @Subscribe(id = "transportDc", target = Target.DATA_CONTAINER)
    public void onTransportDcCollectionChangeChange(CollectionChangeEvent<TransportCount> event) {
        recalculateCells();
    }

    private void recalculateCells() {
        transportCountHolder = new TransportCountHolder();

        transportDc.getItems()
                .forEach(transportCountHolder::add);

        carsCount.setText(String.valueOf(transportCountHolder.getCars()));
        motorcyclesCount.setText(String.valueOf(transportCountHolder.getMotorcycles()));
        bicyclesCount.setText(String.valueOf(transportCountHolder.getBicycles()));
        totalCount.setText(String.valueOf(transportCountHolder.getTotal()));

        carsPercentage.setText(transportCountHolder.getCarsPercentage());
        motorcyclesPercentage.setText(transportCountHolder.getMotorcyclesPercentage());
        bicyclesPercentage.setText(transportCountHolder.getBicyclesPercentage());
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

        public String getCarsPercentage() {
            return cars * 100 / getTotal() + "%";
        }

        public Integer getMotorcycles() {
            return motorcycles;
        }

        public String getMotorcyclesPercentage() {
            return motorcycles * 100 / getTotal() + "%";
        }

        public Integer getBicycles() {
            return bicycles;
        }

        public String getBicyclesPercentage() {
            return bicycles * 100 / getTotal() + "%";
        }

        public Integer getTotal() {
            return cars + motorcycles + bicycles;
        }
    }
}
