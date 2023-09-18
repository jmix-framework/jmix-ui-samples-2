package io.jmix.uisamples.view.flowui.components.progressbar.themevariant;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.progressbar.ProgressBar;
import io.jmix.flowui.backgroundtask.BackgroundTask;
import io.jmix.flowui.backgroundtask.BackgroundTaskHandler;
import io.jmix.flowui.backgroundtask.BackgroundWorker;
import io.jmix.flowui.backgroundtask.TaskLifeCycle;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ViewController("progress-bar-theme-variant")
@ViewDescriptor("progress-bar-theme-variant.xml")
public class ProgressBarThemeVariantSample extends StandardView {

    protected static final int ITERATIONS = 20;

    @ViewComponent
    protected ProgressBar standardProgressBar;
    @ViewComponent
    protected ProgressBar successProgressBar;
    @ViewComponent
    protected ProgressBar errorProgressBar;
    @ViewComponent
    protected ProgressBar contrastProgressBar;

    @Autowired
    protected BackgroundWorker backgroundWorker;

    protected BackgroundTaskHandler<Void> taskHandler;
    protected Collection<ProgressBar> progressBars;

    @Subscribe
    protected void onInit(InitEvent event) {
        progressBars = List.of(standardProgressBar, successProgressBar, errorProgressBar, contrastProgressBar);
    }

    @Subscribe("indeterminateCheckbox")
    protected void onIndeterminateCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        Boolean value = event.getValue();

        if (value && taskHandler != null && taskHandler.isAlive()) {
            taskHandler.cancel();
        } else if (!value){
            runNewTask();
        }

        progressBars.forEach(progressBar -> progressBar.setIndeterminate(value));
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
                progressBars.forEach(progressBar -> progressBar.setValue(lastValue / ITERATIONS));
            }
        };
    }
}
