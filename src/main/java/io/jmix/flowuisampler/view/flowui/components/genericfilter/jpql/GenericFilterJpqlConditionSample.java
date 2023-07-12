package io.jmix.flowuisampler.view.flowui.components.genericfilter.jpql;

import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.genericfilter.configuration.DesignTimeConfiguration;
import io.jmix.flowui.component.jpqlfilter.JpqlFilter;
import io.jmix.flowui.component.jpqlfilter.JpqlFilterSupport;
import io.jmix.flowui.component.propertyfilter.SingleFilterSupport;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("generic-filter-jpql-condition")
@ViewDescriptor("generic-filter-jpql-condition.xml")
public class GenericFilterJpqlConditionSample extends StandardView {

    @ViewComponent
    protected GenericFilter genericFilter;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected JpqlFilterSupport jpqlFilterSupport;
    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Subscribe
    protected void onInit(InitEvent event) {
        initConfiguration();
    }

    protected void initConfiguration() {
        DesignTimeConfiguration javaConfiguration = genericFilter.addConfiguration("javaConfiguration",
                "Programmatically defined configuration");
        DataLoader dataLoader = genericFilter.getDataLoader();

        javaConfiguration.getRootLogicalFilterComponent().add(createProductJpqlFilter(dataLoader));
    }

    protected JpqlFilter<Product> createProductJpqlFilter(DataLoader dataLoader) {
        JpqlFilter<Product> jpqlFilter = uiComponents.create(JpqlFilter.class);

        jpqlFilter.setConditionModificationDelegated(true);
        jpqlFilter.setDataLoader(dataLoader);
        jpqlFilter.setCondition("i.product.id = ?", "join {E}.items i");
        jpqlFilter.setParameterClass(Product.class);
        jpqlFilter.setLabel("Order items contains");

        jpqlFilter.setParameterName(jpqlFilterSupport.generateParameterName(
                jpqlFilter.getId().orElse(null),
                jpqlFilter.getParameterClass().getSimpleName()));
        jpqlFilter.setValueComponent(singleFilterSupport.generateValueComponent(
                dataLoader.getContainer().getEntityMetaClass(),
                jpqlFilter.hasInExpression(),
                jpqlFilter.getParameterClass()
        ));

        return jpqlFilter;
    }
}
