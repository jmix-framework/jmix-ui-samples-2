package io.jmix.uisamples.view.flowui.pivottable.sorterfunction;

import io.jmix.core.Messages;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.JsFunction;
import io.jmix.uisamples.entity.TipInfo;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("pivottable-sorter-function")
@ViewDescriptor("pivottable-sorter-function.xml")
public class PivotTableSorterFunction extends StandardView {

    @ViewComponent
    protected PivotTable<TipInfo> pivotTable;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        String sortersFunction =
                """
                    sortersFunction(property) {
                        if (property == "%s") {
                            return pivotTable.$jQuery.pivotUtilities.sortAs([6,5,4,3,2,1]);
                        }
                    }
                """;
        pivotTable.setSortersFunction(new JsFunction(sortersFunction.formatted(
                messages.getMessage("io.jmix.uisamples.entity/TipInfo.size"))));
    }
}