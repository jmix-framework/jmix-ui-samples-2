package io.jmix.uisamples.view.flowui.calendar.customcalendardisplaymode;

import io.jmix.fullcalendarflowui.kit.component.model.CalendarDisplayMode;

public enum CustomCalendarDisplayModes implements CalendarDisplayMode {

    TWO_MONTHS("twoMonths"),
    THREE_DAYS("threeDays");

    private final String id;

    CustomCalendarDisplayModes(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
