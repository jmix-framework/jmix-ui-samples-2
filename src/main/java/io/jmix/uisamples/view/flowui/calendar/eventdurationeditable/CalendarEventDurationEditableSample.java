package io.jmix.uisamples.view.flowui.calendar.eventdurationeditable;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarEvent;
import io.jmix.fullcalendarflowui.component.event.EventResizeEvent;
import io.jmix.uisamples.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("calendar-event-duration-editable")
@ViewDescriptor("calendar-event-duration-editable.xml")
public class CalendarEventDurationEditableSample extends StandardView {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe("calendar")
    public void onCalendarResizeEvent(final EventResizeEvent event) {
        EntityCalendarEvent<Event> calendarEvent = event.getCalendarEvent();

        dataManager.save(calendarEvent.getEntity());

        notifications.create("Event: \"%s\" is saved".formatted(calendarEvent.getTitle()))
                .withType(Notifications.Type.SUCCESS)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }
}
