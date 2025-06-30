package io.jmix.uisamples.view.flowui.maps.projection;

import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.feature.MarkerFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;

@ViewController(id = "geo-map-custom-projection")
@ViewDescriptor(path = "geo-map-custom-projection.xml")
public class GeoMapCustomProjectionSample extends StandardView {

    @ViewComponent
    private GeoMap map;

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        map.setProjection(() -> "EPSG:27700");

        source.addFeature(new MarkerFeature(GeometryUtils.createPoint(530000, 180000)));
    }
}