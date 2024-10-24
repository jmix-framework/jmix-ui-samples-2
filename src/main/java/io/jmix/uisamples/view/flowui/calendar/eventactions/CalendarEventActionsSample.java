package io.jmix.uisamples.view.flowui.calendar.eventactions;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.event.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController(id = "calendar-event-actions")
@ViewDescriptor(path = "calendar-event-actions.xml")
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

    @Subscribe("calendar")
    public void onCalendarMouseEnterEvent(final EventMouseEnterEvent event) {
        notifications.create("EventMouseEnterEvent", "Mouse enter the " + event.getCalendarEvent().getTitle() + " event")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("calendar")
    public void onCalendarMouseLeaveEvent(final EventMouseLeaveEvent event) {
        notifications.create("EventMouseLeaveEvent", "Mouse leave the " + event.getCalendarEvent().getTitle() + " event")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}