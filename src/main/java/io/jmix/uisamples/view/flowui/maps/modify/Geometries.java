package io.jmix.uisamples.view.flowui.maps.modify;

import io.jmix.maps.utils.GeometryUtils;
import org.locationtech.jts.geom.*;

public final class Geometries {

    private static final GeometryFactory factory = GeometryUtils.getGeometryFactory();

    public static LineString createLineString() {
        return factory.createLineString(
                new Coordinate[]{
                        new Coordinate(-25.0, 45.0),
                        new Coordinate(-30.0, 40.0),
                        new Coordinate(-25.0, 35.0),
                        new Coordinate(-30.0, 30.0),
                        new Coordinate(-25.0, 25.0),
                        new Coordinate(-30.0, 20.0),
                        new Coordinate(-25.0, 15.0),
                        new Coordinate(-30.0, 10.0)
                }
        );
    }

    public static Polygon createPolygon() {
        return factory.createPolygon(factory.createLinearRing(
                new Coordinate[]{
                        new Coordinate(-15, 45),
                        new Coordinate(0, 45),
                        new Coordinate(0, 35),
                        new Coordinate(-15, 35),
                        new Coordinate(-15, 45),
                }));
    }
}
