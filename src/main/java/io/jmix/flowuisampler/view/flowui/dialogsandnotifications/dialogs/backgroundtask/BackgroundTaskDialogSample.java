package io.jmix.flowuisampler.view.flowui.dialogsandnotifications.dialogs.backgroundtask;

import com.vaadin.flow.component.ClickEvent;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.backgroundtask.BackgroundTask;
import io.jmix.flowui.backgroundtask.TaskLifeCycle;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

@ViewController("background-task-dialog")
@ViewDescriptor("background-task-dialog.xml")
public class BackgroundTaskDialogSample extends StandardView {

    @Autowired
    protected Dialogs dialogs;

    @Subscribe("standardBackgroundTaskDialogButton")
    protected void onStandardBackgroundTaskDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createBackgroundTaskDialog(getDefaultBackgroundTask())
                .withHeader("Background task dialog")
                .open();
    }

    @Subscribe("customBackgroundTaskDialogButton")
    protected void onCustomBackgroundTaskDialogButtonClick(ClickEvent<JmixButton> event) {
        dialogs.createBackgroundTaskDialog(getDefaultBackgroundTask())
                .withHeader("Background task dialog")
                .withText("Background task are here!")
                .withCancelAllowed(true)
                .withShowProgressInPercentage(true)
                .withTotal(6)
                .open();
    }

    protected BackgroundTask<Integer, Void> getDefaultBackgroundTask() {
        return new BackgroundTask<>(100) {
            @Override
            public Void run(TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
                for (int i = 1; i <= 6; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    taskLifeCycle.publish(i);
                }
                return null;
            }
        };
    }
}
