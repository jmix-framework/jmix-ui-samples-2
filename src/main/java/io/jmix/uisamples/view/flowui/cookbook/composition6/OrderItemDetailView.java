package io.jmix.uisamples.view.flowui.cookbook.composition6;

import com.vaadin.flow.data.provider.Query;
import io.jmix.core.DataManager;
import io.jmix.core.Sort;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.OrderItem;
import io.jmix.uisamples.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@ViewController(id = "order-item-detail-1")
@ViewDescriptor(path = "order-item-detail-view.xml")
@EditedEntityContainer("orderItemDc")
public class OrderItemDetailView extends StandardDetailView<OrderItem> {

    @Autowired
    private DataManager dataManager;

    private List<UUID> addedProductIds;

    public void setAddedProducts(List<Product> addedProducts) {
        this.addedProductIds = addedProducts.stream().map(Product::getId).toList();
    }

    @Install(to = "productField", subject = "itemsFetchCallback")
    private Stream<Product> productFieldItemsFetchCallback(final Query<Product, String> query) {
        String param = query.getFilter().orElse("");
        return dataManager.load(Product.class)
                .condition(LogicalCondition.and(
                        PropertyCondition.notInList("id", addedProductIds),
                        PropertyCondition.contains("name", param)
                ))
                .sort(Sort.by("name"))
                .firstResult(query.getOffset())
                .maxResults(query.getLimit())
                .list()
                .stream();
    }
}
