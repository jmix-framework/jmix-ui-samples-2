package io.jmix.uisamples.view.flowui.maps.polygon;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.source.SourceFeatureClickNotifier;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("geo-map-polygon")
@ViewDescriptor("geo-map-polygon.xml")
public class GeoMapPolygonSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @ViewComponent("map.vectorLayer.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(PolygonFeatures.getUSAStates());
        source.addSourceFeatureClickListener(this::onSourceFeatureClick);
    }

    private void onSourceFeatureClick(SourceFeatureClickNotifier.SourceFeatureClickEvent event) {
        String name = (String) event.getFeature().getProperties().get("name");
        notifications.show(name);
    }
}
