`urlQueryParameters` facet is designed for declarative binding of the fragment state to the URL. It allows
you to
preserve the fragment state when navigating between views or refreshing the browser page.

The obvious example is the entity list view with fragment with `dataGrid`, pagination and filtering. You will want to
preserve pagination and filtering parameters when refreshing the browser page and when navigating to a detail view and
back.

Supported components:

- `pagination`
- `propertyFilter`
- `genericFilter`
- `dataGridHeaderFilter`

In this sample, when opening the view, the age filter condition was automatically applied from the URL. You can use
pagination and see that new parameters have been added to the URL. After reloading the page, the state of these
components will be restored.
