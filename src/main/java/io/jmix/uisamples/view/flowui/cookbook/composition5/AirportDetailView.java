package io.jmix.uisamples.view.flowui.cookbook.composition5;

import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Airport;
import io.jmix.uisamples.entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController(id = "airport-detail-5")
@ViewDescriptor(path = "airport-detail-view.xml")
@EditedEntityContainer("airportDc")
public class AirportDetailView extends StandardDetailView<Airport> {

    @Autowired
    private DialogWindows dialogWindows;
    @Autowired
    private Notifications notifications;

    @ViewComponent
    private DataGrid<Terminal> terminalsDataGrid;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionPropertyContainer<Terminal> terminalsDc;

    @Subscribe("terminalsDataGrid.createAction")
    public void onTerminalsDataGridCreateAction(final ActionPerformedEvent event) {
        if (terminalsDataGrid.getEditor().isOpen()) {
            notifications.create("Close the editor before creating a new entity").show();
            return;
        }

        // Create a new `Terminal` instance
        Terminal terminal = dataContext.create(Terminal.class);
        terminal.setAirport(getEditedEntity());
        // And add it to the data container
        terminalsDc.getMutableItems().add(terminal);

        // Invoke inline editing in the data grid
        terminalsDataGrid.select(terminal);
        terminalsDataGrid.getEditor().editItem(terminal);
    }

    @Subscribe("terminalsDataGrid.showMeetingPointsAction")
    public void onTerminalsDataGridShowMeetingPointsAction(final ActionPerformedEvent event) {
        Terminal terminal = terminalsDataGrid.getSingleSelectedItem();
        if (terminal == null)
            return;

        // Open `TerminalDetailView` and pass the current `DataContext` to it as a parent
        // to support composition editing
        DialogWindow<TerminalDetailView> window = dialogWindows.detail(terminalsDataGrid)
                .withViewClass(TerminalDetailView.class)
                .withParentDataContext(dataContext)
                .editEntity(terminal)
                .build();
        window.setWidth("64em");
        window.setResizable(true);
        window.open();
    }

    @Subscribe("terminalsDataGrid")
    public void onTerminalsDataGridItemDoubleClick(final ItemDoubleClickEvent<Terminal> event) {
        terminalsDataGrid.getEditor().editItem(event.getItem());
    }
}