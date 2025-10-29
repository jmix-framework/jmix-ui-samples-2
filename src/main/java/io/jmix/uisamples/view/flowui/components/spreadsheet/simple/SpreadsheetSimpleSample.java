package io.jmix.uisamples.view.flowui.components.spreadsheet.simple;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.spreadsheet.Spreadsheet;
import io.jmix.flowui.download.DownloadFormat;
import io.jmix.flowui.download.Downloader;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ViewController("spreadsheet-simple")
@ViewDescriptor("spreadsheet-simple.xml")
public class SpreadsheetSimpleSample extends StandardView {

    @Autowired
    private Downloader downloader;

    @ViewComponent
    private Spreadsheet spreadsheet;

    @Subscribe
    public void onInit(InitEvent event) {
        spreadsheet.createCell(0, 0, "Hello, World!");
        spreadsheet.setColumnWidth(0, 100);
    }

    @Subscribe("downloadBtn")
    public void onDownloadBtnClick(ClickEvent<JmixButton> event) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            spreadsheet.write(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error while downloading XLSX", e);
        }

        downloader.download(byteArrayOutputStream.toByteArray(), "spreadsheet", DownloadFormat.XLSX);
    }
}
