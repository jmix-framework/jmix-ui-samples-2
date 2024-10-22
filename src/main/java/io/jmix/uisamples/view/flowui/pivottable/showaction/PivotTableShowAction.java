package io.jmix.uisamples.view.flowui.pivottable.showaction;

import io.jmix.core.Messages;
import io.jmix.flowui.Actions;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.pivottableflowui.action.PivotTableViewBuilder;
import io.jmix.pivottableflowui.action.ShowPivotTableAction;
import io.jmix.pivottableflowui.kit.component.model.Renderer;
import io.jmix.pivottableflowui.kit.component.model.Renderers;
import io.jmix.uisamples.entity.TipInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@ViewController("pivottable-show-action")
@ViewDescriptor("pivottable-show-action.xml")
public class PivotTableShowAction extends StandardView {

    @Autowired
    private Actions actions;
    @ViewComponent
    private DataGrid<TipInfo> tipInfoesDataGrid;
    @ViewComponent
    private JmixButton customShowPivotTableActionButton;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onInit(final InitEvent event) {
        ShowPivotTableAction<TipInfo> showPivotTableAction = actions.create(ShowPivotTableAction.ID);
        showPivotTableAction.setTarget(tipInfoesDataGrid);
        customShowPivotTableActionButton.setAction(showPivotTableAction);
        customShowPivotTableActionButton.setText(messages.getMessage(getClass(), "customShowActionButton.text"));

        showPivotTableAction.addActionPerformedListener(actionPerformedEvent -> {
            PivotTableViewBuilder builder = getApplicationContext().getBean(
                    PivotTableViewBuilder.class, tipInfoesDataGrid);
            Renderers renderers = new Renderers();
            renderers.setSelectedRenderer(Renderer.TABLE);
            renderers.setRenderers(List.of(Renderer.TABLE, Renderer.TABLE_BAR_CHART, Renderer.HEATMAP,
                    Renderer.ROW_HEATMAP, Renderer.COL_HEATMAP));

            builder.withIncludedProperties(Arrays.asList("sex", "smoker", "day", "time"))
                    .withRows(Arrays.asList("sex", "smoker"))
                    .withColumns(Arrays.asList("day", "time"))
                    .withRenderers(renderers)
                    .withItems(tipInfoesDataGrid.getItems().getItems())
                    .show();
        });
    }
}