package io.jmix.uisamples.view.flowui.components.genericfilter.property;

import io.jmix.core.querycondition.PropertyConditionUtils;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.genericfilter.configuration.DesignTimeConfiguration;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.propertyfilter.SingleFilterSupport;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("generic-filter-property-condition")
@ViewDescriptor("generic-filter-property-condition.xml")
public class GenericFilterPropertyConditionSample extends StandardView {

    @ViewComponent
    protected GenericFilter genericFilter;

    @Autowired
    protected UiComponents uiComponents;
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

        javaConfiguration.getRootLogicalFilterComponent().add(createCustomerPropertyFilter(dataLoader));
        javaConfiguration.getRootLogicalFilterComponent().add(createGradePropertyFilter(dataLoader));
    }

    protected PropertyFilter<Customer> createCustomerPropertyFilter(DataLoader dataLoader) {
        PropertyFilter<Customer> customerPropertyFilter = uiComponents.create(PropertyFilter.class);

        customerPropertyFilter.setConditionModificationDelegated(true);
        customerPropertyFilter.setDataLoader(dataLoader);
        customerPropertyFilter.setProperty("customer");
        customerPropertyFilter.setOperation(PropertyFilter.Operation.EQUAL);
        customerPropertyFilter.setOperationEditable(true);

        customerPropertyFilter.setParameterName(PropertyConditionUtils.generateParameterName(
                customerPropertyFilter.getProperty()));
        customerPropertyFilter.setValueComponent(singleFilterSupport.generateValueComponent(
                dataLoader.getContainer().getEntityMetaClass(),
                customerPropertyFilter.getProperty(),
                customerPropertyFilter.getOperation()
        ));

        return customerPropertyFilter;
    }

    protected PropertyFilter<CustomerGrade> createGradePropertyFilter(DataLoader dataLoader) {
        PropertyFilter<CustomerGrade> gradePropertyFilter = uiComponents.create(PropertyFilter.class);
        gradePropertyFilter.setConditionModificationDelegated(true);
        gradePropertyFilter.setDataLoader(dataLoader);
        gradePropertyFilter.setProperty("customer.grade");
        gradePropertyFilter.setOperation(PropertyFilter.Operation.EQUAL);
        gradePropertyFilter.setOperationEditable(true);

        gradePropertyFilter.setParameterName(PropertyConditionUtils.generateParameterName(
                gradePropertyFilter.getProperty()));
        gradePropertyFilter.setValueComponent(singleFilterSupport.generateValueComponent(
                dataLoader.getContainer().getEntityMetaClass(),
                gradePropertyFilter.getProperty(),
                gradePropertyFilter.getOperation()
        ));

        return gradePropertyFilter;
    }
}
