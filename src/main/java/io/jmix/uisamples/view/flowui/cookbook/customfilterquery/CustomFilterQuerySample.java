package io.jmix.uisamples.view.flowui.cookbook.customfilterquery;

import com.google.common.base.Strings;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Customer;

import java.util.HashMap;
import java.util.Map;

@ViewController("custom-filter-query")
@ViewDescriptor("custom-filter-query.xml")
public class CustomFilterQuerySample extends StandardView {

    @ViewComponent
    protected TypedTextField<String> nameFilterField;
    @ViewComponent
    protected CollectionLoader<Customer> customersDl;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        reload();
    }

    @Subscribe("nameFilterField")
    protected void onNameFilterFieldValueChange(TypedValueChangeEvent<TypedTextField<String>, String> event) {
        reload();
    }

    protected void reload() {
        String query = "select e from Customer e ";
        Map<String, Object> paramsMap = new HashMap<>();

        if (!Strings.isNullOrEmpty(nameFilterField.getTypedValue())) {
            query += "where (e.name like :name or e.lastName like :name)";
            paramsMap.put("name", "(?i)%" + nameFilterField.getTypedValue() + "%");
        }
        query += " order by e.name";

        customersDl.setQuery(query);
        customersDl.setParameters(paramsMap);
        customersDl.load();
    }
}
