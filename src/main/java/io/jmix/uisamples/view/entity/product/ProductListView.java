package io.jmix.uisamples.view.entity.product;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Product;

@ViewController("Product.list")
@ViewDescriptor("product-list-view.xml")
@LookupComponent("productsDataGrid")
@DialogMode(width = "50em")
public class ProductListView extends StandardListView<Product> {
}
