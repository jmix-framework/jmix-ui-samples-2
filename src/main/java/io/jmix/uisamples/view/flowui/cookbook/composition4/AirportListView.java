package io.jmix.uisamples.view.flowui.cookbook.composition4;

import io.jmix.flowui.view.DialogMode;
import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Airport;


@ViewController(id = "composition-master-detail-2")
@ViewDescriptor(path = "airport-list-view.xml")
@DialogMode(width = "64em")
public class AirportListView extends StandardListView<Airport> {
}