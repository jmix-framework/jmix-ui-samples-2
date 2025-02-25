package io.jmix.uisamples.bean;

import io.jmix.core.Metadata;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.uisamples.entity.Marker;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("uisamples_MountainService")
public class MountainService {

    private final Metadata metadata;

    public MountainService(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Marker> fetchMountains() {
        return List.of(
                createMarker("Everest", 86.92528, 27.98806),
                createMarker("K2", 76.51333, 35.88139),
                createMarker("Kangchenjunga", 88.14750, 27.70333),
                createMarker("Lhotse", 86.93306, 27.96167),
                createMarker("Makalu", 87.08889, 27.88972),
                createMarker("Cho Oyu", 86.66083, 28.09417),
                createMarker("Dhaulagiri I", 83.49306, 28.69667),
                createMarker("Manaslu", 84.55972, 28.55),
                createMarker("Nanga Parbat", 74.58917, 35.23722),
                createMarker("Annapurna I", 83.82028, 28.59556)
        );
    }

    private Marker createMarker(String name, double x, double y) {
        Marker marker = metadata.create(Marker.class);
        marker.setName(name);
        marker.setPoint(GeometryUtils.createPoint(x, y));
        return marker;
    }
}
