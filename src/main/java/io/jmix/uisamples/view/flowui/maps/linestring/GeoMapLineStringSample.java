package io.jmix.uisamples.view.flowui.maps.linestring;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.LineStringFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.style.LineStringStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;

import static io.jmix.maps.utils.GeometryUtils.createLineString;

@ViewController("geo-map-line-string")
@ViewDescriptor("geo-map-line-string.xml")
public class GeoMapLineStringSample extends StandardView {

    @ViewComponent("map.vectorSource.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addFeature(
                new LineStringFeature(createLineString(getLineStringCoordinates()))
                        .withStyles(
                                new LineStringStyle()
                                        .withStroke(new Stroke()
                                                .withWidth(4D)
                                                .withColor("#228D00")
                                                .withLineDash(List.of(15d, 5d)))
                                        .build()));
    }

    private Coordinate[] getLineStringCoordinates() {
        return new Coordinate[]{
                new Coordinate(112.44633374057992, 34.64677054125742),
                new Coordinate(108.95369303573241, 34.26772302998699),
                new Coordinate(102.63082975258553, 37.92738199394276),
                new Coordinate(95.8166667, 40.5003888),
                new Coordinate(89.18277506194964, 42.96512075271956),
                new Coordinate(81.31652897834161, 43.920456331536634),
                new Coordinate(70.92766224445013, 40.53246130916595),
                new Coordinate(66.96506356330353, 39.655075255790685),
                new Coordinate(62.17164082909909, 37.662329795963856),
                new Coordinate(54.03743924787759, 35.951252553794326),
                new Coordinate(44.57546730296757, 33.10006105462993),
                new Coordinate(40.750792348390235, 34.73844661139756),};
    }
}
