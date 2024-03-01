package io.jmix.uisamples.view.flowui.maps.heatmap;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import static io.jmix.maps.utils.GeometryUtils.createPoint;

@ViewController("geo-map-heatmap")
@ViewDescriptor("geo-map-heatmap.xml")
public class GeoMapHeatmapSample extends StandardView {

    private static final Random random = new Random();

    @ViewComponent("map.heatmapLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(
                Arrays.stream(HeatPoints.getSecondCoordinates())
                        .map(c -> new PointFeature(createPoint(c.getX(), c.getY()))
                                .withProperty("weight", random.nextDouble(0.1, 1)))
                        .collect(Collectors.toList()));
    }
}
