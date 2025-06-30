package io.jmix.uisamples.doc;

import com.google.common.base.Strings;
import io.jmix.flowui.view.ViewInfo;
import io.jmix.flowui.view.ViewRegistry;
import io.jmix.uisamples.config.UiSamplesMenuConfig;
import io.jmix.uisamples.config.UiSamplesMenuItem;
import io.jmix.uisamples.util.UiSamplesHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/doc")
public class DocController {

    private final UiSamplesMenuConfig menuConfig;
    private final ViewRegistry viewRegistry;
    private final UiSamplesHelper uiSamplesHelper;

    public DocController(UiSamplesMenuConfig menuConfig, ViewRegistry viewRegistry, UiSamplesHelper uiSamplesHelper) {
        this.menuConfig = menuConfig;
        this.viewRegistry = viewRegistry;
        this.uiSamplesHelper = uiSamplesHelper;
    }

    @GetMapping
    public List<String> getContent() {
        List<String> list = menuConfig.getItemsAsList().stream()
                .filter(menuItem -> !menuItem.isMenu() && !menuItem.isOverview())
                .map(UiSamplesMenuItem::getId)
                .toList();
        return list;
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") String articleId) {
        ViewInfo viewInfo = viewRegistry.getViewInfo(articleId);

        StringBuilder content = new StringBuilder();

        content.append("# ").append(menuConfig.getMenuItemTitle(articleId)).append("\n");

        Package descriptionPackage = viewInfo.getControllerClass().getPackage();
        if (descriptionPackage != null) {
            content.append("## Description\n");
            content.append(getDescription(articleId, descriptionPackage.getName()));
        }

        UiSamplesMenuItem menuItem = menuConfig.getItemById(articleId);
        if (menuItem.isDefaultFiles()) {
            viewInfo.getTemplatePath()
                    .ifPresent(templatePath -> {
                        content.append("\n## ").append(uiSamplesHelper.getFileName(templatePath));
                        content.append("\n```\n");
                        content.append(uiSamplesHelper.getFileContent(templatePath));
                        content.append("\n```\n");
                    });
            String controllerFilePath = getControllerFilePath(viewInfo.getControllerClassName());
            content.append("\n## ").append(uiSamplesHelper.getFileName(controllerFilePath));
            content.append("\n```\n");
            content.append(uiSamplesHelper.getFileContent(controllerFilePath));
            content.append("\n```\n");
        }

        List<String> otherFiles = menuItem.getOtherFiles();
        for (String filePath : otherFiles) {
            content.append("\n## ").append(uiSamplesHelper.getFileName(filePath));
            content.append("\n```\n");
            content.append(uiSamplesHelper.getFileContent(filePath));
            content.append("\n```\n");
        }

        return content.toString();
    }

    private String getDescription(String articleId, String descriptionsPack) {
        String descriptionFileName = getDescriptionFilePath(articleId, descriptionsPack);

        String text = uiSamplesHelper.getFileContent(descriptionFileName + ".html");
        if (!Strings.isNullOrEmpty(text)) {
            return text;
        } else {
            text = uiSamplesHelper.getFileContent(descriptionFileName + ".md");
            if (!Strings.isNullOrEmpty(text)) {
                return text;
            }
        }
        return "No description";
    }

    private String getDescriptionFilePath(String sampleId, String descriptionsPack) {
        descriptionsPack = descriptionsPack.replaceAll("\\.", "/");
        StringBuilder sb = new StringBuilder(descriptionsPack);

        if (!descriptionsPack.endsWith("/")) {
            sb.append("/");
        }

        sb.append(sampleId).append("-");
        sb.append(getCurrentLocale().toLanguageTag());

        return sb.toString();
    }

    private String getControllerFilePath(String controllerName) {
        return controllerName.replaceAll("\\.", "/") + ".java";
    }

    private Locale getCurrentLocale() {
        return Locale.ENGLISH;
    }


}
