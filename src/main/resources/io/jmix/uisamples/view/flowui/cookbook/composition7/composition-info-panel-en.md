This sample demonstrates how to inform users about changes in the whole composition including nested dialog windows.

The composition consists of the `Order` and `OrderItem` entities. When you open an order to edit, you can see the **No changes detected** message at the top of the order detail view. The message will change to **There are unsaved changes!** if you change any fields in this view, as well as if you create or remove an order item or edit an order item in its detail view.    

Implementation details:

- [Order.java]({currentPath}?tab=Order.java)
    - `Order.items` attribute is annotated with `@Composition`.

- [order-detail-view.xml]({currentPath}?tab=order-detail-view.xml)
  - The `<div id="infoPanel">` element defines the initial state of the info panel.

- [OrderDetailView.java]({currentPath}?tab=OrderDetailView.java)
  - `updateInfoPanel()` method updates the info panel when changes are detected.
  - `onChange()` event listener calls `updateInfoPanel()` when the `DataContext` of this view detects changes in its entities. It happens, for example, when the user modifies the `date` attribute or the `items` collection. However, this listener does not react on changes made in the nested `OrderItemDetailView`. This is why `updateInfoPanel()` should also be called from the `OrderItemDetailView` itself.
  - `itemsDataGridEditActionViewConfigurer()` handler passes the reference to `updateInfoPanel()` to `OrderItemDetailView`.
  
- [OrderItemDetailView.java]({currentPath}?tab=OrderItemDetailView.java)
  - `onPostSave()` event listener calls `updateInfoPanel()` after the user clicks **OK** and the changes are saved by the `DataContext` of this view. We cannot use `onChange()` event listener here because changes in this view can be canceled by the user. 
