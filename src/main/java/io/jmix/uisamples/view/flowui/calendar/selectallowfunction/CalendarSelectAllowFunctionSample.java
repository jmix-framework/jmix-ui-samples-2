package io.jmix.uisamples.view.flowui.calendar.selectallowfunction;

import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.kit.component.model.JsFunction;

import java.time.LocalDate;
import java.util.TimeZone;

@ViewController("calendar-select-allow-function")
@ViewDescriptor("calendar-select-allow-function.xml")
public class CalendarSelectAllowFunctionSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.setSelectAllowJsFunction(new JsFunction(
                String.format("""
                        function(selectionInfo) {
                        console.log('TAAG');
                           const currentDate = new Date('%s');
                           return currentDate < selectionInfo.start
                                  || currentDate >= selectionInfo.end;
                        }
                        """, convertToStrDate(LocalDate.now()))
        ));
    }

    private String convertToStrDate(LocalDate date) {
        TimeZone timeZone = calendar.getTimeZone() == null
                ? TimeZone.getDefault()
                : calendar.getTimeZone();
        return date.atStartOfDay(timeZone.toZoneId()).toOffsetDateTime().toString();
    }
}
