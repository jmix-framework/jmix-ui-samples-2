package io.jmix.uisamples.view.flowui.components.fileupload.fileuploadfield;

import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.upload.Receiver;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.FileUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ViewController("file-upload-field")
@ViewDescriptor("file-upload-field.xml")
public class FileUploadFieldSample extends StandardView {

    @Autowired
    private Notifications notifications;

    @Subscribe("fileUploadField")
    public void onFileUploadFieldFileUploadSucceeded(
            final FileUploadSucceededEvent<FileStorageUploadField> event) {
        String fileName = event.getFileName();
        notifications.create("Your file %s has been uploaded successfully.".formatted(event.getFileName()))
                .withThemeVariant(NotificationVariant.LUMO_PRIMARY)
                .show();
    }
}
