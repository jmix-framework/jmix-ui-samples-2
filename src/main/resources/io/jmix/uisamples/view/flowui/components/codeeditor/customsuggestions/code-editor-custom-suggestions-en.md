The `codeEditor` component supports the ability to set your own autocompletions. You can add autocompletion by setting
the `suggester` property in the component. This can be done eather declaratively using the `@Install` annotation or
programmatically using the `CodeEditor#setSuggester(Suggester)` method.

In addition, it is possible to define a regular expression that will open a popup window with autocompletions. Any
entered sequence of characters that satisfies the set regular expression will initiate the opening of popup window. In
this example, in addition to the standard keyboard shortcut, the popup window with autocompletions can be opened after
entering *the dot character* (`.`).
