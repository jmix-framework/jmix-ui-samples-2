package io.jmix.flowuisampler.view.flowui.components.numberfield.dataaware;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.textfield.NumberField;
import io.jmix.core.Metadata;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("number-field-dataaware")
@ViewDescriptor("number-field-dataaware.xml")
public class NumberFieldDataawareSample extends StandardView {

    @ViewComponent
    protected InstanceContainer<Point> pointDc;
    @ViewComponent
    protected Paragraph pValue;

    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInit(InitEvent event) {
        Point point = metadata.create(Point.class);
        point.setX(31.32);
        pointDc.setItem(point);
    }

    @Subscribe("numberField")
    protected void onNumberFieldValueChange(ComponentValueChangeEvent<NumberField, Number> changeEvent) {
        pValue.setText(String.valueOf(pointDc.getItem().getX()));
    }
}
