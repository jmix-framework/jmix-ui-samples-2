package io.jmix.uisamples.view.flowui.calendar.listcalendardataprovider;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ViewController("calendar-list-calendar-data-provider")
@ViewDescriptor("calendar-list-calendar-data-provider.xml")
public class CalendarListCalendarDataProviderSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    private ListCalendarDataProvider listDataProvider;

    @Subscribe
    public void onInit(final InitEvent event) {
        listDataProvider = new ListCalendarDataProvider("listDataProvider");
        listDataProvider.addItem(createEvent());

        calendar.addDataProvider(listDataProvider);
    }

    @Subscribe(id = "addEventBtn", subject = "clickListener")
    public void onAddEventBtnClick(final ClickEvent<JmixButton> event) {
        listDataProvider.addItem(createEvent());
    }

    @Subscribe(id = "removeEventBtn", subject = "clickListener")
    public void onRemoveEventBtnClick(final ClickEvent<JmixButton> event) {
        List<CalendarEvent> items = listDataProvider.getItems();
        if (!items.isEmpty()) {
            listDataProvider.removeItem(items.get(items.size() - 1));
        }
    }

    @Subscribe(id = "updateEventBtn", subject = "clickListener")
    public void onUpdateEventBtnClick(final ClickEvent<JmixButton> event) {
        List<CalendarEvent> items = listDataProvider.getItems();
        if (!items.isEmpty()) {
            SimpleCalendarEvent calendarEvent = (SimpleCalendarEvent) items.get(items.size() - 1);
            calendarEvent.setTitle("Updated event: " + RandomStringUtils.secure().nextAlphabetic(5));
            listDataProvider.updateItem(calendarEvent);
        }
    }

    private CalendarEvent createEvent() {
        return SimpleCalendarEvent.create()
                .withTitle("Event: " + RandomStringUtils.secure().nextAlphabetic(5))
                .withStartDateTime(LocalDate.now().withDayOfMonth(10), LocalTime.NOON)
                .build();
    }
}
