package io.jmix.uisamples.view.flowui.calendar.eventstyling;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController("calendar-event-styling")
@ViewDescriptor("calendar-event-styling.xml")
public class CalendarEventStylingSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.addDataProvider(new ListCalendarDataProvider(List.of(
                        SimpleCalendarEvent.create()
                                .withTitle("All-day event without color")
                                .withAllDay(true)
                                .withStartDateTime(LocalDate.now().withDayOfMonth(9), LocalTime.MIDNIGHT)
                                .build(),
                        SimpleCalendarEvent.create()
                                .withTitle("Simple event without color")
                                .withStartDateTime(LocalDate.now().withDayOfMonth(10), LocalTime.NOON)
                                .build(),
                        SimpleCalendarEvent.create()
                                .withTitle("All-day colored event")
                                .withAllDay(true)
                                .withBackgroundColor("#946BD6")
                                .withTextColor("#2E0571")
                                .withBorderColor("#4C10AE")
                                .withStartDateTime(LocalDate.now().withDayOfMonth(11), LocalTime.MIDNIGHT)
                                .build(),
                        SimpleCalendarEvent.create()
                                .withTitle("Simple colored event")
                                .withBackgroundColor("#946BD6")
                                .withTextColor("#2E0571")
                                .withBorderColor("#4C10AE")
                                .withStartDateTime(LocalDate.now().withDayOfMonth(12), LocalTime.NOON)
                                .build()
                ))
        );
    }
}
