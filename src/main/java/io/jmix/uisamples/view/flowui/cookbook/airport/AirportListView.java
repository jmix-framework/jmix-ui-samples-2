package io.jmix.uisamples.view.flowui.cookbook.airport;

import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Airport;


@ViewController(id = "composition-1-level")
@ViewDescriptor(path = "airport-list-view.xml")
@DialogMode(width = "64em")
public class AirportListView extends StandardListView<Airport> {
}