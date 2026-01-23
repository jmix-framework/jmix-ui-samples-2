package io.jmix.uisamples.view.flowui.containers.dashboard.themevariant;

import com.vaadin.flow.component.dashboard.DashboardVariant;
import io.jmix.dashboard.component.JmixDashboard;
import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ViewController("dashboard-theme-variant")
@ViewDescriptor("dashboard-theme-variant.xml")
public class DashboardThemeVariantSample extends StandardView {

    @ViewComponent
    private JmixCheckboxGroup<DashboardVariant> dashboardThemeVariantCheckboxGroup;
    @ViewComponent
    private JmixDashboard dashboard;

    @Subscribe
    public void onInit(InitEvent event) {
        ComponentUtils.setItemsMap(dashboardThemeVariantCheckboxGroup, getGridThemeVariantItemsMap());
    }

    @Subscribe("dashboardThemeVariantCheckboxGroup")
    public void onDashboardThemeVariantCheckboxGroupValueChange(
            TypedValueChangeEvent<JmixCheckboxGroup<DashboardVariant>, Collection<DashboardVariant>> event) {
        if (event.getValue() == null) {
            return;
        }

        //clear
        dashboard.getElement().getThemeList().clear();

        event.getValue().forEach(dashboard::addThemeVariants);
    }

    private Map<DashboardVariant, String> getGridThemeVariantItemsMap() {
        Map<DashboardVariant, String> map = new LinkedHashMap<>();

        map.put(DashboardVariant.LUMO_ELEVATED_WIDGETS, "Elevated widgets");
        map.put(DashboardVariant.LUMO_FLAT_WIDGETS, "Flat widgets");
        map.put(DashboardVariant.LUMO_SHADED_BACKGROUND, "Shaded background");

        return map;
    }
}
