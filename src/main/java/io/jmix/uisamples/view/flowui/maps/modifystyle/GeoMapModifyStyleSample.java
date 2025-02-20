package io.jmix.uisamples.view.flowui.maps.modifystyle;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.LineStringFeature;
import io.jmix.mapsflowui.component.model.feature.PolygonFeature;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.FeatureType;
import io.jmix.mapsflowui.kit.component.model.layer.LayerStylesBuilder;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.PointStyle;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;

import java.util.List;

@ViewController(id = "geo-map-modify-style")
@ViewDescriptor(path = "geo-map-modify-style.xml")
public class GeoMapModifyStyleSample extends StandardView {

    @ViewComponent("map.vectorLayer")
    private VectorLayer vectorLayer;
    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(List.of(
                createLineStringWithModifyStyle(),
                createPolygonWithModifyStyle(),
                new LineStringFeature(Geometries.createLineString()),
                new PolygonFeature(Geometries.createPolygon())
        ));

        setupGeneralModifyStyle();
    }

    private void setupGeneralModifyStyle() {
        Style generalStyle = new PointStyle()
                .withImage(new CircleStyle()
                        .withRadius(8)
                        .withFill(new Fill("blue")))
                .build();

        vectorLayer.setModifyStyles(LayerStylesBuilder.modifyStyles(false)
                .withStyles(FeatureType.POLYGON, List.of(generalStyle))
                .withStyles(FeatureType.LINE_STRING, List.of(generalStyle))
                .build());
    }

    private LineStringFeature createLineStringWithModifyStyle() {
        return new LineStringFeature(Geometries.createLineStringStyled())
                .withModifyStyles(new PointStyle()
                        .withImage(new CircleStyle()
                                .withRadius(6)
                                .withFill(new Fill("red"))
                                .withStroke(new Stroke()
                                        .withWidth(2d)
                                        .withColor("green")))
                        .build());
    }

    private PolygonFeature createPolygonWithModifyStyle() {
        return new PolygonFeature(Geometries.createPolygonStyled())
                .withModifyStyles(new PointStyle()
                        .withImage(new CircleStyle()
                                .withRadius(7)
                                .withFill(new Fill("#63ADD0")))
                        .build());
    }
}