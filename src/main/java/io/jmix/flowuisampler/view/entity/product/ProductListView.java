package io.jmix.flowuisampler.view.entity.product;

import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Product;

@ViewController("sampler_Product.list")
@ViewDescriptor("product-list-view.xml")
@LookupComponent("productsDataGrid")
@DialogMode(width = "50em")
public class ProductListView extends StandardListView<Product> {
}
