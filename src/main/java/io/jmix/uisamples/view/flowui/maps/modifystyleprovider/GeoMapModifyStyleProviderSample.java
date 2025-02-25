package io.jmix.uisamples.view.flowui.maps.modifystyleprovider;

import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.PointStyle;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.uisamples.entity.Marker;

import java.util.List;
import java.util.Map;

@ViewController(id = "geo-map-modify-style-provider")
@ViewDescriptor(path = "geo-map-modify-style-provider.xml")
public class GeoMapModifyStyleProviderSample extends StandardView {

    private final Map<String, String> cityColors = Map.of(
            "Moscow", "#1531AE",
            "Helsinki", "#589800",
            "Berlin", "#8C003D",
            "Paris", "#A67500",
            "London", "#034769"
    );

    @Install(to = "map.vectorLayer.source", subject = "modifyStyleProvider")
    private List<Style> mapVectorLayerSourceModifyStyleProvider(final Marker marker) {
        return List.of(new PointStyle()
                .withImage(new CircleStyle()
                        .withRadius(10)
                        .withFill(new Fill(cityColors.get(marker.getName())))
                        .withStroke(new Stroke()
                                .withWidth(2d)
                                .withColor("#ffffff")))
                .build());
    }
}