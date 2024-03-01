package io.jmix.uisamples.view.flowui.charts.incrementalupdate.manualupdate;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.core.Metadata;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.TransportCount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@ViewController("manual-data-update")
@ViewDescriptor("manual-data-update.xml")
public class ManualDataUpdateSample extends StandardView {

    @Autowired
    protected Metadata metadata;

    @ViewComponent
    protected CollectionContainer<TransportCount> transportDc;
    @ViewComponent
    protected Chart chart;

    protected Integer baseYear = 2013;
    protected Random random = new Random();

    @Subscribe("addDataBtn")
    protected void onAddDataBtnClick(ClickEvent<JmixButton> event) {
        addData();
    }

    @Subscribe("removeDataBtn")
    protected void onRemoveDataBtnClick(ClickEvent<JmixButton> event) {
        removeData();
    }

    protected void addData() {
        TransportCount transportCount = metadata.create(TransportCount.class);

        transportCount.setYear(baseYear++);
        transportCount.setCars(random.nextInt(100, 1700));
        transportCount.setMotorcycles(random.nextInt(200, 700));
        transportCount.setBicycles(random.nextInt(5, 100));

        transportDc.getMutableItems().add(transportCount);
    }

    protected void removeData() {
        if (!transportDc.getItems().isEmpty()) {
            transportDc.getMutableItems().remove(0);
        }
    }
}
