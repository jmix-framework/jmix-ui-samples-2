package io.jmix.uisamples.view.flowui.calendar.genericcalendardisplaymodes;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.core.Messages;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.kit.component.model.GenericCalendarDisplayModes;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static io.jmix.fullcalendarflowui.kit.component.model.GenericCalendarDisplayModes.DAY_GRID;

@ViewController(id = "calendar-generic-calendar-display-modes")
@ViewDescriptor(path = "calendar-generic-calendar-display-modes.xml")
public class CalendarGenericCalendarDisplayModesSample extends StandardView {

    @ViewComponent
    private FullCalendar calendar;
    @ViewComponent
    private JmixComboBox<GenericCalendarDisplayModes> genericDisplayModesBox;
    @ViewComponent
    private TypedDatePicker<LocalDate> endDateField;
    @ViewComponent
    private TypedDatePicker<LocalDate> startDateField;

    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        genericDisplayModesBox.setItems(GenericCalendarDisplayModes.values());
        genericDisplayModesBox.setItemLabelGenerator(messages::getMessage);
        genericDisplayModesBox.setValue(DAY_GRID);
        genericDisplayModesBox.addValueChangeListener(e ->
                calendar.setCalendarDisplayMode(e.getValue() == null ? DAY_GRID : e.getValue()));
    }

    @Subscribe(id = "setRangeBtn", subject = "clickListener")
    public void onSetRangeBtnClick(final ClickEvent<JmixButton> event) {
        if (startDateField.getValue() == null
                || endDateField.getValue() == null
                || startDateField.getValue().isAfter(endDateField.getValue())) {
            return;
        }
        calendar.setVisibleRange(startDateField.getValue(), endDateField.getValue());
    }
}