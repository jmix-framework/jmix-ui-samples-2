This sample demonstrates how to use inline editing to reduce the nesting of dialog windows.

Here you can see the same data structure consisting of `Airport`, `Terminal` and `MeetingPoint` entities as in the [2-levels composition]({contextPath}/sample/composition-2-levels) example. The airport detail view displays the list of terminals in the editable data grid. The **Show meeting points** button allows users to view and edit meeting points of a selected terminal.

Implementation details:

- [Airport.java]({currentPath}?tab=Airport.java)
    - `Airport.terminals` attribute is annotated with `@Composition`.

- [Terminal.java]({currentPath}?tab=Terminal.java)
    - `Terminal.meetingPoints` attribute is annotated with `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml)
  - `terminalsDataGrid` columns have the `editable="true"` attribute. The data grid also has `editorActionsColumn` for opening and closing inline edit fields for a row.
  - `showMeetingPointsAction` action invokes the selected terminal detail view programmatically in `AirportDetailView` controller.

- [AirportDetailView.java]({currentPath}?tab=AirportDetailView.java) displays airport fields and editable data grid of terminals.
  - `onTerminalsDataGridCreateAction()` handler overrides the default behavior of `terminalsDataGrid.createAction`. Instead of opening a terminal detail view, it creates a new `Terminal` instance, adds it to the data container and invokes inline editing in the data grid.
  - `onTerminalsDataGridShowMeetingPointsAction()` handler is invoked when the user clicks **Show meeting points** button or action in the terminals data grid context menu. It opens [TerminalDetailView]({currentPath}?tab=TerminalDetailView.java) and passes the current `DataContext` to it as a parent. This code demonstrates how to open a nested composition view programmatically.

- [TerminalDetailView.java]({currentPath}?tab=TerminalDetailView.java)
  - This view displays meeting points of a particular terminal. Other attributes of the terminal are edited in the terminals data grid of the airport.
  - `onReady()` event handler updates the dialog title with the name of the edited terminal.
  - `onMeetingPointsDataGridCreateAction()` handler overrides the default behavior of `meetingPointsDataGrid.createAction`. It creates a new meeting point instance, adds it to the collection container and opens the data grid inline editor.
  - `onMeetingPointsDcItemPropertyChange()` event handler marks the root edited `Terminal` entity as modified in `DataContext` when a meeting point's property changes. This ensures all data are saved to the parent data context.   
