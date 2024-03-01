package io.jmix.uisamples.view.flowui.charts.series.scatter.simple;

import io.jmix.core.LoadContext;
import io.jmix.core.Metadata;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@ViewController("scatter-series-simple")
@ViewDescriptor("scatter-series-simple.xml")
public class ScatterSeriesSimpleSample extends StandardView {

    @Autowired
    protected Metadata metadata;

    @Install(to = "pointDl", target = Target.DATA_LOADER)
    protected List<Point> pointDlLoadDelegate(LoadContext<Point> loadContext) {
        return Stream.of(
                        createPoint(10.0, 8.04),
                        createPoint(8.07, 6.95),
                        createPoint(13.0, 7.58),
                        createPoint(9.05, 8.81),
                        createPoint(11.0, 8.33),
                        createPoint(14.0, 7.66),
                        createPoint(13.4, 6.81),
                        createPoint(10.0, 6.33),
                        createPoint(14.0, 8.96),
                        createPoint(12.5, 6.82),
                        createPoint(9.15, 7.2),
                        createPoint(11.5, 7.2),
                        createPoint(3.03, 4.23),
                        createPoint(12.2, 7.83),
                        createPoint(2.02, 4.47),
                        createPoint(1.05, 3.33),
                        createPoint(4.05, 4.96),
                        createPoint(6.03, 7.24),
                        createPoint(12.0, 6.26),
                        createPoint(12.0, 8.84),
                        createPoint(7.08, 5.82),
                        createPoint(5.02, 5.68)
                )
                .sorted(Comparator.comparingDouble(Point::getX))
                .toList();
    }

    protected Point createPoint(Double x, Double y) {
        Point point = metadata.create(Point.class);
        point.setX(x);
        point.setY(y);
        return point;
    }
}
