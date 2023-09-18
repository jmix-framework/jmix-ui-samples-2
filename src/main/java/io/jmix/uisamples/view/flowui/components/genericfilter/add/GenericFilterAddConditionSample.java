package io.jmix.uisamples.view.flowui.components.genericfilter.add;

import io.jmix.core.querycondition.PropertyConditionUtils;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.propertyfilter.SingleFilterSupport;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("generic-filter-add-condition")
@ViewDescriptor("generic-filter-add-condition.xml")
public class GenericFilterAddConditionSample extends StandardView {

    @ViewComponent
    protected GenericFilter genericFilter;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        initConfiguration();
    }

    protected void initConfiguration() {
        DataLoader dataLoader = genericFilter.getDataLoader();
        PropertyFilter<Integer> agePropertyFilter = craeteAgePropertyFilter(dataLoader);

        genericFilter.getCurrentConfiguration().getRootLogicalFilterComponent().add(agePropertyFilter);
        genericFilter.setCurrentConfiguration(genericFilter.getCurrentConfiguration());
    }

    protected PropertyFilter<Integer> craeteAgePropertyFilter(DataLoader dataLoader) {
        PropertyFilter<Integer> agePropertyFilter = uiComponents.create(PropertyFilter.class);
        agePropertyFilter.setConditionModificationDelegated(true);
        agePropertyFilter.setDataLoader(dataLoader);
        agePropertyFilter.setProperty("age");
        agePropertyFilter.setOperation(PropertyFilter.Operation.GREATER_OR_EQUAL);
        agePropertyFilter.setOperationEditable(true);

        agePropertyFilter.setParameterName(PropertyConditionUtils.generateParameterName(
                agePropertyFilter.getProperty()));
        agePropertyFilter.setValueComponent(singleFilterSupport.generateValueComponent(
                dataLoader.getContainer().getEntityMetaClass(),
                agePropertyFilter.getProperty(),
                agePropertyFilter.getOperation()
        ));

        return agePropertyFilter;
    }
}
