package io.jmix.uisamples.view.flowui.calendar.calendarcallbackloaddelegate;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.CallbackCalendarDataProvider.ItemsFetchContext;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarEvent;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import io.jmix.uisamples.bean.EventService;
import io.jmix.uisamples.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@ViewController("calendar-callback-load-delegate")
@ViewDescriptor("calendar-callback-load-delegate.xml")
public class CalendarCallbackLoadDelegateSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Autowired
    private EventService eventService;
    @Autowired
    private Notifications notifications;

    @Install(to = "calendar.simpleEventsDataProvider", subject = "loadDelegate")
    private List<CalendarEvent> calendarSimpleEventsDataProviderLoadDelegate(final ItemsFetchContext context) {
        return List.of(SimpleCalendarEvent.create()
                .withTitle("Simple event")
                .withStartDateTime(LocalDateTime.now())
                .build());
    }

    @Install(to = "calendar.entityEventsDataProvider", subject = "loadDelegate")
    private List<CalendarEvent> calendarEntityEventsDataProviderLoadDelegate(final ItemsFetchContext context) {
        showDataLoadingNotification();

        List<Event> events = eventService.fetchEvents(context.getStartDate(), context.getEndDate());

        return events.stream()
                .map(e -> (CalendarEvent) new EntityCalendarEvent<>(e,
                        (EntityCalendarDataProvider) context.getDataProvider()))
                .toList();
    }

    @Subscribe(id = "previousBtn", subject = "clickListener")
    public void onPreviousBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToPrevious();
    }

    @Subscribe(id = "nextBtn", subject = "clickListener")
    public void onNextBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToNext();
    }

    private void showDataLoadingNotification() {
        notifications.create("Loading events")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
