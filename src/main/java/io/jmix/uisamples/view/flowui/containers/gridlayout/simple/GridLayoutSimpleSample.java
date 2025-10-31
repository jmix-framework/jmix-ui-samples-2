package io.jmix.uisamples.view.flowui.containers.gridlayout.simple;

import io.jmix.flowui.component.SupportsTypedValue.TypedValueChangeEvent;
import io.jmix.flowui.component.gridlayout.GridLayout;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;

import java.util.Objects;

@ViewController("grid-layout-simple")
@ViewDescriptor("grid-layout-simple.xml")
public class GridLayoutSimpleSample extends StandardView {

    @ViewComponent
    private GridLayout<?> gridLayout;

    @Subscribe("gapField")
    public void onGapFieldValueChangeEvent(TypedValueChangeEvent<TypedTextField<String>, String> event) {
        gridLayout.setGap(Objects.requireNonNullElse(event.getValue(), "var(--lumo-space-s)"));
    }

    @Subscribe("columnMinWidthField")
    public void onColumnMinWidthFieldValueChangeEvent(TypedValueChangeEvent<TypedTextField<String>, String> event) {
        gridLayout.setColumnMinWidth(Objects.requireNonNullElse(event.getValue(), "19rem"));
    }
}
