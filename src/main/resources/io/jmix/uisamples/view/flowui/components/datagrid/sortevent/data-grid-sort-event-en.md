`com.vaadin.flow.data.event.SortEvent` reacts to changes in column sorting.
The sample above shows the current sort order for each column as it changes. To add custom behavior when sorting changes — such as
triggering other updates on the view — add your own logic accessing the sort order via `event.getSortOrder()`.