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
        FileRef fileRef = FileRef.create(SamplesFileStorage.DEFAULT_STORAGE_NAME, "documentation.pdf", "documentation.pdf");
        if (!fileStorage.fileExists(fileRef)) {
            uploadFile(fileRef, "META-INF/resources/pdf/", "documentation.pdf");
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
