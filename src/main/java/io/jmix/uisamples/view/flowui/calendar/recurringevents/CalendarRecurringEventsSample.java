package io.jmix.uisamples.view.flowui.calendar.recurringevents;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import io.jmix.fullcalendarflowui.component.event.EventDropEvent;
import io.jmix.fullcalendarflowui.component.event.EventResizeEvent;
import io.jmix.fullcalendarflowui.component.model.DaysOfWeek;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static io.jmix.fullcalendarflowui.component.model.DayOfWeek.MONDAY;
import static io.jmix.fullcalendarflowui.component.model.DayOfWeek.THURSDAY;

@ViewController("calendar-recurring-events")
@ViewDescriptor("calendar-recurring-events.xml")
public class CalendarRecurringEventsSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.addDataProvider(new ListCalendarDataProvider(generateRecurringEvents()));
    }

    @Subscribe("calendar")
    public void onCalendarEventDrop(final EventDropEvent event) {
        CalendarEvent calendarEvent = event.getCalendarEvent();

        notifications.show("Event with \"%s\" ID is moved".formatted(calendarEvent.getId()));
    }

    @Subscribe("calendar")
    public void onCalendarEventResize(final EventResizeEvent event) {
        CalendarEvent calendarEvent = event.getCalendarEvent();

        notifications.show("Event with \"%s\" ID is resized".formatted(calendarEvent.getId()));
    }

    private List<CalendarEvent> generateRecurringEvents() {
        return List.of(
                SimpleCalendarEvent.create("morning")
                        .withTitle("Morning jog")
                        .withRecurringDaysOfWeek(new DaysOfWeek(Set.of(MONDAY)))
                        .withRecurringStartDate(LocalDate.now().withDayOfMonth(1))
                        .withRecurringEndDate(LocalDate.now().withDayOfMonth(26))
                        .withRecurringStartTime(LocalTime.of(8, 0))
                        .build(),
                SimpleCalendarEvent.create("cinema")
                        .withTitle("Cinema")
                        .withGroupId("cinema")
                        .withAllDay(true)
                        .withRecurringDaysOfWeek(new DaysOfWeek(Set.of(THURSDAY)))
                        .withRecurringStartDate(LocalDate.now().withDayOfMonth(1))
                        .withRecurringEndDate(LocalDate.now().withDayOfMonth(26))
                        .build()
        );
    }
}
