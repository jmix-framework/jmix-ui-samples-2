package io.jmix.uisamples.view.entity.kanbanuser;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorageLocator;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.KanbanUser;
import org.springframework.beans.factory.annotation.Autowired;

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

        StreamResource streamResource =
                (StreamResource) UiComponentUtils.createResource(fileRef, fileStorageLocator);
        image.setSrc(streamResource);
        image.getStyle().set("object-fit", "contain");

        return image;
    }
}
