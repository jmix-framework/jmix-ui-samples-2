package io.jmix.uisamples.view.entity.kanbanuser;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.server.streams.DownloadHandler;
import com.vaadin.flow.server.streams.DownloadResponse;
import com.vaadin.flow.server.streams.InputStreamDownloadHandler;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorageLocator;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.KanbanUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

@ViewController("KanbanUser.list")
@ViewDescriptor("kanban-user-list-view.xml")
@LookupComponent("kanbanUsersDataGrid")
@DialogMode(height = "25em", width = "17em")
public class KanbanUserListView extends StandardListView<KanbanUser> {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private FileStorageLocator fileStorageLocator;

    @Supply(to = "kanbanUsersDataGrid.avatar", subject = "renderer")
    public Renderer<KanbanUser> kanbanUsersDataGridAvararRenderer() {
        return new ComponentRenderer<>(this::createImage);
    }

    private Component createImage(KanbanUser user) {
        FileRef fileRef = user.getAvatar();
        if (fileRef == null) {
            return new Span();
        }

        Image image = uiComponents.create(Image.class);
        image.setWidth("30px");
        image.setHeight("30px");

        InputStreamDownloadHandler handler = DownloadHandler.fromInputStream(event -> {
            InputStream inputStream = fileStorageLocator.getByName(fileRef.getStorageName()).openStream(fileRef);

            return new DownloadResponse(inputStream, fileRef.getFileName(), fileRef.getContentType(), -1);
        });

        image.setSrc(handler);
        image.getStyle().set("object-fit", "contain");

        return image;
    }
}
