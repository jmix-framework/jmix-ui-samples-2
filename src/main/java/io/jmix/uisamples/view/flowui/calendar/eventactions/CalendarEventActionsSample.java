package io.jmix.uisamples.view.flowui.calendar.eventactions;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.fullcalendarflowui.component.event.EventClickEvent;
import io.jmix.fullcalendarflowui.component.event.EventDropEvent;
import io.jmix.fullcalendarflowui.component.event.EventResizeEvent;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("calendar-event-actions")
@ViewDescriptor("calendar-event-actions.xml")
public class CalendarEventActionsSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @Subscribe("calendar")
    public void onCalendarResizeEvent(final EventResizeEvent event) {
        notifications.create("EventResizeEvent", "Event " + event.getCalendarEvent().getTitle() + " is resized")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("calendar")
    public void onCalendarDropEvent(final EventDropEvent event) {
        notifications.create("EventDropEvent", "Event " + event.getCalendarEvent().getTitle() + " is dropped")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("calendar")
    public void onCalendarClickEvent(final EventClickEvent event) {
        notifications.create("EventClickEvent", "Event " + event.getCalendarEvent().getTitle() + " is clicked")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
