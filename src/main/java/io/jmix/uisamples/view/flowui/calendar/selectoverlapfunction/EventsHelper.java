package io.jmix.uisamples.view.flowui.calendar.selectoverlapfunction;

import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;

import java.time.LocalDateTime;
import java.util.List;

public final class EventsHelper {

    public static List<CalendarEvent> generateEvents() {
        return List.of(
                SimpleCalendarEvent.create()
                        .withTitle("Offsite corporate event")
                        .withGroupId("holiday")
                        .withAllDay(true)
                        .withStartDateTime(LocalDateTime.now().withDayOfMonth(3))
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Company Wellness Day")
                        .withGroupId("holiday")
                        .withAllDay(true)
                        .withStartDateTime(LocalDateTime.now().withDayOfMonth(13))
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Employee Appreciation Day")
                        .withGroupId("holiday")
                        .withAllDay(true)
                        .withStartDateTime(LocalDateTime.now().withDayOfMonth(17))
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Innovation Hackathon")
                        .withGroupId("holiday")
                        .withAllDay(true)
                        .withStartDateTime(LocalDateTime.now().withDayOfMonth(22))
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Diversity and Inclusion Summit")
                        .withGroupId("holiday")
                        .withAllDay(true)
                        .withStartDateTime(LocalDateTime.now().withDayOfMonth(28))
                        .build()
        );
    }
}
