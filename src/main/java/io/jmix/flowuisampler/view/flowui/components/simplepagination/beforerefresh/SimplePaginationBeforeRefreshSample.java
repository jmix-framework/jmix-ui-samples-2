package io.jmix.flowuisampler.view.flowui.components.simplepagination.beforerefresh;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.PaginationComponent;
import io.jmix.flowui.component.pagination.SimplePagination;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("simple-pagination-before-refresh")
@ViewDescriptor("simple-pagination-before-refresh.xml")
public class SimplePaginationBeforeRefreshSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("simplePagination")
    protected void onSimplePaginationBeforeRefresh(PaginationComponent.BeforeRefreshEvent<SimplePagination> event) {
        notifications.show("Before data refresh");
    }
}
