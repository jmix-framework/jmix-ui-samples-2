package io.jmix.uisamples.view.flowui.maps.pointstyle;

import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.model.feature.MarkerFeature;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.Padding;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.MarkerStyle;
import io.jmix.mapsflowui.kit.component.model.style.PointStyle;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.image.RegularShape;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;

import java.util.List;

@ViewController("geo-map-point-styling")
@ViewDescriptor("geo-map-point-styling.xml")
public class GeoMapPointStylingSample extends StandardView {

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(List.of(
                createStyledMarker(),
                createPointWithCustomIcon(),
                createPointWithText(),
                createPointWithCustomShape()
        ));
    }

    private MarkerFeature createStyledMarker() {
        return new MarkerFeature(GeometryUtils.createPoint(-70, 30))
                .withStyles(
                        MarkerStyle.createDefaultStyle()
                                .withText(new TextStyle()
                                        .withText("Marker with\ntext")
                                        .withFont("bold 15px sans-serif")
                                        .withOffsetY(25)));
    }

    private PointFeature createPointWithCustomIcon() {
        return new PointFeature(GeometryUtils.createPoint(-50, 30))
                .withStyles(
                        new PointStyle()
                                .withImage(new IconStyle()
                                        .withSrc("icons/jmix-icon.png")
                                        .withScale(0.08)
                                        .withAnchor(new Anchor(0.5, 0.95)))
                                .build());
    }

    private PointFeature createPointWithText() {
        return new PointFeature(GeometryUtils.createPoint(-30, 30))
                .withStyles(
                        new PointStyle()
                                .withText(new TextStyle()
                                        .withText("Only text")
                                        .withFont("bold 15px sans-serif")
                                        .withFill(new Fill("#FFCF73"))
                                        .withPadding(new Padding(5, 5, 5, 5))
                                        .withBackgroundFill(new Fill("rgba(69, 115, 213, 0.5)")))
                                .build());
    }

    private PointFeature createPointWithCustomShape() {
        return new PointFeature(GeometryUtils.createPoint(-10, 30))
                .withStyles(
                        new PointStyle()
                                .withImage(new RegularShape()
                                        .withPoints(3)
                                        .withRadius(10)
                                        .withRotation(Math.PI / 4)
                                        .withAngle(0d)
                                        .withFill(new Fill("#FFF400"))
                                        .withStroke(new Stroke()
                                                .withColor("#620CAC")
                                                .withWidth(2d)))
                                .build());
    }
}
