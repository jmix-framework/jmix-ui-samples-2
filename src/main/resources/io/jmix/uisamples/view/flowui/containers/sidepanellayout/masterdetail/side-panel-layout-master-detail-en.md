This example shows how to use `SidePanelLayout` as an entity editor.
    
A `CustomerDetailFragment` is created dynamically and placed into the side panel for both
`CreateAction` and `EditAction` actions.
    
The fragment handles validation, save/cancel actions, and closes the panel after completion, while the host view
updates or reloads the `DataGrid`.
