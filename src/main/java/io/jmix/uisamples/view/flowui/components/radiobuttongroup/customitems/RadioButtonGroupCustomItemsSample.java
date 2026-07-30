package io.jmix.uisamples.view.flowui.components.radiobuttongroup.customitems;

import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.CustomerGrade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ViewController("radio-button-group-custom-items")
@ViewDescriptor("radio-button-group-custom-items.xml")
public class RadioButtonGroupCustomItemsSample extends StandardView {

    // tag::items-list-field[] sample-hide
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> radioButtonGroupWithList;
    // end::items-list-field[] sample-hide
    // tag::items-map-field[] sample-hide
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> radioButtonGroupWithMap;
    // end::items-map-field[] sample-hide
    // tag::items-enum-field[] sample-hide
    @ViewComponent
    protected JmixRadioButtonGroup<CustomerGrade> radioButtonGroupWithEnum;
    // end::items-enum-field[] sample-hide

    @Subscribe
    protected void onInit(InitEvent event) {
        // tag::items-list-init[] sample-hide
        radioButtonGroupWithList.setItems(getItemsList());
        // end::items-list-init[] sample-hide
        // tag::items-map-init[] sample-hide
        ComponentUtils.setItemsMap(radioButtonGroupWithMap, getItemsMap());
        // end::items-map-init[] sample-hide
        // tag::items-enum-init[] sample-hide
        radioButtonGroupWithEnum.setItems(CustomerGrade.class);
        // end::items-enum-init[] sample-hide
    }

    // tag::items-list-method[] sample-hide
    protected List<Integer> getItemsList() {
        return List.of(2, 4, 5, 7);
    }
    // end::items-list-method[] sample-hide

    // tag::items-map-method[] sample-hide
    protected Map<Integer, String> getItemsMap() {
        Map<Integer, String> itemsMap = new LinkedHashMap<>();
        itemsMap.put(2, "Two");
        itemsMap.put(4, "Four");
        itemsMap.put(5, "Five");
        itemsMap.put(7, "Seven");
        return itemsMap;
    }
    // end::items-map-method[] sample-hide
}
