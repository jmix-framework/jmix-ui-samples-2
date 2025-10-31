The `header` element in the `Card` component allows you to specify custom markup for the header. It replaces the values
of `title` and `subtitle`.

The following list shows the display priorities of header settings:

1. `header`
2. `title` and `subtitle` nested elements in `card`
3. `title` and `subtitle` attributes of `card`

According to this list, when multiple header settings are specified at the same time, the one with the highest priority
will be displayed.
