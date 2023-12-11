package io.jmix.uisamples.view.flowui.components.managingfiles.filestorageuploadfield;

import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.upload.Receiver;
import io.jmix.core.FileRef;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

@ViewController("file-storage-upload-field")
@ViewDescriptor("file-storage-upload-field.xml")
public class FileStorageUploadFieldSample extends StandardView {

    @ViewComponent
    private FileStorageUploadField fileStorageUploadField;

    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private Notifications notifications;

    @Subscribe("fileStorageUploadField")
    public void onFileStorageUploadFieldFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {
        Receiver receiver = event.getReceiver();
        if (receiver instanceof FileTemporaryStorageBuffer storageBuffer) {
            UUID fileId = storageBuffer.getFileData().getFileInfo().getId();
            File file = temporaryStorage.getFile(fileId);

            if (file != null) {
                FileRef fileRef = new FileRef("tempStorage", file.getAbsolutePath(), file.getName());
                fileStorageUploadField.setValue(fileRef);
                notifications.create("Your file %s has been uploaded successfully.".formatted(event.getFileName()))
                        .withThemeVariant(NotificationVariant.LUMO_PRIMARY)
                        .show();
                temporaryStorage.deleteFile(fileId);
            }
        }
    }
}
