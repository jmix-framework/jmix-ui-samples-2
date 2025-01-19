This sample demonstrates a two-level composition of `Airport`, `Terminal` and `MeetingPoint` entities: the airport contains a collection of terminals which in turn contain collections of meeting points. 

Terminals and meeting points are saved to the database only together with the owning airport, when you click **OK** button in the airport detail view. If you change a terminal or a meeting point and click **OK** in its detail view, but then click **Cancel** in the airport detail view, the changes will not be saved.  

Implementation details: 

- [Airport.java]({currentPath}?tab=Airport.java)
  - `Airport.terminals` attribute is annotated with `@Composition`.

- [Terminal.java]({currentPath}?tab=Terminal.java)
  - `Terminal.meetingPoints` attribute is annotated with `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml)
  - The fetch plan for loading `Airport` contains the `Airport.terminals` attribute.
  - A nested collection data container is defined for the `Airport.terminals` attribute. 
  - The data grid displaying terminals is connected to this data container.
  - The `create` and `edit` actions of the terminals data grid open the terminal detail view as a dialog (see `<property name="openMode" value="DIALOG"/>`). 

- [terminal-detail-view.xml]({currentPath}?tab=terminal-detail-view.xml)
  - The fetch plan for loading `Terminal` contains the `Terminal.meetingPoints` attribute.
  - A nested collection data container is defined for the `Terminal.meetingPoints` attribute.
  - The data grid displaying meeting points is connected to this data container.
  - The `create` and `edit` actions of the meeting points data grid open the meeting point detail view as a dialog (see `<property name="openMode" value="DIALOG"/>`).

The use of multiple nested dialog windows might be problematic for users. The following examples illustrate how to minimize the nesting while editing the same data structure:

- [Reducing levels from above]({contextPath}/sample/composition-reduce-top)
- [Reducing levels by inline editing]({contextPath}/sample/composition-reduce-bottom)
