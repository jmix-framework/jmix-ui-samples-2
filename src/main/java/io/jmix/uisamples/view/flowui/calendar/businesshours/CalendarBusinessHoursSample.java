package io.jmix.uisamples.view.flowui.calendar.businesshours;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import io.jmix.fullcalendarflowui.component.model.CalendarBusinessHours;
import io.jmix.fullcalendarflowui.component.model.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController("calendar-business-hours")
@ViewDescriptor("calendar-business-hours.xml")
public class CalendarBusinessHoursSample extends StandardView {

    @ViewComponent
    private FullCalendar declarativeCalendar;
    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        declarativeCalendar.addDataProvider(
                new ListCalendarDataProvider(
                        List.of(SimpleCalendarEvent.create()
                                .withTitle("Event")
                                .withStartDateTime(LocalDate.now(), LocalTime.NOON)
                                .build())
                )
        );

        calendar.addDataProvider(
                new ListCalendarDataProvider(
                        List.of(SimpleCalendarEvent.create()
                                .withTitle("Event")
                                .withStartDateTime(LocalDate.now(), LocalTime.NOON)
                                .build())
                )
        );

        setBusinessHoursProgrammatically();
    }

    private void setBusinessHoursProgrammatically() {
        calendar.setBusinessHours(
                List.of(
                        CalendarBusinessHours.of(
                                LocalTime.of(10, 0),
                                LocalTime.of(19, 0),
                                DayOfWeek.TUESDAY),
                        CalendarBusinessHours.of(
                                LocalTime.of(11, 0),
                                LocalTime.of(20, 0),
                                DayOfWeek.THURSDAY)
                )
        );
    }
}
