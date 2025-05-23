package io.jmix.uisamples.view.flowui.cookbook.composition3;

import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Airport;

@ViewController(id = "airport-detail-3")
@ViewDescriptor(path = "airport-detail-view.xml")
@EditedEntityContainer("airportDc")
public class AirportDetailView extends StandardDetailView<Airport> {
}
