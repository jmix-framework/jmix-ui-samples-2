

This sample demonstrates a one-level composition of `Airport` and `Terminal` entities: the airport contains a collection of terminals. 

Terminals are saved to the database only together with the owning airport, when you click **OK** button in the airport detail view. If you change a terminal and click **OK** in its detail view, but then click **Cancel** in the airport detail view, the terminal changes will not be saved.  

Implementation details: 

- [Airport.java]({currentPath}?tab=Airport.java)
  - `Airport.terminals` attribute is annotated with `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml)
  - The fetch plan for loading `Airport` contains the `Airport.terminals` attribute.
  - A nested collection data container is defined for the `Airport.terminals` attribute. 
  - The data grid displaying terminals is connected to this data container.
  - The `create` and `edit` actions of the terminals data grid open the terminal detail view as a dialog (see `<property name="openMode" value="DIALOG"/>`). 
