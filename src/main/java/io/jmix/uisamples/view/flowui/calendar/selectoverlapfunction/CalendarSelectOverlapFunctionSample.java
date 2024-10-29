package io.jmix.uisamples.view.flowui.calendar.selectoverlapfunction;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.kit.component.model.JsFunction;

@ViewController("calendar-select-overlap-function")
@ViewDescriptor("calendar-select-overlap-function.xml")
public class CalendarSelectOverlapFunctionSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.addDataProvider(new ListCalendarDataProvider(EventsHelper.generateEvents()));
        calendar.setSelectOverlapJsFunction(new JsFunction("""
                function(event) {
                   return event.groupId !== "holiday";
                }
                """));
    }
}
