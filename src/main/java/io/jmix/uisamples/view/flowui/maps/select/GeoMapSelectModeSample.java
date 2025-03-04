package io.jmix.uisamples.view.flowui.maps.select;

import com.vaadin.flow.data.selection.SelectionEvent;
import io.jmix.core.LoadContext;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.HasGeoObjectSelect.GeoObjectSelectEvent;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import io.jmix.uisamples.bean.MountainService;
import io.jmix.uisamples.entity.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController(id = "geo-map-select-mode")
@ViewDescriptor(path = "geo-map-select-mode.xml")
public class GeoMapSelectModeSample extends StandardView {

    @ViewComponent
    private DataGrid<Marker> mountainsDataGrid;

    @ViewComponent("map.vectorLayer.source")
    private DataVectorSource<Marker> source;

    @Autowired
    private MountainService mountainService;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.setAttributions(List.of("<a href=\"https://www.flaticon.com/free-icons/rocky-mountains\"" +
                " target=\"_blank\">Icons from Flaticon.</a>"));
    }

    @Install(to = "markersDl", target = Target.DATA_LOADER)
    private List<Marker> markersDlLoadDelegate(final LoadContext loadContext) {
        return mountainService.fetchMountains();
    }

    @Install(to = "map.vectorLayer.source", subject = "styleProvider")
    private Style mapVectorLayerSourceStyleProvider(final Marker marker) {
        return new Style()
                .withImage(new IconStyle()
                        .withSrc("icons/maps/mountain.png")
                        .withAnchor(new Anchor(0.5, 0.95))
                        .withScale(0.085))
                .withText(new TextStyle()
                        .withText(marker.getName())
                        .withOffsetY(5)
                        .withFont("bold 15px sans-serif"));
    }

    @Install(to = "map.vectorLayer.source", subject = "selectStyleProvider")
    private List<Style> mapVectorLayerSourceSelectStyleProvider(final Marker marker) {
        return List.of(
                new Style()
                        .withImage(new IconStyle()
                                .withSrc("icons/maps/mountain-selected.png")
                                .withAnchor(new Anchor(0.5, 0.95))
                                .withScale(0.085))
                        .withText(new TextStyle()
                                .withText(marker.getName())
                                .withOffsetY(5)
                                .withFill(new Fill("#024C68"))
                                .withFont("bold 15px sans-serif"))
        );
    }

    @Subscribe("map.vectorLayer.source")
    public void onSourceGeoObjectSelect(final GeoObjectSelectEvent<Marker> event) {
        if (event.isFromClient()) {
            mountainsDataGrid.deselectAll();
            mountainsDataGrid.select(source.getSelectedGeoObjects());
        }
    }

    @Subscribe("mountainsDataGrid")
    public void onMountainsDataGridSelection(final SelectionEvent<DataGrid<Marker>, Marker> event) {
        if (event.isFromClient()) {
            source.deselectAllGeoObjects();
            source.selectGeoObjects(event.getAllSelectedItems());
        }
    }
}