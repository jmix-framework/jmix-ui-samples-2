package io.jmix.uisamples.view.flowui.pivottable.sorterfunction;

import io.jmix.core.MessageTools;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.kit.component.model.JsFunction;
import io.jmix.uisamples.entity.TipInfo;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("pivot-table-sorter-function")
@ViewDescriptor("pivot-table-sorter-function.xml")
public class PivotTableSorterFunction extends StandardView {

    @ViewComponent
    protected PivotTable<TipInfo> pivotTable;

    @Autowired
    private Metadata metadata;
    @Autowired
    private MessageTools messageTools;

    @Subscribe
    public void onInit(final InitEvent event) {
        MetaClass metaClass = metadata.getClass(TipInfo.class);
        String sizeCaption = messageTools.getPropertyCaption(metaClass, "size");

        String sortersFunction = """
                    sortersFunction(property) {
                        if (property == "%s") {
                            return pivotTable.$jQuery.pivotUtilities.sortAs([6,5,4,3,2,1]);
                        }
                    }
                """.formatted(sizeCaption);

        pivotTable.setSortersFunction(new JsFunction(sortersFunction));
    }
}
