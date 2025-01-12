package io.jmix.uisamples.view.flowui.cookbook.terminal;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Terminal;
import io.jmix.uisamples.view.sys.main.MainView;

@Route(value = "terminals/:id", layout = MainView.class)
@ViewController(id = "Terminal.detail")
@ViewDescriptor(path = "terminal-detail-view.xml")
@EditedEntityContainer("terminalDc")
public class TerminalDetailView extends StandardDetailView<Terminal> {
}