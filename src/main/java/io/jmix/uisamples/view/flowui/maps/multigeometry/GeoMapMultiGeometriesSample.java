package io.jmix.uisamples.view.flowui.maps.multigeometry;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.MultiLineStringFeature;
import io.jmix.mapsflowui.component.model.feature.MultiMarkerFeature;
import io.jmix.mapsflowui.component.model.feature.MultiPolygonFeature;
import io.jmix.mapsflowui.component.model.source.SourceFeatureClickNotifier;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.maps.utils.GeometryUtils.*;

@ViewController("geo-map-multi-geometries")
@ViewDescriptor("geo-map-multi-geometries.xml")
public class GeoMapMultiGeometriesSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @ViewComponent("map.vectorSource.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addFeature(new MultiPolygonFeature(
                createMultiPolygon(MultiGeometries.getPolygons())));

        source.addFeature(new MultiLineStringFeature(
                createMultiLineString(MultiGeometries.getLineString())));

        source.addFeature(new MultiMarkerFeature(
                createMultiPoint(MultiGeometries.getPoints())));

        source.addSourceFeatureClickListener(this::onSourceFeatureClick);
    }

    private void onSourceFeatureClick(SourceFeatureClickNotifier.SourceFeatureClickEvent event) {
        Feature feature = event.getFeature();
        if (feature instanceof MultiMarkerFeature) {
            notifications.show("Washington State cities");
        } else if (feature instanceof MultiLineStringFeature) {
            notifications.show("Washington State roads");
        } else if (feature instanceof MultiPolygonFeature) {
            notifications.show("Washington State");
        }
    }
}
