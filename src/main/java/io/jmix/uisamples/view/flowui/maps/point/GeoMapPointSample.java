package io.jmix.uisamples.view.flowui.maps.point;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.GeoObjectClickNotifier;
import io.jmix.mapsflowui.component.model.source.SourceFeatureClickNotifier;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.uisamples.entity.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.jmix.maps.utils.GeometryUtils.createPoint;

@ViewController("geo-map-point")
@ViewDescriptor("geo-map-point.xml")
public class GeoMapPointSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @ViewComponent("map.dataLayer.dataSource")
    private DataVectorSource<Marker> dataSource;

    @ViewComponent("map.vectorLayer.vectorSource")
    private VectorSource vectorSource;

    @Subscribe
    public void onInit(final InitEvent event) {
        dataSource.addGeoObjectClickListener(this::onDataVectorSourceClick);
        vectorSource.addSourceFeatureClickListener(this::onVectorSourceClick);

        vectorSource.addAllFeatures(List.of(
                new PointFeature(createPoint(30.327877976068475, 59.94327979807139)),
                new PointFeature(createPoint(13.722223426393388, 51.05181869560283)),
                new PointFeature(createPoint(-2.2449710026111562, 53.47839876123808))
        ));
    }

    private void onVectorSourceClick(SourceFeatureClickNotifier.SourceFeatureClickEvent event) {
        notifications.create("GeoObjectClickEvent", ((PointFeature) event.getFeature()).getPoint().toText())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    private void onDataVectorSourceClick(GeoObjectClickNotifier.GeoObjectClickEvent<Marker> event) {
        notifications.create("GeoObjectClickEvent", event.getItem().getPoint().toText())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
