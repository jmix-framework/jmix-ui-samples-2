package io.jmix.flowuisampler.view.flowui.containers.tabsheet.icons;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("tabsheet-icons")
@ViewDescriptor("tabsheet-icons.xml")
public class TabSheetIconsSample extends StandardView {

    @ViewComponent
    protected JmixTabSheet tabSheet;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        initTabSheet();
    }

    protected void initTabSheet() {
        tabSheet.add(createProfileTab(), new Span("Profile tab content"));
        tabSheet.add(createNotificationsTab(), new Span("Notifications tab content"));
        tabSheet.add(createSettingsTab(), new Span("Settings tab content"));
    }

    @Subscribe("iconControlCheckbox")
    protected void onIconControlCheckboxValueChane(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (event.getValue()) {
            tabSheet.getChildren().forEach(tab -> ((Tab) tab).addThemeVariants(TabVariant.LUMO_ICON_ON_TOP));
        } else {
            tabSheet.getChildren().forEach(tab -> tab.getElement().getThemeList().clear());
        }
    }

    protected Tab createProfileTab() {
        Tab tab = uiComponents.create(Tab.class);
        tab.add(VaadinIcon.USER.create(), new Text("Profile"));

        return tab;
    }

    protected Tab createNotificationsTab() {
        Tab tab = uiComponents.create(Tab.class);
        tab.add(VaadinIcon.BELL.create(), new Text("Notifications"));

        return tab;
    }

    protected Tab createSettingsTab() {
        Tab tab = uiComponents.create(Tab.class);
        tab.add(VaadinIcon.COG.create(), new Text("Settings"));

        return tab;
    }
}
