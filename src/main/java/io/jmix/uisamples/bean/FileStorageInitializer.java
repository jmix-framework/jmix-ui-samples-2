package io.jmix.uisamples.bean;

import io.jmix.core.FileRef;
import io.jmix.core.security.Authenticated;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component("uisamples_FileStorageInitializer")
public class FileStorageInitializer {

    private final SamplesFileStorage fileStorage;

    public FileStorageInitializer(SamplesFileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @EventListener
    @Authenticated
    public void onApplicationStarted(ApplicationStartedEvent event) {
        uploadFile("META-INF/resources/pdf/", "documentation.pdf");
        uploadFile("META-INF/resources/avatar/", "dora.png");
        uploadFile("META-INF/resources/avatar/", "edward.png");
        uploadFile("META-INF/resources/avatar/", "john.png");
        uploadFile("META-INF/resources/avatar/", "mary.png");
    }

    private void uploadFile(String path, String fileName) {
        FileRef fileRef = FileRef.create(SamplesFileStorage.DEFAULT_STORAGE_NAME, fileName, fileName);
        if (!fileStorage.fileExists(fileRef)) {
            uploadFile(fileRef, path, fileName);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void uploadFile(FileRef fileRef, String path, String fileName) {
        ClassPathResource resource = new ClassPathResource(path + fileName);
        try (InputStream stream = resource.getInputStream()) {
            fileStorage.saveStream(fileRef, stream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read resource: " + path + fileName, e);
        }
    }
}
