The `Card` component is a universal container for displaying content in the form of a card. The component contains
several predefined slots for customization.

The following slots are supported:

* `title` – card title can be set using an attribute or nested element
* `subTitle` – card subtitle can be set using an attribute or nested element
* `headerPrefix` – component before the header, most often it is `avatar` and similar components
* `header` – allows setting custom markup for the header (replaces `title` and `subtitle`)
* `headerSuffix`– component after the header
* `media`– media content for the card (image, avatar, etc.) can be styled using [
  `themeVariant`]({contextPath}/sample/card-theme-variant)
* `content`– markup for the main content of the card
* `footer`– markup for the footer of the card

All slots except `content` and `footer` can contain only one nested component.
