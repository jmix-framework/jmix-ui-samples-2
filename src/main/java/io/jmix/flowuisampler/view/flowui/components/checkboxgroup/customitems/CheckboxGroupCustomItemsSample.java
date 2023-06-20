package io.jmix.flowuisampler.view.flowui.components.checkboxgroup.customitems;

import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.CustomerGrade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ViewController("checkbox-group-custom-items")
@ViewDescriptor("checkbox-group-custom-items.xml")
public class CheckboxGroupCustomItemsSample extends StandardView {

    @ViewComponent
    protected JmixCheckboxGroup<Integer> checkboxGroupWithList;
    @ViewComponent
    protected JmixCheckboxGroup<Integer> checkboxGroupWithMap;
    @ViewComponent
    protected JmixCheckboxGroup<CustomerGrade> checkboxGroupWithEnum;

    @Subscribe
    protected void onInit(InitEvent event) {
        checkboxGroupWithList.setItems(getItemsList());
        ComponentUtils.setItemsMap(checkboxGroupWithMap, getItemsMap());
        checkboxGroupWithEnum.setItems(CustomerGrade.class);
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
