package io.jmix.uisamples.view.flowui.calendar.simplecalendarevent;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController(id = "calendar-simple-calendar-event")
@ViewDescriptor(path = "calendar-simple-calendar-event.xml")
public class CalendarSimpleCalendarEventSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        SimpleCalendarEvent calendarEvent = SimpleCalendarEvent.create()
                .withTitle("Meeting")
                .withStartDateTime(LocalDate.now(), LocalTime.of(15, 0))
                .build();

        calendar.addDataProvider(new ListCalendarDataProvider(List.of(calendarEvent)));
    }
}