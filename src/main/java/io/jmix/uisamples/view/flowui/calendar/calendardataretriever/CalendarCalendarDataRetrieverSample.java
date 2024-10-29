package io.jmix.uisamples.view.flowui.calendar.calendardataretriever;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.CalendarDataRetriever;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController("calendar-calendar-data-retriever")
@ViewDescriptor("calendar-calendar-data-retriever.xml")
public class CalendarCalendarDataRetrieverSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe
    public void onInit(final InitEvent event) {
        calendar.addDataProvider(new CalendarDataRetriever() {
            @Override
            public List<CalendarEvent> onItemsFetch(ItemsFetchContext context) {
                return loadEvents(context.getStartDate(), context.getEndDate());
            }
        });
    }

    private List<CalendarEvent> loadEvents(LocalDate start, LocalDate end) {
        if ((start.isBefore(LocalDate.now()) || start.equals(LocalDate.now()))
                && end.isAfter(LocalDate.now())) {
            return List.of(
                    SimpleCalendarEvent.create()
                            .withTitle("Morning jog")
                            .withStartDateTime(
                                    LocalDate.now().withDayOfMonth(10),
                                    LocalTime.of(8, 0))
                            .build(),
                    SimpleCalendarEvent.create()
                            .withTitle("Work hours")
                            .withStartDateTime(
                                    LocalDate.now().withDayOfMonth(10),
                                    LocalTime.of(10, 0))
                            .withEndDateTime(
                                    LocalDate.now().withDayOfMonth(10),
                                    LocalTime.of(19, 0))
                            .build()
            );
        }

        return List.of();
    }

    @Subscribe(id = "previousBtn", subject = "clickListener")
    public void onPreviousBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToPrevious();
    }

    @Subscribe(id = "nextBtn", subject = "clickListener")
    public void onNextBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToNext();
    }
}
