package io.jmix.uisamples.view.flowui.cookbook.iframepdfrest;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.page.Page;
import io.jmix.core.FileRef;
import io.jmix.core.common.util.URLEncodeUtils;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.bean.SamplesFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@ViewController("iframe-pdf-rest")
@ViewDescriptor("iframe-pdf-rest.xml")
public class IframePdfRestSample extends StandardView {

    private static final String REST_PATH = "/custom-rest/files?fileRef=";
    private static final FileRef FILE_REF = FileRef.create(
            SamplesFileStorage.DEFAULT_STORAGE_NAME,
            "documentation.pdf",
            "documentation.pdf"
    );

    @Autowired
    private ApplicationContext applicationContext;
    @ViewComponent
    private IFrame fileRefIframe;

    @Subscribe
    public void onInit(InitEvent event) {
        Page page = UI.getCurrent().getPage();

        page.fetchCurrentURL(url -> {
            String contextPath = ((WebApplicationContext) applicationContext).getServletContext().getContextPath();

            String documentSrc = url.getProtocol() + "://" + url.getAuthority() + contextPath +
                    REST_PATH + URLEncodeUtils.encodeUtf8(FILE_REF.toString());

             fileRefIframe.setSrc(documentSrc);
        });
    }
}
