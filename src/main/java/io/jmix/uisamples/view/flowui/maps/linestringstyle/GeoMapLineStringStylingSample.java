package io.jmix.uisamples.view.flowui.maps.linestringstyle;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.LineStringFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.style.LineStringStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;

import java.util.List;

import static io.jmix.maps.utils.GeometryUtils.createLineString;

@ViewController("geo-map-line-string-styling")
@ViewDescriptor("geo-map-line-string-styling.xml")
public class GeoMapLineStringStylingSample extends StandardView {

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(List.of(
                new LineStringFeature(createLineString(LineStringCoordinates.getFirstCoordinates())),
                createStyledLineString()
        ));
    }

    private LineStringFeature createStyledLineString() {
        return new LineStringFeature(createLineString(LineStringCoordinates.getSecondCoordinates()))
                .withStyles(
                        new LineStringStyle()
                                .withStroke(new Stroke()
                                        .withWidth(6d)
                                        .withColor("#39E639")
                                        .withLineDash(List.of(20d, 10d)))
                                .build(),
                        new LineStringStyle()
                                .withStroke(new Stroke()
                                        .withWidth(3d)
                                        .withColor("#FF4040")
                                        .withLineDash(List.of(20d, 10d)))
                                .build());
    }
}
