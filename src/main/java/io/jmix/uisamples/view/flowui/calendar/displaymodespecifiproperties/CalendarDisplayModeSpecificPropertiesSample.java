package io.jmix.uisamples.view.flowui.calendar.displaymodespecifiproperties;

import io.jmix.core.Messages;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes.DAY_GRID_MONTH;
import static io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayModes.TIME_GRID_WEEK;

@ViewController("calendar-display-mode-specific-properties")
@ViewDescriptor("calendar-display-mode-specific-properties.xml")
public class CalendarDisplayModeSpecificPropertiesSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;
    @ViewComponent
    private JmixComboBox<CalendarDisplayModes> displayModesBox;

    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        displayModesBox.setItems(DAY_GRID_MONTH, TIME_GRID_WEEK);
        displayModesBox.setItemLabelGenerator(messages::getMessage);
        displayModesBox.setValue(DAY_GRID_MONTH);
        displayModesBox.addValueChangeListener(e ->
                calendar.setCalendarDisplayMode(e.getValue() == null ? DAY_GRID_MONTH : e.getValue()));
    }
}
