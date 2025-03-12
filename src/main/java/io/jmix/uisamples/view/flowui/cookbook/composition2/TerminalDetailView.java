package io.jmix.uisamples.view.flowui.cookbook.composition2;

import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.MeetingPoint;
import io.jmix.uisamples.entity.Terminal;

@ViewController(id = "terminal-detail-2")
@ViewDescriptor(path = "terminal-detail-view.xml")
@EditedEntityContainer("terminalDc")
public class TerminalDetailView extends StandardDetailView<Terminal> {

    @ViewComponent
    private DataContext dataContext;

    @Subscribe(id = "meetingPointsDc", target = Target.DATA_CONTAINER)
    public void onMeetingPointsDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<MeetingPoint> event) {
        // Mark the root edited `Terminal` entity as modified in `DataContext`
        // when a meeting point's property changes
        Terminal terminal = getEditedEntity();
        dataContext.setModified(terminal, true);
    }
}
