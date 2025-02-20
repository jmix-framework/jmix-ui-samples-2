package io.jmix.uisamples.view.flowui.cookbook.composition5;

import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.MeetingPoint;
import io.jmix.uisamples.entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController(id = "terminal-detail-5")
@ViewDescriptor(path = "terminal-detail-view.xml")
@EditedEntityContainer("terminalDc")
public class TerminalDetailView extends StandardDetailView<Terminal> {

    @Autowired
    private Notifications notifications;

    @ViewComponent
    private DataGrid<MeetingPoint> meetingPointsDataGrid;
    @ViewComponent
    private CollectionPropertyContainer<MeetingPoint> meetingPointsDc;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private MessageBundle messageBundle;

    @Override
    public String getPageTitle() {
        return getEditedEntityOrNull() != null
                ? messageBundle.formatMessage("meetingPoints", getEditedEntity().getName())
                : super.getPageTitle();
    }

    @Subscribe("meetingPointsDataGrid.createAction")
    public void onMeetingPointsDataGridCreateAction(final ActionPerformedEvent event) {
        if (meetingPointsDataGrid.getEditor().isOpen()) {
            notifications.show("Close the editor before creating a new entity");
            return;
        }

        // Create a new meeting point instance
        MeetingPoint meetingPoint = dataContext.create(MeetingPoint.class);
        meetingPoint.setTerminal(getEditedEntity());
        // And add it to the collection container
        meetingPointsDc.getMutableItems().add(meetingPoint);

        // Opens the data grid inline editor
        meetingPointsDataGrid.select(meetingPoint);
        meetingPointsDataGrid.getEditor().editItem(meetingPoint);
    }

    @Subscribe("meetingPointsDataGrid")
    public void onMeetingPointsDataGridItemDoubleClick(final ItemDoubleClickEvent<MeetingPoint> event) {
        meetingPointsDataGrid.getEditor().editItem(event.getItem());
    }

    @Subscribe(id = "meetingPointsDc", target = Target.DATA_CONTAINER)
    public void onMeetingPointsDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<MeetingPoint> event) {
        // Mark the root edited `Terminal` entity as modified in `DataContext`
        // when a meeting point's property changes
        Terminal terminal = getEditedEntity();
        dataContext.setModified(terminal, true);
    }
}
