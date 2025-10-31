
This example demonstrates how to isolate entities when sending them from a view to a custom service. For additional information, see the [Working with Entities in UI]({docsBaseUrl}flow-ui/data/entities-in-ui.html) documentation section.

The list and detail views in this example allow users to manage the `Contract` entity which has a `status` enumeration attribute with the `DRAFT`, `APPROVED`, `REJECTED` possible values. 

The **Approve**, **Revise**, and **Reject** buttons in the detail view invoke the `ContractService` bean passing the edited `Contract` and a new status to it. The service updates the status in the entity instance and saves the changes.

The standard saving process of the view initiated by the **Save** button is intercepted by the `saveDelegate()` handler method. It also invokes the `ContractService` bean. Notice that the handler is called only if the entity has been modified since the last save.

Implementation details:

- [contract-detail-view.xml]({currentPath}?tab=contract-detail-view.xml)
  - `saveAction` is of the `detail_save` type. It does not close the view after clicking **Save**.
- [ContractDetailView.java]({currentPath}?tab=ContractDetailView.java)
  - `changeContractStatus()` method creates a copy of the edited entity and sends that copy to the service. The returned instance is merged into `DataContext`. The merged instance is ignored because it's used only by the view logic internally.
  - `saveDelegate()` handler is called by `DataContext` when the user clicks **Save**. It also creates a copy of the edited entity and sends it to the service for saving. The saved instance is returned from the service and the handler. It is merged by the `DataContext` implementation after exiting the handler.
- [ContractService.java]({currentPath}?tab=ContractService.java)
  - `changeStatus()` method updates the status of the entity instance and sends it to the `DataManager`. The updated instance is then returned to refresh the view state. If an exception occurs in the service method, the state of the entity presented in the view remains untouched, because the service operates with a copy of the entity.
- [ContractListView.java]({currentPath}?tab=ContractListView.java) defines "after close" handlers for `createAction` and `editAction` to always refresh the entities list after closing the detail view. This is required because data might be modified by the detail view while it was open.