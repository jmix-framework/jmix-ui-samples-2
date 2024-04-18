package io.jmix.uisamples.bean;

import com.google.common.base.Strings;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.jmix.core.Messages;
import io.jmix.core.Resources;
import io.jmix.core.common.xmlparsing.Dom4jTools;
import io.jmix.flowui.UiComponents;
import io.jmix.uisamples.view.sys.sampleview.SampleView;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.util.List;

@org.springframework.stereotype.Component
public class OverviewPageGenerator {

    private final Dom4jTools dom4jTools;
    private final UiComponents uiComponents;
    private final Resources resources;
    private final Messages messages;

    public OverviewPageGenerator(Dom4jTools dom4jTools, UiComponents uiComponents, Resources resources,
                                 Messages messages) {
        this.dom4jTools = dom4jTools;
        this.uiComponents = uiComponents;
        this.resources = resources;
        this.messages = messages;
    }

    public VerticalLayout generate(String messagesPrefix, String resourceName) {
        String prefix = messagesPrefix + "-overview";

        VerticalLayout overviewRoot = uiComponents.create(VerticalLayout.class);
        InputStream inputStream = resources.getResourceAsStream(resourceName);

        if (inputStream == null) {
            String errorMessage = String.format("Resource with path '%s' can't be loaded", resourceName);
            throw new IllegalArgumentException(errorMessage);
        } else {
            Document document = dom4jTools.readDocument(inputStream);
            Element rootElement = document.getRootElement();

            initHeader(rootElement.element("header"), overviewRoot, prefix);
            initSamples(rootElement.element("samples"), overviewRoot, prefix);
            initResources(rootElement.element("resources"), overviewRoot, prefix);
        }

        return overviewRoot;
    }

