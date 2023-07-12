package io.jmix.flowuisampler.view.flowui.components.progressbar.simple;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.progressbar.ProgressBar;
import io.jmix.flowui.backgroundtask.BackgroundTask;
import io.jmix.flowui.backgroundtask.BackgroundTaskHandler;
import io.jmix.flowui.backgroundtask.BackgroundWorker;
import io.jmix.flowui.backgroundtask.TaskLifeCycle;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

@ViewController("progress-bar-simple")
@ViewDescriptor("progress-bar-simple.xml")
public class ProgressBarSimpleSample extends StandardView {

    protected static final int ITERATIONS = 20;

    @ViewComponent
    protected ProgressBar progressBar;

    @Autowired
    protected BackgroundWorker backgroundWorker;

    protected BackgroundTaskHandler<Void> taskHandler;

    @Subscribe
    protected void onInit(InitEvent event) {
        runNewTask();
    }

    protected void runNewTask() {
        taskHandler = backgroundWorker.handle(createBackgroundTask());
        taskHandler.execute();
    }

    protected BackgroundTask<Integer, Void> createBackgroundTask() {
        return new BackgroundTask<>(100, TimeUnit.SECONDS) {

            @Override
            public Void run(TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
                for (int i = 1; i <= ITERATIONS; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    taskLifeCycle.publish(i);
                }

                return null;
            }

            @Override
            public void progress(List<Integer> changes) {
                double lastValue = changes.get(changes.size() - 1);
                progressBar.setValue(lastValue / ITERATIONS);
            }
        };
    }

    @Subscribe("restartButton")
    protected void onRestartButtonClick(ClickEvent<JmixButton> event) {
        if (taskHandler != null && !taskHandler.isAlive() && taskHandler.isDone()) {
            runNewTask();
        }
    }
}
