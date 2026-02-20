package io.jmix.uisamples.view.flowui.components.datagrid.sortevent;

import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.data.event.SortEvent;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@ViewController("data-grid-sort-event")
@ViewDescriptor("data-grid-sort-event.xml")
public class DataGridSortEvent extends StandardView {

    @Autowired
    private Notifications notifications;

    @Subscribe("dataGrid")
    public void onDataGridSort(final SortEvent<DataGrid<Order>, GridSortOrder<DataGrid<Order>>> event) {
        String sortDescription;

        if (event.getSortOrder().isEmpty()) {
            sortDescription = "Default";
        } else {
            GridSortOrder<DataGrid<Order>> primaryOrder =
                    event.getSortOrder().get(0);

            DataGrid.Column column = primaryOrder.getSorted();
            String header = column.getHeaderText();
            String directionText = switch (primaryOrder.getDirection()) {
                case ASCENDING -> "Ascending";
                case DESCENDING -> "Descending";
            };
            sortDescription = (header != null ? header : "<no header>")
                    + ": " + directionText;
        }
        notifications.create(sortDescription)
                .show();
    }
}