package io.jmix.uisamples.view.flowui.calendar.eventstarteditable;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarEvent;
import io.jmix.fullcalendarflowui.component.event.EventDropEvent;
import io.jmix.uisamples.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("calendar-event-start-editable")
@ViewDescriptor("calendar-event-start-editable.xml")
public class CalendarEventStartEditableSample extends StandardView {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe("calendar")
    public void onCalendarEventDrop(final EventDropEvent event) {
        EntityCalendarEvent<Event> calendarEvent = event.getCalendarEvent();

        dataManager.save(calendarEvent.getEntity());

        notifications.create("Event: \"%s\" is saved".formatted(calendarEvent.getTitle()))
                .withType(Notifications.Type.SUCCESS)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }
}
