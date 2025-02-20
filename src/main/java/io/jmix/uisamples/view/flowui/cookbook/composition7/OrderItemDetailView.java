package io.jmix.uisamples.view.flowui.cookbook.composition7;

import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.OrderItem;

@ViewController(id = "order-item-detail-2")
@ViewDescriptor(path = "order-item-detail-view.xml")
@EditedEntityContainer("orderItemDc")
public class OrderItemDetailView extends StandardDetailView<OrderItem> {

    private Runnable updateInfoPanel;

    public void setChangeEventListener(Runnable updateInfoPanel) {
        this.updateInfoPanel = updateInfoPanel;
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPostSave(final DataContext.PostSaveEvent event) {
        if (!event.getSavedInstances().isEmpty() && updateInfoPanel != null)
            updateInfoPanel.run();
    }
}
