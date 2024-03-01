package io.jmix.uisamples.view.flowui.charts.dataitems.entitydataitem;

import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.EntityDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.core.Metadata;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.ValueDescription;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("entity-data-item")
@ViewDescriptor("entity-data-item.xml")
public class EntityDataItemSample extends StandardView {

    @Autowired
    protected Metadata metadata;

    @ViewComponent
    protected Chart chart;

    @Subscribe
    protected void onInit(InitEvent event) {
        ListChartItems<EntityDataItem> chartItems = new ListChartItems<>(
                new EntityDataItem(createValueDescriptionEntity(75, "Sky")),
                new EntityDataItem(createValueDescriptionEntity(7, "Shady side of pyramid")),
                new EntityDataItem(createValueDescriptionEntity(18, "Sunny side of pyramid"))
        );

        chart.withDataSet(
                new DataSet().withSource(
                        new DataSet.Source<EntityDataItem>()
                                .withDataProvider(chartItems)
                                .withCategoryField("description")
                                .withValueField("value")
                )
        );
    }

    protected ValueDescription createValueDescriptionEntity(Integer value, String description) {
        ValueDescription entity = metadata.create(ValueDescription.class);
        entity.setValue(value);
        entity.setDescription(description);
        return entity;
    }
}
