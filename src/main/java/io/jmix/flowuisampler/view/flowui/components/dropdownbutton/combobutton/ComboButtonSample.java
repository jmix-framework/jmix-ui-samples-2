package io.jmix.flowuisampler.view.flowui.components.dropdownbutton.combobutton;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.dropdownbutton.DropdownButtonItem;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("combo-button")
@ViewDescriptor("combo-button.xml")
public class ComboButtonSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("save")
    protected void onSaveActionPerformed(ActionPerformedEvent event) {
        notifications.show("Saved!");
    }

    @Subscribe("saveButton.saveDraftItem")
    protected void onSaveDraftItemClick(DropdownButtonItem.ClickEvent event) {
        notifications.show("Saved as draft!");
    }

    @Subscribe("saveButton.saveCopyItem")
    protected void onSaveCopyItemClick(DropdownButtonItem.ClickEvent event) {
        notifications.show("Saved as copy!");
    }

    @Subscribe("saveButton.savePublishItem")
    protected void onSavePublishItemClick(DropdownButtonItem.ClickEvent event) {
        notifications.show("Saved and published!");
    }
}
