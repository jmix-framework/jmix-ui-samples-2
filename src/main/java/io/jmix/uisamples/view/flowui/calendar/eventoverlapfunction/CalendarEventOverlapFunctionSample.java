package io.jmix.uisamples.view.flowui.calendar.eventoverlapfunction;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import io.jmix.fullcalendarflowui.kit.component.model.JsFunction;
import org.apache.groovy.util.Maps;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController("calendar-event-overlap-function")
@ViewDescriptor("calendar-event-overlap-function.xml")
public class CalendarEventOverlapFunctionSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.setEventOverlapJsFunction(new JsFunction("""
                function(stillEvent, movingEvent) {
                    console.dir(stillEvent);
                    const stillEventProps = stillEvent.extendedProps;
                    const movingEventProps = movingEvent.extendedProps;
                    return stillEventProps.workingShiftNumber === movingEventProps.workingShiftNumber;
                }
                """));

        calendar.addDataProvider(new ListCalendarDataProvider(generateEvents()));
    }

    private List<CalendarEvent> generateEvents() {
        return List.of(
                SimpleCalendarEvent.create()
                        .withTitle("Event for WS #1")
                        .withAllDay(true)
                        .withStartDateTime(LocalDate.now().minusDays(9), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().minusDays(7), LocalTime.MIDNIGHT)
                        .withAdditionalProperties(Maps.of("workingShiftNumber", "WS #1"))
                        .build(),
                SimpleCalendarEvent.create()
                        .withTitle("Event for WS #2")
                        .withAllDay(true)
                        .withStartDateTime(LocalDate.now().minusDays(15), LocalTime.MIDNIGHT)
                        .withEndDateTime(LocalDate.now().minusDays(13), LocalTime.MIDNIGHT)
                        .withAdditionalProperties(Maps.of("workingShiftNumber", "WS #2"))
                        .build()
        );
    }
}
