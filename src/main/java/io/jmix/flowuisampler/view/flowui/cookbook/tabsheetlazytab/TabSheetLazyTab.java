package io.jmix.flowuisampler.view.flowui.cookbook.tabsheetlazytab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.component.lazycomponent.LazyComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("tabsheet-lazy-tab")
@ViewDescriptor("tabsheet-lazy-tab.xml")
public class TabSheetLazyTab extends StandardView {

    @ViewComponent
    protected JmixTabSheet tabSheet;
    @ViewComponent
    protected Span spanInfo;

    @Autowired
    protected UiComponents uiComponents;

    @Subscribe
    protected void onInit(InitEvent event) {
        tabSheet.add("LazyTab 1", new LazyComponent(this::getLazyTabContent));
        tabSheet.add("LazyTab 2", new LazyComponent(this::getLazyTabContent));

        checkComponents();
        tabSheet.addSelectedChangeListener(e -> checkComponents());
    }

    protected Component getLazyTabContent() {
        VerticalLayout content = uiComponents.create(VerticalLayout.class);

        Span info = uiComponents.create(Span.class);
        info.setText("Lazy Span");

        TypedTextField<String> textField = uiComponents.create(TypedTextField.class);
        textField.setLabel("Lazy textField");

        JmixButton button = uiComponents.create(JmixButton.class);
        button.setText("Lazy button");

        content.add(info, textField, button);
        return content;
    }

    protected void checkComponents() {
        StringBuilder sb = new StringBuilder("Tab 1 = ");
        List<Component> tab1Content = tabSheet.getContentByTab(tabSheet.getTabAt(0)).getChildren().toList();
        sb.append(tab1Content.isEmpty() ? "null" : "true");

        sb.append(", LazyTab 1 = ");
        List<Component> tab2Content = tabSheet.getContentByTab(tabSheet.getTabAt(1)).getChildren().toList();
        sb.append(tab2Content.isEmpty() ? "null" : "true");

        sb.append(", LazyTab 2 = ");
        List<Component> tab3Content = tabSheet.getContentByTab(tabSheet.getTabAt(2)).getChildren().toList();
        sb.append(tab3Content.isEmpty() ? "null" : "true");

        spanInfo.setText(sb.toString());
    }
}
