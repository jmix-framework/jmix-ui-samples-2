package io.jmix.uisamples.view.flowui.maps.polygonstyle;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.PolygonStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.uisamples.view.flowui.maps.polygon.PolygonFeatures;

import java.util.List;

@ViewController("geo-map-polygon-styling")
@ViewDescriptor("geo-map-polygon-styling.xml")
public class GeoMapPolygonStyleSample extends StandardView {

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @ViewComponent("map.vectorLayer")
    private VectorLayer mapVectorLayer;

    @Subscribe
    public void onInit(final InitEvent event) {
        List<Feature> states = PolygonFeatures.getUSAStates();
        source.addAllFeatures(states);

        setupGeneralStyle();
        setupFeatureStyle(states.get(states.size() - 1));
    }

    private void setupGeneralStyle() {
        mapVectorLayer.addStyles(
                new PolygonStyle()
                        .withFill(new Fill("rgba(68, 123, 212, 0.3)"))
                        .withStroke(new Stroke()
                                .withWidth(3d)
                                .withColor("#104BA9"))
                        .build());
    }

    private void setupFeatureStyle(Feature feature) {
        feature.addStyles(
                new PolygonStyle()
                        .withFill(new Fill("rgba(0, 129, 16, 0.3)"))
                        .withStroke(new Stroke()
                                .withWidth(3d)
                                .withColor("#00C618"))
                        .build());
    }
}
