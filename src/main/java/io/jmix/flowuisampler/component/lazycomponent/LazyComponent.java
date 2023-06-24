package io.jmix.flowuisampler.component.lazycomponent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.SerializableSupplier;

public class LazyComponent extends Div {

    public LazyComponent(SerializableSupplier<? extends Component> supplier) {
        initComponent(supplier);
    }

    protected void initComponent(SerializableSupplier<? extends Component> supplier) {
        addAttachListener(event -> {
            if (getElement().getChildCount() == 0) {
                add(supplier.get());
            }
        });
    }
}
