package io.jmix.uisamples.view.flowui.cookbook.composition4;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Airport;

@ViewController(id = "airport-detail-4")
@ViewDescriptor(path = "airport-detail-view.xml")
@EditedEntityContainer("airportDc")
@DialogMode(width = "64em", resizable = true)
public class AirportDetailView extends StandardDetailView<Airport> {
}
