package io.jmix.uisamples.view.flowui.cookbook.composition4;

import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Terminal;

@ViewController(id = "terminal-detail-4")
@ViewDescriptor(path = "terminal-detail-view.xml")
@EditedEntityContainer("terminalDc")
public class TerminalDetailView extends StandardDetailView<Terminal> {
}