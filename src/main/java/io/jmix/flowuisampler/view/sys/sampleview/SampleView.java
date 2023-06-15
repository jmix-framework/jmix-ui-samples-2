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

package io.jmix.flowuisampler.view.sys.sampleview;

import com.google.common.base.Strings;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.CoreProperties;
import io.jmix.core.Messages;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.Views;
import io.jmix.flowui.component.codeeditor.CodeEditor;
import io.jmix.flowui.component.layout.ViewLayout;
import io.jmix.flowui.component.scroller.JmixScroller;
import io.jmix.flowui.component.splitlayout.JmixSplitLayout;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.kit.component.codeeditor.CodeEditorMode;
import io.jmix.flowui.view.*;
import io.jmix.flowuisampler.config.SamplerMenuConfig;
import io.jmix.flowuisampler.config.SamplerMenuItem;
import io.jmix.flowuisampler.util.SamplerHelper;
import io.jmix.flowuisampler.view.sys.main.MainView;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Route(value = "sample/:sampleId?", layout = MainView.class)
@ViewController("SampleView")
@ViewDescriptor("sample-view.xml")
@AnonymousAllowed
public class SampleView extends StandardView implements LocaleChangeObserver {

    protected static final String DOC_URL_MESSAGES_KEY = "docUrl";

    @Autowired
    protected SamplerMenuConfig menuConfig;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected ViewRegistry viewRegistry;
    @Autowired
    protected MessageBundle messageBundle;
    @Autowired
    protected SamplerHelper samplerHelper;
    @Autowired
    protected Messages messages;
    @Autowired
    protected CoreProperties coreProperties;
    @Autowired
    protected Views views;

    protected JmixTabSheet tabSheet;
    protected String sampleId;
    protected SamplerMenuItem menuItem;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getRouteParameters().get("sampleId")
                .ifPresent(this::updateSample);
    }

    protected void updateSample(String sampleId) {
        this.sampleId = sampleId;
        this.menuItem = menuConfig.getItemById(sampleId);

        StandardView sampleView = ((StandardView) views.create(sampleId));

        updateLayout(sampleView);
        updateTabs();
    }

    protected void updateLayout(StandardView sampleView) {
        getContent().removeAll();

        createTabSheet();

        ViewLayout sampleViewContent = sampleView.getContent();

        sampleViewContent.setHeight(null);

        if (menuItem.isSplitEnabled()) {
            JmixSplitLayout splitLayout = uiComponents.create(JmixSplitLayout.class);
            splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
            splitLayout.setWidthFull();
            splitLayout.setHeightFull();

            VerticalLayout contentHolder = uiComponents.create(VerticalLayout.class);
            contentHolder.add(sampleViewContent);

            splitLayout.addToPrimary(contentHolder);
            splitLayout.addToSecondary(tabSheet);

            getContent().add(splitLayout);
        } else {
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
        if (Strings.isNullOrEmpty(title)) {
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

        String messagesPack = samplerHelper.findMessagePack(viewInfo);
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
        String fileContent = samplerHelper.getFileContent(src);
        if (!Strings.isNullOrEmpty(src) && !Strings.isNullOrEmpty(fileContent)) {
            CodeEditor codeEditor = createSourceCodeEditor(getCodeEditorMode(src));
            codeEditor.setValue(fileContent);
            addTab(samplerHelper.getFileName(src), codeEditor, VaadinIcon.CODE.create());
        }
    }

    protected CodeEditor createSourceCodeEditor(CodeEditorMode mode) {
        CodeEditor editor = uiComponents.create(CodeEditor.class);

        editor.setShowPrintMargin(false);
        editor.setMode(mode);
        editor.setReadOnly(true);
        editor.setWidthFull();
        editor.setHeightFull();

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

        scrollerContainer.add(docLinkFooter);
        return scrollerContainer;
    }

    protected Component createDescriptionTextComponent(String descriptionsPack) {
        StringBuilder sb = new StringBuilder();
        String text = samplerHelper.getFileContent(getDescriptionFileName(descriptionsPack));

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
        docLink.setText(messages.getMessage(getClass(), "documentation"));
        docLink.setHref(docUrl.toString());
        docLink.setTarget("_blank");
        return docLink;
    }

    protected String getControllerFileName(String controllerName) {
        return controllerName.replaceAll("\\.", "/") + ".java";
    }

    protected void createMessagesContainers(String messagesPack) {
        for (Locale locale : coreProperties.getAvailableLocales()) {

            String tabTitle = String.format("messages_%s.properties", locale.toString());
            String src = samplerHelper.packageToPath(messagesPack) + "/" + tabTitle;
            String content = samplerHelper.getFileContent(src);

            if (StringUtils.isNotBlank(content)) {
                CodeEditor sourceCodeEditor = createSourceCodeEditor(getCodeEditorMode(src));
                sourceCodeEditor.setValue(content);
                addTab(tabTitle, sourceCodeEditor, VaadinIcon.GLOBE.create());
            }
        }
    }

    @Override
    public void localeChange(LocaleChangeEvent event) {
        // TODO: 22.12.2022 implement kremnevda
    }

    protected CodeEditorMode getCodeEditorMode(String src) {
        String fileExtension = samplerHelper.getFileExtension(src);

        CodeEditorMode mode = CodeEditorMode.TEXT;
        if (fileExtension != null) {
            switch (fileExtension) {
                case "xsd", "xml" -> mode = CodeEditorMode.XML;
                case "java" -> mode = CodeEditorMode.JAVA;
                case "js" -> mode = CodeEditorMode.JAVASCRIPT;
                case "properties" -> mode = CodeEditorMode.PROPERTIES;
                case "css" -> mode = CodeEditorMode.CSS;
                case "scss" -> mode = CodeEditorMode.SCSS;
            }
        }

        return mode;
    }

    protected Locale getCurrentLocale() {
        return UI.getCurrent().getLocale();
    }
}
