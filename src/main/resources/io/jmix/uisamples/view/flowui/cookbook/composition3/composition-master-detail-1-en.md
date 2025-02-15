
This sample illustrates how to reduce the nesting of dialog windows by using master-detail lists of entities instead of the top level of the composition.

Here you can see the same data structure consisting of `Airport`, `Terminal` and `MeetingPoint` entities as in the [previous example]({contextPath}/sample/composition-2-levels) with two-level relationship. However, the list of terminals is displayed next to the list of airports in a master-detail format, rather than within the airport detail view. This removes one level of nested dialog windows, thereby enhancing the user experience.

In this example, airports and terminals are edited and saved independently, while meeting points are saved to the database only together with the owning terminal. So the composition relationship takes place only for `Terminal` and `MeetingPoint` entities. 

Implementation details: 

- [Airport.java]({currentPath}?tab=Airport.java)
  - `Airport.terminals` attribute is still annotated with `@Composition`, but it doesn't affect the system behavior in this example and can be omitted.

- [Terminal.java]({currentPath}?tab=Terminal.java)
  - `Terminal.meetingPoints` attribute is annotated with `@Composition`.

- [airport-list-view.xml]({currentPath}?tab=airport-list-view.xml)
  - A standalone collection data container is defined for the `Terminal` entity. The list of terminals for the selected airport is loaded into the container by a JPQL query with the `:container_airportsDc` parameter. This parameter is set by the `dataLoadCoordinator` facet in the [automatic mode]({docsBaseUrl}/flow-ui/facets/dataLoadCoordinator.html#automatic-mode).   

- [AirportListView.java]({currentPath}?tab=AirportListView.java)
  - The `terminalsDataGridCreate` action is enabled only when an airport is selected in the airports data grid. The action is initially disabled in [airport-list-view.xml]({currentPath}?tab=airport-list-view.xml).
  - A selected airport is set to a newly created terminal. 

- [terminal-detail-view.xml]({currentPath}?tab=terminal-detail-view.xml)
  - The fetch plan for loading `Terminal` contains the `Terminal.meetingPoints` attribute.
  - A nested collection data container is defined for the `Terminal.meetingPoints` attribute.
  - The data grid displaying meeting points is connected to this data container.
  - The `create` and `edit` actions of the meeting points data grid open the meeting point detail view as a dialog (see `<property name="openMode" value="DIALOG"/>`).
