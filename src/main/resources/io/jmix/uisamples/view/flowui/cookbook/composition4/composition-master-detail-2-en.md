This sample illustrates how to reduce the nesting of dialog windows by using master-detail lists of entities at the bottom of the composition.

Here you can see the same data structure consisting of `Airport`, `Terminal` and `MeetingPoint` entities as in the [2-levels composition]({contextPath}/sample/composition-2-levels) example. But the list of meeting points is displayed next to the list of terminals right in the airport detail view. This master-detail structure eliminates one level of nested dialog windows, thereby improving the user experience.

Implementation details:

- [Airport.java]({currentPath}?tab=Airport.java)
  - `Airport.terminals` attribute is annotated with `@Composition`.

- [Terminal.java]({currentPath}?tab=Terminal.java)
    - `Terminal.meetingPoints` attribute is annotated with `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml) shows an airport and the master-detail lists of its terminals and meeting points. 
  - The fetch plan for loading `Airport` contains the `Airport.terminals` attribute which in turn contains `Terminal.meetingPoints`.
  - Two nested collection data containers are defined: for `Airport.terminals` and `Terminal.meetingPoints` attributes.
  - Data grids displaying terminals and meeting points are connected to the respective data containers.
  - The `create` and `edit` actions of the terminals and meeting points data grids open detail views in dialog windows (see `<property name="openMode" value="DIALOG"/>`).
