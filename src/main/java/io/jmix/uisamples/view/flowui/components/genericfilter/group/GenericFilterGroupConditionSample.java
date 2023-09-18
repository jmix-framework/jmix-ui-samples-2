package io.jmix.uisamples.view.flowui.components.genericfilter.group;

import io.jmix.core.querycondition.PropertyConditionUtils;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.genericfilter.configuration.DesignTimeConfiguration;
import io.jmix.flowui.component.logicalfilter.GroupFilter;
import io.jmix.flowui.component.logicalfilter.LogicalFilterComponent;
import io.jmix.flowui.component.logicalfilter.LogicalFilterSupport;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.propertyfilter.SingleFilterSupport;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.CustomerGrade;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("generic-filter-group-condition")
@ViewDescriptor("generic-filter-group-condition.xml")
public class GenericFilterGroupConditionSample extends StandardView {

    @ViewComponent
    protected GenericFilter genericFilter;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected SingleFilterSupport singleFilterSupport;
    @Autowired
    protected LogicalFilterSupport logicalFilterSupport;

    @Subscribe
    protected void onInit(InitEvent event) {
        initConfiguration();
    }

    protected void initConfiguration() {
        DesignTimeConfiguration javaConfiguration = genericFilter.addConfiguration("javaConfiguration",
                "Programmatically defined configuration");
        DataLoader dataLoader = genericFilter.getDataLoader();

        GroupFilter groupFilter = createGroupFilter(dataLoader);
        PropertyFilter<Boolean> activePropertyFilter = createActivePropertyFilter(dataLoader);
        PropertyFilter<CustomerGrade> gradePropertyFilter = createGradePropertyFilter(dataLoader);
        groupFilter.add(activePropertyFilter);
        groupFilter.add(gradePropertyFilter);

        PropertyFilter<Integer> agePropertyFilter = createAgePropertyFilter(dataLoader);

        javaConfiguration.getRootLogicalFilterComponent().add(groupFilter);
        javaConfiguration.getRootLogicalFilterComponent().add(agePropertyFilter);

        activePropertyFilter.setValue(false);
        javaConfiguration.setFilterComponentDefaultValue(activePropertyFilter.getParameterName(), false);

        gradePropertyFilter.setValue(CustomerGrade.STANDARD);
        javaConfiguration.setFilterComponentDefaultValue(gradePropertyFilter.getParameterName(), CustomerGrade.STANDARD);

        agePropertyFilter.setValue(30);
        javaConfiguration.setFilterComponentDefaultValue(agePropertyFilter.getParameterName(), 30);
    }

    protected GroupFilter createGroupFilter(DataLoader dataLoader) {
        GroupFilter groupFilter = uiComponents.create(GroupFilter.class);
        groupFilter.setConditionModificationDelegated(true);
        groupFilter.setDataLoader(dataLoader);
        groupFilter.setOperation(LogicalFilterComponent.Operation.OR);

        return groupFilter;
    }

    protected PropertyFilter<Boolean> createActivePropertyFilter(DataLoader dataLoader) {
        PropertyFilter<Boolean> activePropertyFilter = uiComponents.create(PropertyFilter.class);
        activePropertyFilter.setConditionModificationDelegated(true);
        activePropertyFilter.setDataLoader(dataLoader);
        activePropertyFilter.setProperty("active");
        activePropertyFilter.setOperation(PropertyFilter.Operation.EQUAL);
        activePropertyFilter.setOperationEditable(true);

        activePropertyFilter.setParameterName(PropertyConditionUtils.generateParameterName(
                activePropertyFilter.getProperty()));
        activePropertyFilter.setValueComponent(singleFilterSupport.generateValueComponent(
                dataLoader.getContainer().getEntityMetaClass(),
                activePropertyFilter.getProperty(),
                activePropertyFilter.getOperation()
        ));

        return activePropertyFilter;
    }

    protected PropertyFilter<CustomerGrade> createGradePropertyFilter(DataLoader dataLoader) {
        PropertyFilter<CustomerGrade> gradePropertyFilter = uiComponents.create(PropertyFilter.class);
        gradePropertyFilter.setConditionModificationDelegated(true);
        gradePropertyFilter.setDataLoader(dataLoader);
        gradePropertyFilter.setProperty("grade");
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

    protected PropertyFilter<Integer> createAgePropertyFilter(DataLoader dataLoader) {
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
