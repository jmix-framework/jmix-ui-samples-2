package io.jmix.uisamples.view.flowui.maps.drag;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.source.HasGeoObjectDrag.GeoObjectDragEndEvent;
import io.jmix.mapsflowui.component.model.source.HasGeoObjectDrag.GeoObjectDragStartEvent;
import io.jmix.uisamples.entity.Marker;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController(id = "geo-map-drag-mode")
@ViewDescriptor(path = "geo-map-drag-mode.xml")
public class GeoMapDragModeSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @Subscribe("map.vectorLayer.source")
    public void onSourceGeoObjectDragStart(final GeoObjectDragStartEvent<Marker> event) {
        Marker marker = event.getItems().iterator().next();
        notifications.create("DragStartEvent", marker.getName())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("map.vectorLayer.source")
    public void onSourceGeoObjectDragEnd(final GeoObjectDragEndEvent<Marker> event) {
        Marker marker = event.getItems().iterator().next();
        notifications.create("DragEndEvent", marker.getName())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}