package io.jmix.uisamples.view.flowui.calendar.selection;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import io.jmix.core.Messages;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.event.SelectEvent;
import io.jmix.fullcalendarflowui.component.event.UnselectEvent;
import io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes.DAY_GRID_MONTH;
import static io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes.TIME_GRID_WEEK;

@ViewController("calendar-selection")
@ViewDescriptor("calendar-selection.xml")
public class CalendarSelectionSample extends StandardView {

    @ViewComponent
    private JmixComboBox<CalendarDisplayModes> displayModesBox;
    @ViewComponent
    private FullCalendar calendar;
    @ViewComponent
    private TypedDatePicker<LocalDate> startDateField;
    @ViewComponent
    private TypedDatePicker<LocalDate> endDateField;
    @ViewComponent
    private TypedDateTimePicker<LocalDateTime> startDateTimeField;
    @ViewComponent
    private TypedDateTimePicker<LocalDateTime> endDateTimeField;

    @Autowired
    private Messages messages;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event) {
        displayModesBox.setItems(DAY_GRID_MONTH, TIME_GRID_WEEK);
        displayModesBox.setItemLabelGenerator(messages::getMessage);
        displayModesBox.setValue(DAY_GRID_MONTH);
        displayModesBox.addValueChangeListener(e ->
                calendar.setCalendarDisplayMode(e.getValue() == null ? DAY_GRID_MONTH : e.getValue()));
    }

    @Subscribe(id = "calendar")
    public void onCalendarSelectEvent(SelectEvent event) {
        notifications.create("SelectEvent",
                        String.format("From %s to %s", event.getStartDateTime(), event.getEndDateTime()))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe(id = "calendar")
    public void onCalendarUnselectEvent(UnselectEvent event) {
        notifications.create("UnselectEvent")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe(id = "selectDateBtn", subject = "clickListener")
    public void onSelectDateBtnClick(final ClickEvent<JmixButton> event) {
        LocalDate start = startDateField.getValue();
        LocalDate end = endDateField.getValue();
        if (start == null || end == null) {
            return;
        }

        calendar.select(start, end);
    }

    @Subscribe(id = "selectDateTimeBtn", subject = "clickListener")
    public void onSelectDateTimeBtnClick(final ClickEvent<JmixButton> event) {
        LocalDateTime start = startDateTimeField.getValue();
        LocalDateTime end = endDateTimeField.getValue();
        if (start == null || end == null) {
            return;
        }

        calendar.select(start, end);
    }
}
