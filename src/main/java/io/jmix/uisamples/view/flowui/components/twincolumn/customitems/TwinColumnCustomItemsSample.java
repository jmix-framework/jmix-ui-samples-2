package io.jmix.uisamples.view.flowui.components.twincolumn.customitems;

import io.jmix.flowui.component.twincolumn.TwinColumn;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.CustomerGrade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ViewController("twin-column-custom-items")
@ViewDescriptor("twin-column-custom-items.xml")
public class TwinColumnCustomItemsSample extends StandardView {

    @ViewComponent
    protected TwinColumn<Integer> twinColumnWithList;
    @ViewComponent
    protected TwinColumn<Integer> twinColumnWithMap;
    @ViewComponent
    protected TwinColumn<CustomerGrade> twinColumnWithEnum;

    @Subscribe
    protected void onInit(InitEvent event) {
        twinColumnWithList.setItems(getItemsList());
        ComponentUtils.setItemsMap(twinColumnWithMap, getItemsMap());
        twinColumnWithEnum.setItems(CustomerGrade.class);
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
