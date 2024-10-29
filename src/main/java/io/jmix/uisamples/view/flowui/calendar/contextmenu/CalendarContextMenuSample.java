package io.jmix.uisamples.view.flowui.calendar.contextmenu;

import io.jmix.core.Metadata;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.action.DialogAction;
import io.jmix.flowui.app.inputdialog.DialogActions;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.contextmenu.FullCalendarContextMenu;
import io.jmix.fullcalendarflowui.component.contextmenu.event.FullCalendarCellContext;
import io.jmix.fullcalendarflowui.component.data.CalendarEvent;
import io.jmix.fullcalendarflowui.component.data.EntityCalendarEvent;
import io.jmix.uisamples.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.flowui.app.inputdialog.InputParameter.*;

@ViewController("calendar-context-menu")
@ViewDescriptor("calendar-context-menu.xml")
public class CalendarContextMenuSample extends StandardView {

    @ViewComponent
    private CollectionContainer<Event> eventsDc;
    @ViewComponent
    private FullCalendar calendar;

    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private Metadata metadata;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private UiComponents uiComponents;

    @Subscribe
    public void onInit(final InitEvent event) {
        FullCalendarContextMenu contextMenu = calendar.getContextMenu();
        contextMenu.setContentMenuHandler(this::contextMenuHandler);
    }

    private boolean contextMenuHandler(FullCalendarCellContext context) {
        FullCalendarContextMenu contextMenu = calendar.getContextMenu();

        EntityCalendarEvent<Event> calendarEvent = context.getCalendarEvent();
        if (calendarEvent != null) {
            contextMenu.removeAll();

            contextMenu.addItem(messageBundle.getMessage("contextMenu.edit"),
                    clickEvent -> showEditDialog(context));
            contextMenu.addItem(messageBundle.getMessage("contextMenu.remove"),
                    clickEvent -> eventsDc.getMutableItems().remove(calendarEvent.getEntity()));

            return true;
        } else if (context.getDayCell() != null) {
            contextMenu.removeAll();
            contextMenu.addItem(messageBundle.getMessage("contextMenu.create"),
                    clickEvent -> showEditDialog(context));

            return true;
        }


        return false;
    }

    private void showEditDialog(FullCalendarCellContext context) {
        CalendarEvent calendarEvent = context.getCalendarEvent();

        dialogs.createInputDialog(this)
                .withHeader(messageBundle.getMessage("dialog.header"))
                .withParameters(
                        stringParameter("title")
                                .withLabel(messageBundle.getMessage("dialog.title.label"))
                                .withField(() -> {
                                    TypedTextField<String> field = uiComponents.create(TypedTextField.class);
                                    field.setWidthFull();
                                    field.setTypedValue(calendarEvent == null ? null : calendarEvent.getTitle());
                                    field.setEnabled(calendarEvent == null);
                                    field.setRequired(true);
                                    return field;
                                }),
                        booleanParameter("allDay")
                                .withLabel(messageBundle.getMessage("dialog.allDay.label"))
                                .withDefaultValue(calendarEvent == null ? null : calendarEvent.getAllDay()),
                        localDateTimeParameter("startDate")
                                .withLabel(messageBundle.getMessage("dialog.startDate.label"))
                                .withDefaultValue(calendarEvent == null ? null : calendarEvent.getStartDateTime())
                                .withRequired(true),
                        localDateTimeParameter("endDate")
                                .withLabel(messageBundle.getMessage("dialog.endDate.label"))
                                .withDefaultValue(calendarEvent == null ? null : calendarEvent.getEndDateTime()))
                .withActions(DialogActions.OK_CANCEL, result -> {
                    if (result.getCloseActionType() == DialogAction.Type.OK) {
                        if (calendarEvent == null) {
                            Event event = metadata.create(Event.class);
                            event.setTitle(result.getValue("title"));
                            event.setAllDay(result.getValue("allDay"));
                            event.setStartDate(result.getValue("startDate"));
                            event.setEndDate(result.getValue("endDate"));
                            eventsDc.getMutableItems().add(event);
                        } else {
                            calendarEvent.setAllDay(result.getValue("allDay"));
                            calendarEvent.setStartDateTime(result.getValue("startDate"));
                            calendarEvent.setEndDateTime(result.getValue("endDate"));
                        }
                    }
                })
                .open();
    }
}
