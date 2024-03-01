package io.jmix.uisamples.view.flowui.maps.linestringstyle;

import org.locationtech.jts.geom.Coordinate;

public final class LineStringCoordinates {

    public static Coordinate[] getFirstCoordinates() {
        return new Coordinate[]{
            new Coordinate(-45.0, 45.0),
            new Coordinate(-50.0, 40.0),
            new Coordinate(-45.0, 35.0),
            new Coordinate(-50.0, 30.0),
            new Coordinate(-45.0, 25.0),
            new Coordinate(-50.0, 20.0),
            new Coordinate(-45.0, 15.0),
            new Coordinate(-50.0, 10.0),
        };
    }

    public static Coordinate[] getSecondCoordinates() {
        return new Coordinate[]{
                new Coordinate(-25.0, 45.0),
                new Coordinate(-30.0, 40.0),
                new Coordinate(-25.0, 35.0),
                new Coordinate(-30.0, 30.0),
                new Coordinate(-25.0, 25.0),
                new Coordinate(-30.0, 20.0),
                new Coordinate(-25.0, 15.0),
                new Coordinate(-30.0, 10.0),
        };
    }
}
