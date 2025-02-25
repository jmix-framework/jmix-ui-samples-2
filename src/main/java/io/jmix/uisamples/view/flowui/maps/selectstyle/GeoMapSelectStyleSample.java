package io.jmix.uisamples.view.flowui.maps.selectstyle;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import io.jmix.mapsflowui.kit.component.model.feature.FeatureType;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.PolygonStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.uisamples.view.flowui.maps.polygon.PolygonFeatures;

import java.util.List;

@ViewController(id = "geo-map-select-style")
@ViewDescriptor(path = "geo-map-select-style.xml")
public class GeoMapSelectStyleSample extends StandardView {

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @ViewComponent("map.vectorLayer")
    private VectorLayer mapVectorLayer;

    @Subscribe
    public void onInit(final InitEvent event) {
        List<Feature> states = PolygonFeatures.getUSAStates();
        source.addAllFeatures(states);

        setupGeneralSelectStyle();
        setupSelectStyle(states.get(states.size() - 1));
    }

    private void setupGeneralSelectStyle() {
        mapVectorLayer.addSelectStyles(FeatureType.POLYGON,
                new PolygonStyle()
                        .withFill(new Fill("rgba(68, 123, 212, 0.3)"))
                        .withStroke(new Stroke()
                                .withWidth(3d)
                                .withColor("#104BA9"))
                        .build());
    }

    private void setupSelectStyle(Feature feature) {
        feature.addSelectStyles(
                new PolygonStyle()
                        .withFill(new Fill("rgba(0, 129, 16, 0.3)"))
                        .withStroke(new Stroke()
                                .withWidth(3d)
                                .withColor("#00C618"))
                        .build());
    }
}