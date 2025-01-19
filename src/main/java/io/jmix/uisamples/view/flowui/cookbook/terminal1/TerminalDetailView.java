package io.jmix.uisamples.view.flowui.cookbook.terminal1;

import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Terminal;

@ViewController(id = "terminal-detail-1")
@ViewDescriptor(path = "terminal-detail-view.xml")
@EditedEntityContainer("terminalDc")
public class TerminalDetailView extends StandardDetailView<Terminal> {
}