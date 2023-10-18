package io.jmix.uisamples.view.flowui.facets.timer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("timer-facet")
@ViewDescriptor("timer-facet.xml")
public class TimerFacetSample extends StandardView {

    @ViewComponent
    protected Timer timer;
    @ViewComponent
    protected Span timerIndicator;
    @ViewComponent
    protected JmixButton timerStartBtn;
    @ViewComponent
    protected JmixButton timerStopBtn;

    @Autowired
    protected Notifications notifications;

    protected int seconds = 0;
    protected int ticks = 0;

    @Subscribe("timerStartBtn")
    protected void onStartTimerClick(ClickEvent<JmixButton> event) {
        timer.start();

        timerIndicator.setText("Timer started");
        timerIndicator.getElement().getThemeList().clear();
        timerIndicator.getElement().getThemeList().add("badge pill success");

        notifications.create("Timer started")
                .withPosition(Notification.Position.BOTTOM_END)
                .withThemeVariant(NotificationVariant.LUMO_SUCCESS)
                .show();

        timerStartBtn.setEnabled(false);
        timerStopBtn.setEnabled(true);
    }

    @Subscribe("timerStopBtn")
    protected void onStopTimerClick(ClickEvent<JmixButton> event) {
        timer.stop();
        seconds = 0;

        timerIndicator.setText("Timer stopped");
        timerIndicator.getElement().getThemeList().clear();
        timerIndicator.getElement().getThemeList().add("badge pill error");

        timerStartBtn.setEnabled(true);
        timerStopBtn.setEnabled(false);
    }

    @Subscribe("timer")
    protected void onTimerTick(Timer.TimerActionEvent event) {
        ++ticks;
        seconds += event.getSource().getDelay() / 1000;

        notifications.create("Timer tick", seconds + " seconds passed")
                .withDuration(2750)
                .show();
    }

    @Subscribe("timer")
    protected void onTimerStop(Timer.TimerStopEvent event) {
        notifications.create("Timer stopped", "Number of ticks: " + ticks)
                .withThemeVariant(NotificationVariant.LUMO_PRIMARY)
                .withDuration(5000)
                .show();
        ticks = 0;
    }
}
