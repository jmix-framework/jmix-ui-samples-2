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

    @ViewComponent
    protected JmixRadioButtonGroup<Integer> radioButtonGroupWithList;
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> radioButtonGroupWithMap;
    @ViewComponent
    protected JmixRadioButtonGroup<CustomerGrade> radioButtonGroupWithEnum;

    @Subscribe
    protected void onInit(InitEvent event) {
        radioButtonGroupWithList.setItems(getItemsList());
        ComponentUtils.setItemsMap(radioButtonGroupWithMap, getItemsMap());
        radioButtonGroupWithEnum.setItems(CustomerGrade.class);
    }

    protected List<Integer> getItemsList() {
        return List.of(2, 4, 5, 7);
    }

    protected Map<Integer, String> getItemsMap() {
        Map<Integer, String> itemsMap = new LinkedHashMap<>();
        itemsMap.put(2, "Two");
        itemsMap.put(4, "Four");
        itemsMap.put(5, "Five");
        itemsMap.put(7, "Seven");
        return itemsMap;
    }
}
