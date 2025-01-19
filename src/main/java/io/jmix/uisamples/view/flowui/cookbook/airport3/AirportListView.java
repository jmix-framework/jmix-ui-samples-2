package io.jmix.uisamples.view.flowui.cookbook.airport3;

import io.jmix.flowui.action.list.CreateAction;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Airport;
import io.jmix.uisamples.entity.Terminal;


@ViewController(id = "composition-reduce-top")
@ViewDescriptor(path = "airport-list-view.xml")
@DialogMode(width = "64em")
public class AirportListView extends StandardListView<Airport> {

    @ViewComponent("terminalsDataGrid.create")
    private CreateAction<Terminal> terminalsDataGridCreate;
    @ViewComponent
    private CollectionContainer<Airport> airportsDc;

    @Subscribe(id = "airportsDc", target = Target.DATA_CONTAINER)
    public void onAirportsDcItemChange(final InstanceContainer.ItemChangeEvent<Airport> event) {
        // Enable 'create' action only when there is a selected airport in the data grid
        terminalsDataGridCreate.setEnabled(event.getItem() != null);
    }

    @Install(to = "terminalsDataGrid.create", subject = "initializer")
    private void terminalsDataGridCreateInitializer(final Terminal terminal) {
        // Set the selected airport for a newly created terminal
        terminal.setAirport(airportsDc.getItem());
    }
}