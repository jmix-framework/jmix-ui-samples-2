package io.jmix.uisamples.view.flowui.calendar.customcalendardisplaymode;

import io.jmix.core.Messages;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.kit.component.model.CustomCalendarDisplayMode;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.fullcalendarflowui.kit.component.model.GenericCalendarDisplayModes.TIME_GRID;
import static io.jmix.uisamples.view.flowui.calendar.customcalendardisplaymode.CustomCalendarDisplayModes.THREE_DAYS;
import static io.jmix.uisamples.view.flowui.calendar.customcalendardisplaymode.CustomCalendarDisplayModes.TWO_MONTHS;

@ViewController("calendar-custom-calendar-display-mode")
@ViewDescriptor("calendar-custom-calendar-display-mode.xml")
public class CalendarCustomCalendarDisplayModeSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;
    @ViewComponent
    private JmixComboBox<CustomCalendarDisplayModes> displayModesBox;

    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        displayModesBox.setItems(CustomCalendarDisplayModes.values());
        displayModesBox.setItemLabelGenerator(messages::getMessage);
        displayModesBox.setValue(TWO_MONTHS);
        displayModesBox.addValueChangeListener(e ->
                calendar.setCalendarDisplayMode(e.getValue() == null ? TWO_MONTHS : e.getValue()));

        calendar.addCustomCalendarDisplayMode(
                new CustomCalendarDisplayMode(THREE_DAYS.getId(), TIME_GRID)
                        .withDayCount(3));
    }
}
