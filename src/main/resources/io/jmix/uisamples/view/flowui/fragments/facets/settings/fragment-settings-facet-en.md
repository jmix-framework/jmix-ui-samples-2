`Settings` facet is designed to persist the state of UI components or custom data in a database in
`FLOWUI_USER_SETTINGS` table.
`UserSettingsItem` JPA entity is used for saving state.

To enable **settings** in a Fragment, it should contain the `SettingsFacet`. The simplest way to add facet is
XML.

Facet with `auto="true"` tries to find all components with defined ID that supports **settings**.
All these components will save and apply their settings.

Supported components:

- `DataGrid`: saves order, sorting and visibility of columns
- `Details`: saves `opened` state
- `SimplePagination`: saves `maxResults`, if `itemsPerPageVisible` attribute is `true`
- `GenericFilter`: saves `opened` state

This sample uses `DataGrid` and `Details` components to demonstrate how settings facet works.
The initial state of the `Details` component is set to `opened`.
You can change this state, then go to another view and return.
The last state will be restored. The same is possible for a `DataGrid` component.
