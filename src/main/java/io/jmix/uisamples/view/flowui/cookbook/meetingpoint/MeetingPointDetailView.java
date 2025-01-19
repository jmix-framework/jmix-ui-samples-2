package io.jmix.uisamples.view.flowui.cookbook.meetingpoint;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.MeetingPoint;
import io.jmix.uisamples.view.sys.main.MainView;

@Route(value = "meetingPoints/:id", layout = MainView.class)
@ViewController(id = "MeetingPoint.detail")
@ViewDescriptor(path = "meeting-point-detail-view.xml")
@EditedEntityContainer("meetingPointDc")
public class MeetingPointDetailView extends StandardDetailView<MeetingPoint> {
}