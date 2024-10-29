package io.jmix.uisamples.view.flowui.calendar.callbackcalendardataprovider;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;

@ViewController("calendar-callback-calendar-data-provider")
@ViewDescriptor("calendar-callback-calendar-data-provider.xml")
public class CalendarCallbackCalendarDataProviderSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;

    @Subscribe(id = "previousBtn", subject = "clickListener")
    public void onPreviousBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToPrevious();
    }

    @Subscribe(id = "nextBtn", subject = "clickListener")
    public void onNextBtnClick(final ClickEvent<JmixButton> event) {
        calendar.navigateToNext();
    }
}
