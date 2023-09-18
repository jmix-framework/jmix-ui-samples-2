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

package io.jmix.uisamples.config;

import io.jmix.core.Messages;
import io.jmix.core.Resources;
import io.jmix.core.common.xmlparsing.Dom4jTools;
import io.jmix.flowui.xml.layout.support.LoaderSupport;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.StringTokenizer;
import org.dom4j.Element;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

@Component("uisamples_MenuConfig")
public class UiSamplesMenuConfig {

    public static final String MENU_CONFIG_XML_PROP = "jmix.uisamples.menu-config";

    private static final Log log = LogFactory.getLog(UiSamplesMenuConfig.class);

    protected final Dom4jTools dom4JTools;
    protected final Environment environment;
    protected final Resources resources;
    protected final Messages messages;
    protected final LoaderSupport loaderSupport;

    protected List<UiSamplesMenuItem> rootItems = new ArrayList<>();

    protected final ReadWriteLock lock = new ReentrantReadWriteLock();
    protected volatile boolean initialized;

    public UiSamplesMenuConfig(Dom4jTools dom4JTools, Environment environment, Resources resources,
                               Messages messages, LoaderSupport loaderSupport) {
        this.dom4JTools = dom4JTools;
        this.environment = environment;
        this.resources = resources;
        this.messages = messages;
        this.loaderSupport = loaderSupport;
    }

    public String getMenuItemTitle(String id) {
        try {
            return messages.getMessage("uisamples-menu-config." + id);
        } catch (MissingResourceException e) {
            return id;
        }
    }

    protected void checkInitialized() {
        if (!initialized) {
            lock.readLock().unlock();
            lock.writeLock().lock();

            try {
                if (!initialized) {
                    init();
                    initialized = true;
                }
            } finally {
                lock.readLock().lock();
                lock.writeLock().unlock();
            }
        }
    }

    protected void init() {
        rootItems.clear();

        String configName = environment.getProperty(MENU_CONFIG_XML_PROP);
        StringTokenizer tokenizer = new StringTokenizer(configName);

        for (String location : tokenizer.getTokenArray()) {
            Resource resource = resources.getResource(location);

            if (resource.exists()) {
                try (InputStream stream = resource.getInputStream()) {
                    loadMenuItems(dom4JTools.readDocument(stream).getRootElement(), null);
                } catch (IOException e) {
                    throw new RuntimeException("Unable to read samples menu config from " + location, e);
                }
            } else {
                log.warn("Resource " + location + " not found, ignore it");
            }
        }
    }

    /**
     * Make the config to reload screens on next request.
     */
    public void reset() {
        initialized = false;
    }

    /**
     * Main menu root items
     */
    public List<UiSamplesMenuItem> getRootItems() {
        lock.readLock().lock();
        try {
            checkInitialized();
            return Collections.unmodifiableList(rootItems);
        } finally {
            lock.readLock().unlock();
        }
    }

    protected void loadMenuItems(Element parentElement, @Nullable UiSamplesMenuItem parentItem) {
        for (Element element : parentElement.elements()) {
            UiSamplesMenuItem menuItem = null;
            String id = element.attributeValue("id");

            if (StringUtils.isNotBlank(id)) {
                if ("menu".equals(element.getName())) {
                    menuItem = new UiSamplesMenuItem(parentItem, id);
                    menuItem.setMenu(true);

                    loadString(element, "image", menuItem::setImage);
                    loadString(element, "url", menuItem::setUrl);

                    loadMenuItems(element, menuItem);
                } else if ("item".equals(element.getName())) {
                    menuItem = parseItem(element, parentItem, id);
                } else {
                    log.warn(String.format("Unknown tag '%s' in sample-config", element.getName()));
                }
            } else {
                log.warn("Invalid sample-config: 'id' attribute not defined for tag" + element.getName());
            }

            if (parentItem != null && menuItem != null) {
                parentItem.addChild(menuItem);
            } else {
                rootItems.add(menuItem);
            }
        }
    }

