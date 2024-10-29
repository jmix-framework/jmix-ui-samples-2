package io.jmix.uisamples.view.flowui.calendar.eventconstraint;

import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import io.jmix.fullcalendarflowui.component.model.Display;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public final class EventsHelper {

    public static List<CalendarEvent> generateEvents() {
        return List.of(
                SimpleCalendarEvent.create()
                        .withTitle("Event-1 GP1")
                        .withGroupId("GP1")
                        .withStartDateTime(LocalDateTime.now())
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Event-2 GP1")
                        .withGroupId("GP1")
                        .withStartDateTime(LocalDateTime.now().plusDays(1))
                        .build()
        );
    }

    public static List<CalendarEvent> generateBackgroundEvents() {
        return List.of(
                SimpleCalendarEvent.create()
                        .withTitle("Work days")
                        .withGroupId("workDays")
                        .withAllDay(true)
                        .withDisplay(Display.BACKGROUND)
                        .withStartDateTime(LocalDate.now().minusDays(17), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().minusDays(14), LocalTime.MIDNIGHT)
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Work days")
                        .withGroupId("workDays")
                        .withAllDay(true)
                        .withDisplay(Display.BACKGROUND)
                        .withStartDateTime(LocalDate.now().minusDays(7), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().minusDays(4), LocalTime.MIDNIGHT)
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Work days")
                        .withGroupId("workDays")
                        .withAllDay(true)
                        .withDisplay(Display.BACKGROUND)
                        .withStartDateTime(LocalDate.now().plusDays(4), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().plusDays(7), LocalTime.MIDNIGHT)
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Work days")
                        .withGroupId("workDays")
                        .withAllDay(true)
                        .withDisplay(Display.BACKGROUND)
                        .withStartDateTime(LocalDate.now().plusDays(14), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().plusDays(17), LocalTime.MIDNIGHT)
                        .build()
        );
    }
}
