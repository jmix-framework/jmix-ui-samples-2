This sample demonstrates how to provide inline editing in a `DataGrid` component.

To make a column editable, set its `editable` attribute to `true`. The `editorActionsColumn` element creates an additional column with buttons to control the inline editing.

This example illustrates two editing modes using two data grids on separate tabs:

- In _non-buffered_ mode (the default), inline edit fields directly update the entity attributes. The `editorActionsColumn` element should include a `closeButton`.

- In _buffered_ mode, users can confirm or discard changes in the edited row. The `editorActionsColumn` element should include the `saveButton` and `cancelButton`, which save or discard changes to the entity attributes. Buffered mode is enabled by setting the `editorBuffered` attribute of the `dataGrid` component to `true`.  

The example also demonstrates saving changes to the database. Depending on the state of the **Save immediately** checkbox, changes are saved right after closing the inline editor for each row or when the user clicks the **Save** button.

Changed entities are either saved immediately or queued for later processing using listeners on the `DataGridEditor` object. Listeners can be configured using `@Install` annotations or added programmatically via `dataGrid.getEditor().addSaveListener()` and similar methods. The simplest way is to use the **Handlers** tab of the Jmix Studio's **Component Inspector**, where editor event listeners are marked with `[Editor]` prefixes. 