    private void initHeader(@Nullable Element header, VerticalLayout overviewRoot, String messagesPrefix) {
        if (header != null) {
            List<Element> textElements = header.elements("text");
            VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);

            verticalLayout.setPadding(false);
            verticalLayout.setMaxWidth("41em");

            for (Element textElement : textElements) {
                verticalLayout.add(createLabel(textElement, messagesPrefix));
            }

            overviewRoot.add(verticalLayout);
        }
    }

    private void initSamples(@Nullable Element samples, VerticalLayout overviewRoot, String messagesPrefix) {
        if (samples != null) {
            FlexLayout flexLayout = uiComponents.create(FlexLayout.class);
            flexLayout.setAlignContent(FlexLayout.ContentAlignment.START);
            flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
            flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

            flexLayout.setJustifyContentMode(!isSmallDevice()
                    ? FlexComponent.JustifyContentMode.START
                    : FlexComponent.JustifyContentMode.CENTER);
            flexLayout.addClassName(LumoUtility.Gap.LARGE);

            overviewRoot.add(flexLayout);

            List<Element> samplesList = samples.elements();
            for (Element sample : samplesList) {
                VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
                verticalLayout.setPadding(false);
                verticalLayout.setWidth("20em");

                verticalLayout.add(createImage(sample.element("image")));
                verticalLayout.addClassName(LumoUtility.Gap.MEDIUM);

                List<Element> textElements = sample.elements("text");

                for (Element textElement : textElements) {
                    verticalLayout.add(createLabel(textElement, messagesPrefix));
                }

                List<Element> tagElements = sample.elements("tag");
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
        flexLayout.addClassName(LumoUtility.Gap.SMALL);

        for (Element tagElement : tagElements) {
            Component tag = createComponentByName(tagElement.attributeValue("component"));

            tag.getElement().getThemeList().add("badge pill");
            addClassNames(tag, tagElement.attributeValue("classNames"));
            if (tag instanceof HasText hasText) {
                hasText.setText(tagElement.attributeValue("text"));
            }

            String route = tagElement.attributeValue("route");
            String urlQueryParameters = tagElement.attributeValue("queryParameters");
            flexLayout.add(StringUtils.isNotEmpty(route) ? createRoute(tag, route, urlQueryParameters) : tag);
        }

        return flexLayout;
    }

    private Component createImage(Element imageElement) {
        Image image = uiComponents.create(Image.class);
        image.setSrc(imageElement.attributeValue("src"));
        image.setWidth("20em");

        String route = imageElement.attributeValue("route");
        String urlQueryParameters = imageElement.attributeValue("queryParameters");
        return StringUtils.isNotEmpty(route) ? createRoute(image, route, urlQueryParameters) : image;
    }

    private Component createLabel(Element labelElement, String messagesPrefix) {
        Component label = createLabel(
                getMessage(messagesPrefix, labelElement.attributeValue("message")),
                labelElement.attributeValue("classNames"),
                labelElement.attributeValue("component")
        );
        String route = labelElement.attributeValue("route");
        String urlQueryParameters = labelElement.attributeValue("queryParameters");
        return StringUtils.isNotEmpty(route) ? createRoute(label, route, urlQueryParameters) : label;
    }

    private Component createRoute(Component component, String route, String urlQueryParameters) {
        RouterLink routerLink = uiComponents.create(RouterLink.class);
        RouteParameters routeParams = new RouteParameters("sampleId", route);

        routerLink.setRoute(SampleView.class, routeParams);
        routerLink.setQueryParameters(QueryParameters.fromString(urlQueryParameters));
        routerLink.addClassNames("jmix-main-view-header-link");
        routerLink.add(component);

        return routerLink;
    }

    private Component createLabel(String text, String classNames, String componentName) {
        Component component = createComponentByName(componentName);
        if (component instanceof HasText hasText) {
            hasText.setText(text);
        }
        addClassNames(component, classNames);

        return component;
    }

    private Component createComponentByName(@Nullable String componentName) {
        if (Strings.isNullOrEmpty(componentName)) {
            return uiComponents.create(Span.class);
        }

        return switch (componentName) {
            case "h1" -> uiComponents.create(H1.class);
            case "h2" -> uiComponents.create(H2.class);
            case "h3" -> uiComponents.create(H3.class);
            case "h4" -> uiComponents.create(H4.class);
            case "h5" -> uiComponents.create(H5.class);
            case "h6" -> uiComponents.create(H6.class);
            default -> uiComponents.create(Span.class);
        };
    }

    private void initResources(@Nullable Element resources, VerticalLayout overviewRoot, String messagesPrefix) {
        if (resources != null) {
            List<Element> textElements = resources.elements("text");
            VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
            verticalLayout.setPadding(false);
            verticalLayout.addClassName(LumoUtility.Gap.XSMALL);

            for (Element textElement : textElements) {
                String href = textElement.attributeValue("href");
                verticalLayout.add(StringUtils.isNotEmpty(href)
                        ? createAnchor(textElement, messagesPrefix)
                        : createLabel(textElement, messagesPrefix));
            }

            overviewRoot.add(verticalLayout);
        }
    }

    private Component createAnchor(Element textElement, String messagesPrefix) {
        Anchor anchor = uiComponents.create(Anchor.class);
        anchor.setText(getMessage(messagesPrefix, textElement.attributeValue("message")));
        anchor.setHref(textElement.attributeValue("href"));
        anchor.setTarget(AnchorTarget.BLANK);

        anchor.addClassName(LumoUtility.FontWeight.BOLD);
        addClassNames(anchor, textElement.attributeValue("classNames"));

        return anchor;
    }

    private void addClassNames(Component component, String classNames) {
        if (StringUtils.isNotEmpty(classNames)) {
            component.addClassNames(classNames.split(" "));
        }
    }

    private String getMessage(String messagesPrefix, String code) {
        return messages.getMessage(messagesPrefix + "." + code);
    }

    private boolean isSmallDevice() {
        // magic number from vaadin-app-layout.js
        // '--vaadin-app-layout-touch-optimized' style property
        return UI.getCurrent().getInternals().getExtendedClientDetails().getScreenWidth() < 801;
    }
}
