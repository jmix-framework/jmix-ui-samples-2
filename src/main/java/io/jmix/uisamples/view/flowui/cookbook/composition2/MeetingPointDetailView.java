package io.jmix.uisamples.view.flowui.cookbook.composition2;

import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.MeetingPoint;

@ViewController(id = "meeting-point-detail-2")
@ViewDescriptor(path = "meeting-point-detail-view.xml")
@EditedEntityContainer("meetingPointDc")
public class MeetingPointDetailView extends StandardDetailView<MeetingPoint> {
}
