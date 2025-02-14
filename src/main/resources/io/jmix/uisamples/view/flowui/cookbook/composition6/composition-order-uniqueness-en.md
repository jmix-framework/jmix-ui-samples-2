This sample demonstrates how to implement ordering of composition items and ensure they have unique values within the root entity.

The composition consists of the `Order` and `OrderItem` entities. If you open an order for editing, you can see the data grid with items. Each row has **#** column. You can move the item up and down with the corresponding buttons. When creating a new item or editing an existing one, the dropdown for selecting a product for the item contains only options that have not yet been added to the order.

Implementation details:

- [Order.java]({currentPath}?tab=Order.java)
    - `Order.items` attribute is annotated with `@Composition`.

- [OrderItem.java]({currentPath}?tab=OrderItem.java)
  - `OrderItem.sortValue` attribute stores the position of the item within the order.

- [OrderDetailView.java]({currentPath}?tab=OrderDetailView.java)
  - `itemsDataGridCreateActionInitializer()` handler sets initial value to `sortValue` by incrementing a maximum value among existing items.
  - `onItemsDcItemChange()` event handler enables and disables **Up** and **Down** buttons according to the position of the selected item.
  - `onUpButtonClick()` and `onDownButtonClick()` event listeners exchange `sortValue` between neighbour items and reorder table.
  - `itemsDataGridCreateActionViewConfigurer()` and `itemsDataGridEditActionViewConfigurer()` handlers pass a list of products already selected in the order items to `OrderItemDetailView`. This list is used to filter out already selected products from available options and thus ensure uniqueness of items in the order.

- [OrderItemDetailView.java]({currentPath}?tab=OrderItemDetailView.java)
  - `productFieldItemsFetchCallback()` handler loads available products according to the user input and the list of already added to the order products.
