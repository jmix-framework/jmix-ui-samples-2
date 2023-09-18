package io.jmix.uisamples.view.flowui.components.simplepagination.afterrefresh;

import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.PaginationComponent;
import io.jmix.flowui.component.pagination.SimplePagination;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("simple-pagination-after-refresh")
@ViewDescriptor("simple-pagination-after-refresh.xml")
public class SimplePaginationAfterRefreshSample extends StandardView {

    @Autowired
    protected Notifications notifications;

    @Subscribe("simplePagination")
    protected void onSimplePaginationAfterRefresh(PaginationComponent.AfterRefreshEvent<SimplePagination> event) {
        notifications.show("After data refresh");
    }
}
