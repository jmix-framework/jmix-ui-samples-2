package io.jmix.uisamples.view.flowui.calendar.classnamesgenerator;

import io.jmix.flowui.view.Install;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.fullcalendarflowui.component.event.DayCellClassNamesContext;
import io.jmix.fullcalendarflowui.component.event.DayHeaderClassNamesContext;
import io.jmix.fullcalendarflowui.component.model.DayOfWeek;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ViewController("calendar-class-names-generator")
@ViewDescriptor("calendar-class-names-generator.xml")
public class CalendarClassNamesGeneratorSample extends StandardView {

    private static final String TODAY_CLASS_NAME = "uisamples-today";
    private static final String WEEKEND_CLASS_NAME = "uisamples-weekend";
    private static final String DAY_HEADER_CLASS_NAME = "uisamples-day-header";

    @Install(to = "calendar", subject = "dayCellClassNamesGenerator")
    private List<String> calendarDayCellClassNamesGenerator(final DayCellClassNamesContext context) {
        List<String> classes = new ArrayList<>(2);

        DayOfWeek dow = context.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            classes.add(WEEKEND_CLASS_NAME);
        }
        if (context.getDate().isEqual(LocalDate.now())) {
            classes.add(TODAY_CLASS_NAME);
        }
        return classes;
    }

    @Install(to = "calendar", subject = "dayHeaderClassNamesGenerator")
    private List<String> calendarDayHeaderClassNamesGenerator(final DayHeaderClassNamesContext context) {
        List<String> classes = new ArrayList<>(2);
        classes.add(DAY_HEADER_CLASS_NAME);

        DayOfWeek dow = context.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            classes.add(WEEKEND_CLASS_NAME);
        }
        if (dow == DayOfWeek.fromDayOfWeek(LocalDate.now().getDayOfWeek())) {
            classes.add(TODAY_CLASS_NAME);
        }
        return classes;
    }
}
