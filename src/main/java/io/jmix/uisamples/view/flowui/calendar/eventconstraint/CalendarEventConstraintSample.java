package io.jmix.uisamples.view.flowui.calendar.eventconstraint;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;

@ViewController("calendar-event-constraint")
@ViewDescriptor("calendar-event-constraint.xml")
public class CalendarEventConstraintSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.addDataProvider(new ListCalendarDataProvider(EventsHelper.generateEvents()));
        calendar.addDataProvider(new ListCalendarDataProvider(EventsHelper.generateBackgroundEvents()));
    }
}
