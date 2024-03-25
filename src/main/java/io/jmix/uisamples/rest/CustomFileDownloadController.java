package io.jmix.uisamples.rest;

import io.jmix.core.FileRef;
import io.jmix.core.FileTransferService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("uisamples_CustomFileDownloadController")
@RequestMapping("/custom-rest")
public class CustomFileDownloadController {

    protected FileTransferService fileTransferService;

    public CustomFileDownloadController(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }

    @GetMapping(path = "/files")
    public void getFileRef(@RequestParam String fileRef,
                           HttpServletResponse response) {
        try {
            FileRef fileReference = FileRef.fromString(fileRef);
            String storageName = fileReference.getStorageName();
            fileTransferService.downloadAndWriteResponse(fileReference, storageName, false, response);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid file reference", e);
        }
    }
}
