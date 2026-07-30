package io.jmix.uisamples.view.flowui.components.checkboxgroup.customitems;

import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.CustomerGrade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ViewController("checkbox-group-custom-items")
@ViewDescriptor("checkbox-group-custom-items.xml")
public class CheckboxGroupCustomItemsSample extends StandardView {

    // tag::items-list-field[] sample-hide
    @ViewComponent
    protected JmixCheckboxGroup<Integer> checkboxGroupWithList;
    // end::items-list-field[] sample-hide
    // tag::items-map-field[] sample-hide
    @ViewComponent
    protected JmixCheckboxGroup<Integer> checkboxGroupWithMap;
    // end::items-map-field[] sample-hide
    // tag::items-enum-field[] sample-hide
    @ViewComponent
    protected JmixCheckboxGroup<CustomerGrade> checkboxGroupWithEnum;
    // end::items-enum-field[] sample-hide

    @Subscribe
    protected void onInit(InitEvent event) {
        // tag::items-list-set[] sample-hide
        checkboxGroupWithList.setItems(getItemsList());
        // end::items-list-set[] sample-hide
        // tag::items-map-set[] sample-hide
        ComponentUtils.setItemsMap(checkboxGroupWithMap, getItemsMap());
        // end::items-map-set[] sample-hide
        // tag::items-enum-set[] sample-hide
        checkboxGroupWithEnum.setItems(CustomerGrade.class);
        // end::items-enum-set[] sample-hide
    }

    // tag::items-list-source[] sample-hide
    protected List<Integer> getItemsList() {
        return List.of(2, 4, 5, 7);
    }
    // end::items-list-source[] sample-hide

    // tag::items-map-source[] sample-hide
    protected Map<Integer, String> getItemsMap() {
        Map<Integer, String> itemsMap = new LinkedHashMap<>();
        itemsMap.put(2, "Two");
        itemsMap.put(4, "Four");
        itemsMap.put(5, "Five");
        itemsMap.put(7, "Seven");
        return itemsMap;
    }
    // end::items-map-source[] sample-hide
}
