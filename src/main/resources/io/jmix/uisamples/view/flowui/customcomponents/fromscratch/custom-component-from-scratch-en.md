This example shows how to build the light/dark theme toggle.

The server-side part of the component is located in [`ThemeToggle.java`]({currentPath}?tab=ThemeToggle.java). It
contains a number of annotations for integration with the client-side part:

- `@Tag` – specifies the name of the custom HTML element of the web component
- `@JsModule` – imports the JavaScript module containing the client-side part of the component

Client-side events sent as `CustomEvent` JavaScript objects are declared on the server side using the `@DomEvent` and
`@EventData` annotations. Also, the component class has a listener registration method for these events. See
`ThemeToggleThemeChangedEvent` nested class and `addThemeChangeListener()` method in [
`ThemeToggle.java`]({currentPath}?tab=ThemeToggle.java).

The [`theme-toggle.js`]({currentPath}?tab=theme-toggle.js) file located in the `frontend/src/component/theme-switcher`
folder contains the client-side part, which is a web component extending Vaadin's `Button`.

After creating the client-side and server-side parts of the component, it can be used in views by creating an instance
of its Java class and adding it to a parent component.

The following steps completely integrate the component into Jmix UI:

1. Create an XSD for the component (see [`theme-toggle.xsd`]({currentPath}?tab=theme-toggle.xsd)).
2. Implement the component loader (see [`ThemeToggleLoader.java`]({currentPath}?tab=ThemeToggleLoader.java)).
3. Register the component and its loader in the application (see [
   `ThemeToggleRegistration.java`]({currentPath}?tab=ThemeToggleRegistration.java)).

Once these steps are completed, the custom component can be used in view XML descriptors. See the [
`theme-toggle-usage.xml`]({currentPath}?tab=theme-toggle-usage.xml) file.

See more information about creating custom components in Vaadin
documentation: [Creating Components](https://vaadin.com/docs/latest/create-ui/creating-components),
[Using Vaadin Mixin Interfaces](https://vaadin.com/docs/latest/create-ui/creating-components/mixins), [Using
Events with Components](https://vaadin.com/docs/latest/create-ui/creating-components/events).
