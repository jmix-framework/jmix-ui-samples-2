This sample demonstrates advanced integration of the `InputMask` component provided by the Vaadin addon.\
The basic integration can be found in [another sample]({contextPath}/sample/input-mask-addon-simple).

To fully implement the component in the Jmix UI, you need to perform the following steps:

1. Implement a composite component based on `TypedTextField` to wrap the `InputMask` functionality (see [`MaskField.java`]({currentPath}?tab=MaskField.java))
2. Create an XSD schema to support the component in XML descriptor (see [`mask-field.xsd`]({currentPath}?tab=mask-field.xsd))
3. Implement a component loader from XML (see [`MaskFieldLoader.java`]({currentPath}?tab=MaskFieldLoader.java))
4. Register the component and loader in the application (see [`MaskFieldRegistration.java`]({currentPath}?tab=MaskFieldRegistration.java))

After completing these steps, you will be able to use the component standardly in the Jmix UI.
