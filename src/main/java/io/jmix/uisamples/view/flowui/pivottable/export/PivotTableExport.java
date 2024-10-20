package io.jmix.uisamples.view.flowui.pivottable.export;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.component.PivotTable;
import io.jmix.pivottableflowui.export.PivotTableExcelExporter;
import io.jmix.pivottableflowui.export.PivotTableExporter;
import io.jmix.pivottableflowui.export.PivotTableExporterImpl;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("pivottable-export")
@ViewDescriptor("pivottable-export.xml")
public class PivotTableExport extends StandardView {

    @ViewComponent
    private PivotTable<?> pivotTable;

    @Autowired
    private PivotTableExcelExporter pivotTableExcelExporter;

    private PivotTableExporter pivotTableExport;

    @Subscribe
    protected void onInit(InitEvent event) {
        pivotTableExport = new PivotTableExporterImpl(pivotTable, pivotTableExcelExporter);
    }

    @Subscribe(id = "exportButton", subject = "clickListener")
    public void onExportButtonClick(final ClickEvent<JmixButton> event) {
        pivotTableExport.exportTableToXls();
    }
}