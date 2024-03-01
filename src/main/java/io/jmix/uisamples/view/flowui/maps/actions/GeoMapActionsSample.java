package io.jmix.uisamples.view.flowui.maps.actions;

import com.vaadin.flow.component.notification.Notification;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.event.MapClickEvent;
import io.jmix.mapsflowui.component.event.MapMoveEndEvent;
import io.jmix.mapsflowui.component.event.MapZoomChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("geo-map-actions")
@ViewDescriptor("geo-map-actions.xml")
public class GeoMapActionsSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @ViewComponent
    private GeoMap map;

    @Subscribe
    public void onInit(final InitEvent event) {
        map.addClickListener(this::onMapClick);
        map.addZoomChangedListener(this::onMapZoom);
        map.addMoveEndListener(this::onMapMoveEnd);
    }

    private void onMapClick(MapClickEvent event) {
        notifications.show(String.format("Map left click: %.2f, %2f",
                event.getCoordinate().x, event.getCoordinate().y));
    }

    private void onMapZoom(MapZoomChangedEvent event) {
        notifications.show("Map has been zoomed: " + event.getZoom());
    }

    private void onMapMoveEnd(MapMoveEndEvent event) {
        notifications.create("Map has been moved",
                        String.format("""
                                        Map has been moved:
                                        Center: %.2f, %.2f
                                        Zoom: %.2f
                                        Rotation: %.2f
                                        North-east point: %.8f, %.8f
                                        South-west point: %.8f, %.8f
                                        """,
                                event.getCenter().getX(),
                                event.getCenter().getY(),
                                event.getZoom(),
                                event.getRotation(),
                                event.getExtent().getMaxX(),
                                event.getExtent().getMaxY(),
                                event.getExtent().getMinX(),
                                event.getExtent().getMinY()))
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }
}
