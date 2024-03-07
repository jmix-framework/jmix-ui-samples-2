package io.jmix.uisamples.view.sys.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import io.jmix.core.Resources;
import io.jmix.core.common.util.Dom4j;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.view.MessageBundle;
import io.jmix.uisamples.view.sys.sampleview.SampleView;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;

public class OverviewPageGenerator {

    private UiComponents uiComponents;
    private Resources resources;
    private MessageBundle messageBundle;
    private VerticalLayout overviewRoot;

    public OverviewPageGenerator(UiComponents uiComponents, Resources resources, MessageBundle messageBundle) {
        this.uiComponents = uiComponents;
        this.resources = resources;
        this.messageBundle = messageBundle;
    }

    public void generate(String var1) {
        overviewRoot = uiComponents.create(VerticalLayout.class);
        InputStream inputStream = resources.getResourceAsStream(var1);
        if (inputStream == null) {
            String errorMessage = String.format("Resource with path '%s' can't be loaded", var1);
            throw new GuiDevelopmentException(errorMessage, "sampleView");
        } else {
            Document document = Dom4j.readDocument(inputStream);
            Element rootElement = document.getRootElement();
            initHeader(rootElement.element("header"));
            initSamples(rootElement.element("samples"));
            overviewRoot.add(createLabel("", ""));
            initResources(rootElement.element("resources"));
        }
    }

    public Component getContent() {
        return overviewRoot;
    }

    private void initHeader(Element header) {
        if (header != null) {
            List<Element> textElements = header.elements("text");
            VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
            verticalLayout.setPadding(false);
            verticalLayout.setMaxWidth("41em");
            for (Element textElement : textElements) {
                verticalLayout.add(createLabel(textElement));
            }

            overviewRoot.add(verticalLayout);
        }
    }

    private void initSamples(Element samples) {
        if (samples != null) {
            FlexLayout flexLayout = uiComponents.create(FlexLayout.class);
            flexLayout.setAlignContent(FlexLayout.ContentAlignment.START);
            flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
            flexLayout.setJustifyContentMode(!isSmallDevice()
                    ? FlexComponent.JustifyContentMode.START
                    : FlexComponent.JustifyContentMode.CENTER);
            flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            flexLayout.addClassName("gap-l");

            overviewRoot.add(flexLayout);

            List<Element> samplesList = samples.elements();
            for (Element sample : samplesList) {
                VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
                verticalLayout.setPadding(false);
                verticalLayout.setWidth("20em");
                verticalLayout.add(createImage(sample.element("image")));

                List<Element> textElements = sample.elements("text");

                for (Element textElement : textElements) {
                    verticalLayout.add(createLabel(textElement));
                }

                List<Element> tagElements =  sample.elements("tag");
                if (!tagElements.isEmpty()) {
                    verticalLayout.add(createTags(tagElements));
                }

                flexLayout.add(verticalLayout);
            }
        }
    }

    private Component createTags(List<Element> tagElements) {
        FlexLayout flexLayout = uiComponents.create(FlexLayout.class);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.START);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flexLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.addClassName("gap-s");

        for (Element tagElement : tagElements) {
            Span span = uiComponents.create(Span.class);
            span.getElement().getThemeList().add("badge normal pill");
            addClassNames(span, tagElement.attributeValue("classNames"));

            span.setText(tagElement.attributeValue("name"));
            String route = tagElement.attributeValue("route");
            flexLayout.add(StringUtils.isNotEmpty(route) ? createRoute(span, route) : span);
        }

        return flexLayout;
    }

    private Component createImage(Element imageElement) {
        Image image = uiComponents.create(Image.class);
        image.setSrc(imageElement.attributeValue("src"));
        image.setWidth("20em");
        String route = imageElement.attributeValue("route");
        return StringUtils.isNotEmpty(route) ? createRoute(image, route) : image;
    }

    private Component createLabel(Element labelElement) {
        Component label = createLabel(messageBundle.getMessage(labelElement.attributeValue("message")),
                labelElement.attributeValue("classNames"));
        String route = labelElement.attributeValue("route");
        return StringUtils.isNotEmpty(route) ? createRoute(label, route) : label;
    }

    private Component createRoute(Component component, String route) {
        RouterLink routerLink = uiComponents.create(RouterLink.class);
        RouteParameters routeParams = new RouteParameters("sampleId", route);
        routerLink.setRoute(SampleView.class, routeParams);
        routerLink.addClassNames("jmix-main-view-header-link");
        routerLink.add(component);
        return routerLink;
    }

    private Component createLabel(String text, String classNames) {
        H3 h3 = uiComponents.create(H3.class);
        h3.setText(text);
        addClassNames(h3, classNames);

        return h3;
    }

    private void initResources(Element resources) {
        if (resources != null) {
            List<Element> textElements = resources.elements("text");
            VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
            verticalLayout.setPadding(false);

            for (Element textElement : textElements) {
                String href = textElement.attributeValue("href");
                verticalLayout.add(StringUtils.isNotEmpty(href)
                        ? createAnchor(textElement)
                        : createLabel(textElement));
            }

            overviewRoot.add(verticalLayout);
        }
    }

    private Component createAnchor(Element textElement) {
        Anchor anchor = uiComponents.create(Anchor.class);
        anchor.setText(messageBundle.getMessage(textElement.attributeValue("message")));
        anchor.setHref(textElement.attributeValue("href"));
        anchor.setTarget("_blank");
        addClassNames(anchor, textElement.attributeValue("classNames"));

        return anchor;
    }

    private void addClassNames(Component component, String classNames) {
        if (StringUtils.isNotEmpty(classNames)) {
            component.addClassNames(classNames.split(" "));
        }
    }

    private boolean isSmallDevice() {
        // magic number from vaadin-app-layout.js
        // '--vaadin-app-layout-touch-optimized' style property
        return UI.getCurrent().getInternals().getExtendedClientDetails().getScreenWidth() < 801;
    }
}