    protected UiSamplesMenuItem parseItem(Element element, UiSamplesMenuItem parentItem, String id) {
        UiSamplesMenuItem menuItem = new UiSamplesMenuItem(parentItem, id);

        loadString(element, "page", menuItem::setPage);
        loadString(element, "url", menuItem::setUrl);
        loadString(element, "anchor", menuItem::setAnchor);
        loadString(element, "image", menuItem::setImage);

        loaderSupport.loadBoolean(element, "splitEnabled")
                .ifPresent(menuItem::setSplitEnabled);

        Element otherFilesElement = element.element("otherFiles");
        if (otherFilesElement != null && !otherFilesElement.elements().isEmpty()) {
            List<String> otherFiles = new ArrayList<>();
            for (Element file : otherFilesElement.elements()) {
                String fileName = file.attributeValue("name");
                if (StringUtils.isNotEmpty(fileName))
                    otherFiles.add(fileName);
            }
            menuItem.setOtherFiles(otherFiles);
        }

        Element screenParamsElement = element.element("screenParams");
        if (screenParamsElement != null && !screenParamsElement.elements().isEmpty()) {
            Map<String, Object> params = new HashMap<>();
            for (Element param : screenParamsElement.elements()) {
                String paramName = param.attributeValue("name");
                if (StringUtils.isNotEmpty(paramName)) {
                    String value = param.attributeValue("value");
                    params.put(paramName, value);
                }
            }
            menuItem.setViewParams(params);
        }

        return menuItem;
    }

    public UiSamplesMenuItem getItemById(String id) {
        UiSamplesMenuItem menuItem = IterableUtils.find(getItemsAsList(), new UiSamplesMenuItemPredicate(id));

        if (menuItem == null) {
            throw new IllegalArgumentException("Unable to find item with id " + id);
        }
        return menuItem;
    }

    protected List<UiSamplesMenuItem> getItemsAsList() {
        return getItemsAsList(getRootItems());
    }

    protected List<UiSamplesMenuItem> getItemsAsList(List<UiSamplesMenuItem> allItems) {
        List<UiSamplesMenuItem> items = new ArrayList<>();
        for (UiSamplesMenuItem item : allItems) {
            items.add(item);
            if (item.isMenu())
                items.addAll(getItemsAsList(item.getChildren()));
        }
        return items;
    }

    /**
     * @param itemId id of parent item that contains children
     * @return List of items.
     */
    public List<UiSamplesMenuItem> getAllChildrenAsList(String itemId) {
        UiSamplesMenuItem item = getItemById(itemId);
        List<UiSamplesMenuItem> items = getItemsAsList(Collections.singletonList(item));

        return setCategoriesForSingleItems(items);
    }

    protected List<UiSamplesMenuItem> setCategoriesForSingleItems(List<UiSamplesMenuItem> items) {
        for (int i = 0; i < items.size(); i++) {
            UiSamplesMenuItem item = items.get(i);

            if (item.isMenu()) {
                List<UiSamplesMenuItem> withoutChildrenList = getItemsWithoutChildren(item.getChildren());

                if (!withoutChildrenList.isEmpty()) {
                    for (UiSamplesMenuItem menuItem : withoutChildrenList) {
                        int index = items.indexOf(menuItem);

                        UiSamplesMenuItem itemLabel = new UiSamplesMenuItem(menuItem.getParent(), menuItem.getId());
                        itemLabel.setMenu(true);
                        items.add(index, itemLabel);
                    }
                }
            }
        }
        return items;
    }

    protected List<UiSamplesMenuItem> getItemsWithoutChildren(List<UiSamplesMenuItem> items) {
        List<UiSamplesMenuItem> itemList = new ArrayList<>();

        for (UiSamplesMenuItem item : items) {
            if (!item.isMenu())
                itemList.add(item);
        }

        if (itemList.size() == items.size()) {
            return Collections.emptyList();
        }

        return itemList;
    }

    public boolean isRootItem(String itemId) {
        List<UiSamplesMenuItem> rootsItem = getRootItems();
        return rootsItem.stream()
                .anyMatch(menuItem -> menuItem.getId().equals(itemId));
    }

    protected Optional<String> loadString(Element element, String attributeName) {
        return loaderSupport.loadString(element, attributeName);
    }

    protected void loadString(Element element, String attributeName, Consumer<String> setter) {
        loadString(element, attributeName)
                .ifPresent(setter);
    }
}
