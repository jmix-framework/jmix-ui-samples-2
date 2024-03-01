package io.jmix.uisamples.view.flowui.charts.incrementalupdate.automaticupdate;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.core.Metadata;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.TransportCount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@ViewController("automatic-data-update")
@ViewDescriptor("automatic-data-update.xml")
public class AutomaticDataUpdateSample extends StandardView {

    @Autowired
    protected Metadata metadata;

    @ViewComponent
    protected CollectionContainer<TransportCount> transportDc;
    @ViewComponent
    protected Chart chart;
    @ViewComponent
    protected Timer timer;
    @ViewComponent
    protected JmixButton startStopDataGen;

    protected Integer baseYear = 2013;
    protected Random random = new Random();
    protected boolean timerStarted;

    @Subscribe
    protected void onInit(InitEvent event) {
        prepareTimerButtonToStart();
    }

    @Subscribe(id = "startStopDataGen", subject = "clickListener")
    public void onStartStopDataGenClick(final ClickEvent<JmixButton> event) {
        if (timerStarted) {
            timer.stop();
            prepareTimerButtonToStart();
        } else {
            timer.start();
            prepareTimerButtonToStop();
        }

        timerStarted = !timerStarted;
    }

    @Subscribe("timer")
    protected void onTimerTick(Timer.TimerActionEvent event) {
        removeData();
        addData();
    }

    protected void prepareTimerButtonToStart() {
        startStopDataGen.setText("Start");
        startStopDataGen.setIcon(VaadinIcon.PLAY.create());
        startStopDataGen.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        startStopDataGen.removeThemeVariants(ButtonVariant.LUMO_ERROR);
    }

    protected void prepareTimerButtonToStop() {
        startStopDataGen.setText("Stop");
        startStopDataGen.setIcon(VaadinIcon.PAUSE.create());
        startStopDataGen.addThemeVariants(ButtonVariant.LUMO_ERROR);
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
        transportDc.getMutableItems().remove(0);
    }
}
