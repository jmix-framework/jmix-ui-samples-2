/*
 * Copyright 2022 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.uisamples.view.sys.sampleview;

import com.google.common.base.Strings;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.CoreProperties;
import io.jmix.core.Messages;
import io.jmix.core.Resources;
import io.jmix.core.session.SessionData;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.Views;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.component.layout.ViewLayout;
import io.jmix.flowui.component.scroller.JmixScroller;
import io.jmix.flowui.component.splitlayout.JmixSplitLayout;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorMode;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorTheme;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.bean.MenuNavigationExpander;
import io.jmix.uisamples.config.UiSamplesMenuConfig;
import io.jmix.uisamples.config.UiSamplesMenuItem;
import io.jmix.uisamples.util.CodeEditorThemeHelper;
import io.jmix.uisamples.util.UiSamplesHelper;
import io.jmix.uisamples.view.sys.main.MainView;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.util.*;

@Route(value = "sample/:sampleId?", layout = MainView.class)
@ViewController("SampleView")
@ViewDescriptor("sample-view.xml")
@AnonymousAllowed
public class SampleView extends StandardView implements LocaleChangeObserver {

    protected static final String DOC_URL_MESSAGES_KEY = "docUrl";
    protected static final String CODE_EDITOR_HEIGHT = "-webkit-fill-available";
    protected static final String SRC_ROOT_PATH = "io/jmix/uisamples/view/flowui/";

    @Autowired
    protected UiSamplesMenuConfig menuConfig;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected ViewRegistry viewRegistry;
    @Autowired
    protected MessageBundle messageBundle;
    @Autowired
    protected UiSamplesHelper uiSamplesHelper;
    @Autowired
    protected Messages messages;
    @Autowired
    protected CoreProperties coreProperties;
    @Autowired
    protected Views views;
    @Autowired
    protected ObjectProvider<SessionData> sessionDataProvider;
    @Autowired
    protected Notifications notifications;
    @Autowired
    protected CodeEditorThemeHelper codeEditorThemeHelper;
    @Autowired
    protected MenuNavigationExpander menuNavigationExpander;
    @Autowired
    protected Resources resources;

    protected String sampleId;
    protected StandardView sampleView;

    protected JmixTabSheet tabSheet;
    protected UiSamplesMenuItem menuItem;
    protected Set<CodeEditor> codeEditors = new HashSet<>();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters().get("sampleId")
                .ifPresent(this::updateSample);
        codeEditorThemeHelper.setCodeEditors(codeEditors);
        super.beforeEnter(event);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (sampleId != null) {
            menuNavigationExpander.expand(sampleId);
        }

        super.afterNavigation(event);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (sampleView != null) {
            ComponentUtil.fireEvent(sampleView, new BeforeShowEvent(sampleView));
        }
    }

    protected void updateSample(String sampleId) {
        this.sampleId = sampleId;
        this.menuItem = menuConfig.getItemById(sampleId);

        if (menuItem.isAbout()) {
            initAboutView();
        } else {
            this.sampleView = (StandardView) views.create(sampleId);
            updateLayout(sampleView);
            updateTabs();
        }
    }

    protected void initAboutView() {
        getContent().removeAll();
        String resourcePath = SRC_ROOT_PATH + Strings.nullToEmpty(menuItem.getAboutLocation());
        InputStream resourceAsStream = resources.getResourceAsStream(resourcePath);

        if (resourceAsStream == null) {
            String message = String.format("Resource with path '%s' can't be loaded", resourcePath);
            throw new GuiDevelopmentException(message, getId().orElse("sampleView"));
        }

        Html html = new Html(resourceAsStream);
        getContent().add(html);
    }

    protected void updateLayout(StandardView sampleView) {
        getContent().removeAll();

        createTabSheet();

        ViewLayout sampleViewContent = sampleView.getContent();

        if (menuItem.isSplitEnabled()) {
            JmixSplitLayout splitLayout = uiComponents.create(JmixSplitLayout.class);
            splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
            splitLayout.setWidthFull();
            splitLayout.setHeightFull();

            VerticalLayout contentHolder = uiComponents.create(VerticalLayout.class);
            contentHolder.setPadding(false);
            sampleViewContent.setHeightFull();
            contentHolder.add(sampleViewContent);

            splitLayout.addToPrimary(contentHolder);
            splitLayout.addToSecondary(tabSheet);

            getContent().add(splitLayout);
        } else {
            sampleViewContent.setHeight(null);
            getContent().add(sampleViewContent);
            getContent().add(tabSheet);

            getContent().expand(tabSheet);
        }
    }


    protected void createTabSheet() {
        tabSheet = uiComponents.create(JmixTabSheet.class);

        tabSheet.setId("tabSheet");
        tabSheet.setHeightFull();
        tabSheet.setWidthFull();
    }

    @Override
    public String getPageTitle() {
        String title = menuConfig.getMenuItemTitle(sampleId);
        if (Strings.isNullOrEmpty(title) || sampleId == null) {
            title = super.getPageTitle();
        }

        return title;
    }

    protected void updateTabs() {
        clearTabSheet();

        ViewInfo viewInfo = viewRegistry.getViewInfo(menuItem.getId());

        Package descriptionPackage = viewInfo.getControllerClass().getPackage();
        if (descriptionPackage != null) {
            String message = messageBundle.getMessage("description");
            Component scrollerContent = getScrollerContent(descriptionPackage.getName(),
                    menuItem.getUrl(),
                    menuItem.getPage(),
                    menuItem.getAnchor());

            Component componentDescription = createComponentDescription(scrollerContent);

            addTab(message, componentDescription, VaadinIcon.INFO_CIRCLE_O.create());
        }

        viewInfo.getTemplatePath()
                .ifPresent(this::addSourceTab);

        addSourceTab(getControllerFileName(viewInfo.getControllerClassName()));

        List<String> otherFiles = menuItem.getOtherFiles();
        if (CollectionUtils.isNotEmpty(otherFiles)) {
            otherFiles.forEach(this::addSourceTab);
        }

        String messagesPack = uiSamplesHelper.findMessagePack(viewInfo);
        if (StringUtils.isNotEmpty(messagesPack)) {
            createMessagesContainers(messagesPack);
        }
    }

    protected void clearTabSheet() {
        Collection<Component> tabs = tabSheet.getOwnComponents();

        for (Component tab : tabs) {
            tabSheet.remove(tab);
        }
    }

    protected void addTab(String title, Component component, Icon icon) {
        Tab addedTab = tabSheet.add(title, component);
        addedTab.addComponentAsFirst(icon);
    }

    protected void addSourceTab(String src) {
        String fileContent = uiSamplesHelper.getFileContent(src);
        if (!Strings.isNullOrEmpty(src) && !Strings.isNullOrEmpty(fileContent)) {
            CodeEditor codeEditor = createCodeEditor(getCodeEditorMode(src));
            codeEditor.setValue(fileContent);
            addTab(uiSamplesHelper.getFileName(src), codeEditor, getCodeEditorIcon(src));
        }
    }

    protected CodeEditor createCodeEditor(CodeEditorMode mode) {
        CodeEditor editor = uiComponents.create(CodeEditor.class);

        editor.setTheme(getSessionTheme());
        editor.setShowPrintMargin(false);
        editor.setMode(mode);
        editor.setReadOnly(true);
        editor.setWidthFull();

        if (UI.getCurrent().getSession().getBrowser().isFirefox()) {
            editor.setMinHeight("20em");
            editor.setHeight("95%");
        } else {
            editor.setHeight(CODE_EDITOR_HEIGHT);
        }

        codeEditors.add(editor);
        return editor;
    }

    protected Component createComponentDescription(Component content) {
        JmixScroller scroller = uiComponents.create(JmixScroller.class);
        scroller.setWidthFull();
        scroller.setHeightFull();

        scroller.setContent(content);

        return scroller;
    }

    protected Component getScrollerContent(String descriptionsPack,
                                           @Nullable String url,
                                           @Nullable String page,
                                           @Nullable String anchor) {
        VerticalLayout scrollerContainer = uiComponents.create(VerticalLayout.class);
        scrollerContainer.setPadding(false);

        scrollerContainer.add(createDescriptionTextComponent(descriptionsPack));

        HorizontalLayout docLinkFooter = uiComponents.create(HorizontalLayout.class);
        docLinkFooter.setWidthFull();

        if (!Strings.isNullOrEmpty(url)
                && !Strings.isNullOrEmpty(page)) {
            Component docLink = documentLink(url, page, anchor);
            docLinkFooter.add(docLink);
        }

        docLinkFooter.add(permalink());

        scrollerContainer.add(docLinkFooter);
        return scrollerContainer;
    }

    protected Component createDescriptionTextComponent(String descriptionsPack) {
        StringBuilder sb = new StringBuilder();
        String text = uiSamplesHelper.getFileContent(getDescriptionFileName(descriptionsPack));

        if (!Strings.isNullOrEmpty(text)) {
            sb.append(text);
            sb.append("<hr>");
        }

        Label doc = uiComponents.create(Label.class);

        doc.setWidthFull();
        doc.getElement().setProperty("innerHTML", sb.toString());

        return doc;
    }

    protected String getDescriptionFileName(String descriptionsPack) {
        descriptionsPack = descriptionsPack.replaceAll("\\.", "/");
        StringBuilder sb = new StringBuilder(descriptionsPack);

        if (!descriptionsPack.endsWith("/")) {
            sb.append("/");
        }

        sb.append(sampleId).append("-");
        sb.append(getCurrentLocale().toLanguageTag());
        sb.append(".html");

        return sb.toString();
    }

    protected Component documentLink(String url, String page, @Nullable String anchor) {
        String baseUrl = messages.getMessage(DOC_URL_MESSAGES_KEY);

        StringBuilder docUrl = new StringBuilder(baseUrl);
        docUrl.append(url).append("/").append(page).append(".html");
        if (!Strings.isNullOrEmpty(anchor)) {
            docUrl.append("#").append(anchor);
        }

        Anchor docLink = uiComponents.create(Anchor.class);
        docLink.setText(messageBundle.getMessage("documentation"));
        docLink.setHref(docUrl.toString());
        docLink.setTarget("_blank");
        return docLink;
    }

    protected Component permalink() {
        JmixButton permalinkButton = uiComponents.create(JmixButton.class);

        permalinkButton.setIcon(VaadinIcon.COPY.create());
        permalinkButton.setTitle(messageBundle.getMessage("permalink.title"));
        permalinkButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);
        permalinkButton.addClassNames("me-s", "ms-auto");

        permalinkButton.addClickListener(this::copyToClipboard);

        return permalinkButton;
    }

    protected void copyToClipboard(ClickEvent<Button> event) {
        Page page = UI.getCurrent().getPage();

        page.fetchCurrentURL(url ->
                page.executeJs(getCopyToClipboardScript(), url.toString())
                        .then(jsonValue -> notifications.create(messageBundle.getMessage("successCopyNotification"))
                                        .withPosition(Notification.Position.BOTTOM_END)
                                        .withThemeVariant(NotificationVariant.LUMO_SUCCESS)
                                        .show(),
                                s -> notifications.create(messageBundle.getMessage("errorCopyNotification"))
                                        .withPosition(Notification.Position.BOTTOM_END)
                                        .withThemeVariant(NotificationVariant.LUMO_ERROR)
                                        .show())
        );
    }

    protected String getCopyToClipboardScript() {
        return "const textarea = document.createElement(\"textarea\");\n" +
                "  textarea.value = $0;\n" +
                "  textarea.style.position = \"absolute\";\n" +
                "  textarea.style.opacity = \"0\";\n" +
                "  document.body.appendChild(textarea);\n" +
                "  textarea.select();\n" +
                "  document.execCommand(\"copy\");\n" +
                "  document.body.removeChild(textarea);\n";
    }

    protected String getControllerFileName(String controllerName) {
        return controllerName.replaceAll("\\.", "/") + ".java";
    }

    protected void createMessagesContainers(String messagesPack) {
        for (Locale locale : coreProperties.getAvailableLocales()) {

            String tabTitle = String.format("messages_%s.properties", locale.toString());
            String src = uiSamplesHelper.packageToPath(messagesPack) + "/" + tabTitle;
            String content = uiSamplesHelper.getFileContent(src);

            if (StringUtils.isNotBlank(content)) {
                CodeEditor codeEditor = createCodeEditor(getCodeEditorMode(src));
                codeEditor.setValue(content);
                addTab(tabTitle, codeEditor, VaadinIcon.GLOBE.create());
            }
        }
    }

    @Override
    public void localeChange(LocaleChangeEvent event) {
        // TODO: 22.12.2022 implement kremnevda
    }

    protected CodeEditorMode getCodeEditorMode(String src) {
        String fileExtension = uiSamplesHelper.getFileExtension(src);

        CodeEditorMode mode = CodeEditorMode.TEXT;
        if (fileExtension != null) {
            switch (fileExtension) {
                case "xsd", "xml" -> mode = CodeEditorMode.XML;
                case "html" -> mode = CodeEditorMode.HTML;
                case "java" -> mode = CodeEditorMode.JAVA;
                case "kt" -> mode = CodeEditorMode.KOTLIN;
                case "js" -> mode = CodeEditorMode.JAVASCRIPT;
                case "properties" -> mode = CodeEditorMode.PROPERTIES;
                case "css" -> mode = CodeEditorMode.CSS;
                case "scss" -> mode = CodeEditorMode.SCSS;
            }
        }

        return mode;
    }

    protected Icon getCodeEditorIcon(String src) {
        String fileExtension = uiSamplesHelper.getFileExtension(src);

        VaadinIcon icon = VaadinIcon.CODE;
        if (fileExtension != null) {
            switch (fileExtension) {
                case "java" -> icon = VaadinIcon.COFFEE;
                case "js" -> icon = VaadinIcon.CURLY_BRACKETS;
                case "properties" -> icon = VaadinIcon.LIST_UL;
                case "css", "scss" -> icon = VaadinIcon.CSS;
            }
        }

        return icon.create();
    }

    protected Locale getCurrentLocale() {
        return UI.getCurrent().getLocale();
    }

    protected CodeEditorTheme getSessionTheme() {
        SessionData sessionData = sessionDataProvider.getIfAvailable();

        if (sessionData != null) {
            String currentTheme = (String) sessionData.getAttribute(MainView.CURRENT_THEME_SESSION_ATTRIBUTE);
            return "dark".equalsIgnoreCase(currentTheme)
                    ? CodeEditorTheme.NORD_DARK
                    : CodeEditorTheme.TEXTMATE;
        }

        return CodeEditorTheme.TEXTMATE;
    }
}
