package io.jmix.uisamples.view.flowui.cookbook.composition6;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Order;
import io.jmix.uisamples.entity.OrderItem;
import io.jmix.uisamples.entity.Product;

import java.util.Comparator;
import java.util.List;

@ViewController(id = "order-detail-1")
@ViewDescriptor(path = "order-detail-view.xml")
@EditedEntityContainer("orderDc")
public class OrderDetailView extends StandardDetailView<Order> {

    @ViewComponent
    private CollectionPropertyContainer<OrderItem> itemsDc;
    @ViewComponent
    private JmixButton upButton;
    @ViewComponent
    private JmixButton downButton;

    // Set initial sortValue
    @Install(to = "itemsDataGrid.createAction", subject = "initializer")
    private void itemsDataGridCreateActionInitializer(final OrderItem orderItem) {
        Integer maxValue = itemsDc.getItems().stream()
                .max(Comparator.comparing(OrderItem::getRowNum))
                .map(OrderItem::getRowNum)
                .orElse(0);
        orderItem.setRowNum(maxValue + 1);
    }

    // Enable and disable up/down buttons according to the position of selected item
    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    public void onItemsDcItemChange(final InstanceContainer.ItemChangeEvent<OrderItem> event) {
        upButton.setEnabled(false);
        downButton.setEnabled(false);

        OrderItem orderItem = event.getItem();
        if (orderItem != null) {
            int index = itemsDc.getItemIndex(orderItem);
            if (index > 0) {
                upButton.setEnabled(true);
            }
            if (index < itemsDc.getItems().size() - 1) {
                downButton.setEnabled(true);
            }
        }
    }

    // Exchange sortValue between neighbour items and reorder table after clicking Up
    @Subscribe(id = "upButton", subject = "clickListener")
    public void onUpButtonClick(final ClickEvent<JmixButton> event) {
        OrderItem currentItem = itemsDc.getItemOrNull();
        if (currentItem != null) {
            currentItem.setRowNum(currentItem.getRowNum() - 1);

            OrderItem prevItem = itemsDc.getItems().get(itemsDc.getItemIndex(currentItem) - 1);
            prevItem.setRowNum(prevItem.getRowNum() + 1);

            itemsDc.getDisconnectedItems().sort(Comparator.comparing(OrderItem::getRowNum));
        }
    }

    // Exchange sortValue between neighbour items and reorder table after clicking Down
    @Subscribe(id = "downButton", subject = "clickListener")
    public void onDownButtonClick(final ClickEvent<JmixButton> event) {
        OrderItem currentItem = itemsDc.getItemOrNull();
        if (currentItem != null) {
            currentItem.setRowNum(currentItem.getRowNum() + 1);

            OrderItem nextItem = itemsDc.getItems().get(itemsDc.getItemIndex(currentItem) + 1);
            nextItem.setRowNum(nextItem.getRowNum() - 1);

            itemsDc.getDisconnectedItems().sort(Comparator.comparing(OrderItem::getRowNum));
        }
    }

    // Pass already added products to opened OrderItemDetailView to filter them out from options
    @Install(to = "itemsDataGrid.createAction", subject = "viewConfigurer")
    private void itemsDataGridCreateActionViewConfigurer(final OrderItemDetailView orderItemDetailView) {
        List<Product> addedProducts = itemsDc.getItems().stream()
                .map(OrderItem::getProduct)
                .toList();
        orderItemDetailView.setAddedProducts(addedProducts);
    }

    // Pass already added products to opened OrderItemDetailView to filter them out from options.
    // When editing an item, the product selected in this item should also be available,
    // which is achieved by the `filter()` operation.
    @Install(to = "itemsDataGrid.editAction", subject = "viewConfigurer")
    private void itemsDataGridEditActionViewConfigurer(final OrderItemDetailView orderItemDetailView) {
        List<Product> addedProducts = itemsDc.getItems().stream()
                .filter(orderItem -> orderItem != itemsDc.getItem())
                .map(OrderItem::getProduct)
                .toList();
        orderItemDetailView.setAddedProducts(addedProducts);
    }
}
