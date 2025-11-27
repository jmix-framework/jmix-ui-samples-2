This sample demonstrates possible approaches for displaying PDF files as a dynamic resource using the `IFrame`
component.

To make displaying such resources available, you need to configure the `frame-ancestors` policy directive. See [
`IframeCustomSecurity.java`]({currentPath}?tab=IframeCustomSecurity.java).

Dynamic resources are loaded on the client-side using the `DownloadHandler` objects.

`DownloadHandler` provides generated URI. Registration of the resource URI is automatically handled by
components that explicitly support stream resources (including `IFrame`).\
This way, the resource will be cleaned up when the component is removed from the view.

This source code contains a directive that allows the use of resources if the requests is sent from `demo.jmix.io`.\
More detailed information is available in the documentation for the
directive: [CSP: frame-ancestors - HTTP | MDN 6](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy/frame-ancestors).
