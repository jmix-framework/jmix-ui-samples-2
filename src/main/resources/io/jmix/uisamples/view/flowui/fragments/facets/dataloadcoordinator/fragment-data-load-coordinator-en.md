`dataLoadCoordinator` facet is designed for triggering data loaders and for declarative linking
    of data loaders to data containers, visual components, and host-view or fragment lifecycle events.


There are three working modes:
- **Automatic** mode is activated by `auto` attribute. A loader is triggered on `BeforeShowEvent`.
- **Manual** mode uses nested `refresh` elements that control when the data loaders must be triggered.
- **Semi-automatic** mode is a combination of **automatic** and **manual** modes. When the `auto`
        attribute is set to `true`, and there are some manually configured triggers, `DataLoadCoordinator`
        will automatically configure all loaders having no manual configuration.
