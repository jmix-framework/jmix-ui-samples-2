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

package io.jmix.uisamples.view.sys.main;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.session.SessionData;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.component.main.JmixListMenu;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.main.ListMenu;
import io.jmix.flowui.menu.MenuItem;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.bean.MenuNavigationExpander;
import io.jmix.uisamples.component.themeswitcher.ThemeToggle;
import io.jmix.uisamples.config.UiSamplesMenuConfig;
import io.jmix.uisamples.config.UiSamplesMenuItem;
import io.jmix.uisamples.view.sys.sampleview.SampleView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
@AnonymousAllowed
public class MainView extends StandardMainView {

    public static final String CURRENT_THEME_SESSION_ATTRIBUTE = "currentTheme";

    @ViewComponent
    protected JmixListMenu menu;
    @ViewComponent
    protected TypedTextField<String> searchField;
    @ViewComponent
    protected ThemeToggle themeToggle;

    @Autowired
    protected UiSamplesMenuConfig menuConfig;
    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected ObjectProvider<SessionData> sessionDataProvider;
    @Autowired
    protected MenuNavigationExpander menuNavigationExpander;

    protected List<JmixListMenu.MenuItem> foundItems = new ArrayList<>();
    protected List<String> parentListIdsToExpand = new ArrayList<>();

    @Subscribe
    public void onInit(InitEvent event) {
        initSideMenu();
        initThemeSessionAttribute();

        menuNavigationExpander.setExpandCallback(this::expandAllParentRecursively);
    }

    protected void initThemeSessionAttribute() {
        SessionData sessionData = sessionDataProvider.getIfAvailable();

        if (sessionData != null) {
            themeToggle.getElement().executeJs("return this.getCurrentTheme();")
                    .then(String.class, currentTheme ->
                            sessionData.setAttribute(CURRENT_THEME_SESSION_ATTRIBUTE, currentTheme));
        }

        ComponentUtil.addListener(themeToggle, ThemeToggle.ThemeToggleThemeChangedEvent.class, event -> {
            String currentTheme = event.getValue();

            if (sessionData != null) {
                sessionData.setAttribute(CURRENT_THEME_SESSION_ATTRIBUTE, currentTheme);
            }

            ThemeChangedEvent themeChangedEvent = new ThemeChangedEvent(this, currentTheme);
            getApplicationContext().publishEvent(themeChangedEvent);
        });
    }

    protected void initSideMenu() {
        menu.removeAllMenuItems();
        initMenuItems();
        initSearchField();

        searchField.focus();
    }

    protected void initMenuItems() {
        List<UiSamplesMenuItem> menuItems = menuConfig.getRootItems();

        for (UiSamplesMenuItem item : menuItems) {
            ListMenu.MenuBarItem menuItem = new ListMenu.MenuBarItem(item.getId())
                    .withTitle(menuConfig.getMenuItemTitle(item.getId()));

            loadMenuItems(item, menuItem);

            menu.addMenuItem(menuItem);
        }
        UI.getCurrent().addAfterNavigationListener(event -> updateTitle());
    }

    protected void loadMenuItems(UiSamplesMenuItem parentUiSamplesItem,
                                 ListMenu.MenuBarItem parentSideMenuItem) {
        for (UiSamplesMenuItem currentItem : parentUiSamplesItem.getChildren()) {
            String id = currentItem.getId();

            if (currentItem.isMenu()) {
                ListMenu.MenuBarItem child = new ListMenu.MenuBarItem(id)
                        .withTitle(menuConfig.getMenuItemTitle(currentItem.getId()));

                loadMenuItems(currentItem, child);
                parentSideMenuItem.addChildItem(child);
            } else {
                MenuItem.MenuItemParameter sampleIdParam = new MenuItem.MenuItemParameter("sampleId", id);

                JmixListMenu.ViewMenuItem child = new JmixListMenu.ViewMenuItem(id)
                        .withTitle(menuConfig.getMenuItemTitle(currentItem.getId()))
                        .withControllerClass(SampleView.class)
                        .withRouteParameters(Collections.singletonList(sampleIdParam));

                parentSideMenuItem.addChildItem(child);
            }
        }
    }

    protected void initSearchField() {
        JmixButton searchButton = createSearchButton();

        searchField.setSuffixComponent(searchButton);
        searchField.addKeyPressListener(Key.ENTER, this::searchFieldEnterPressListener);
    }

    protected JmixButton createSearchButton() {
        JmixButton searchButton = uiComponents.create(JmixButton.class);
        searchButton.setIcon(VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);

        searchButton.addClickListener(this::searchButtonClickListener);

        return searchButton;
    }

    protected void searchFieldEnterPressListener(KeyPressEvent keyPressEvent) {
        search(searchField.getValue());
    }

    protected void searchButtonClickListener(ClickEvent<Button> buttonClickEvent) {
        search(searchField.getValue());
    }

    @SuppressWarnings("ConstantConditions")
    protected void search(String searchValue) {
        foundItems.clear();
        menu.removeAllMenuItems();
        initMenuItems();

        if (!StringUtils.isEmpty(searchValue)) {
            findItemsRecursively(menu.getMenuItems(), searchValue);

            for (JmixListMenu.MenuItem item : foundItems) {
                if (menuConfig.getItemById(item.getId()).getParent() != null) {
                    expandAllParentRecursively(item.getId());
                }

                ListMenu.MenuItem menuItem = menu.getMenuItem(item.getId());
                if (menuItem.isMenu()
                        && menuItem instanceof ListMenu.MenuBarItem menuBarItem
                        && !menuBarItem.getChildren().isEmpty()) {
                    expand(menuItem, true);
                }
            }

            removeNotRequestedItems(List.copyOf(menu.getMenuItems()), null, searchValue);
        }
    }

    protected void findItemsRecursively(List<JmixListMenu.MenuItem> items, String searchValue) {
        for (JmixListMenu.MenuItem item : items) {
            if (StringUtils.containsIgnoreCase(item.getTitle(), searchValue)) {
                foundItems.add(item);
            }
            if (item.isMenu()) {
                findItemsRecursively(((ListMenu.MenuBarItem) item).getChildren(), searchValue);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    protected void removeNotRequestedItems(List<JmixListMenu.MenuItem> list,
                                           @Nullable ListMenu.MenuBarItem parentItem,
                                           String searchValue) {
        for (JmixListMenu.MenuItem item : list) {
            if (item.isMenu() && item instanceof ListMenu.MenuBarItem menuItem && menuItem.hasChildren()) {
                if (!menuItem.isOpened()) {
                    menu.removeMenuItem(item);
                } else if (!StringUtils.containsIgnoreCase(item.getTitle(), searchValue)) {
                    ListMenu.MenuBarItem menuBarItem = (ListMenu.MenuBarItem) item;
                    removeNotRequestedItems(menuBarItem.getChildren(), menuBarItem, searchValue);
                }
            } else if (!StringUtils.containsIgnoreCase(item.getTitle(), searchValue)) {
                menu.removeMenuItem(item);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Subscribe("collapseAllBtn")
    public void onCollapseAllBtnClick(ClickEvent<Button> event) {
        for (JmixListMenu.MenuItem item : menu.getMenuItems()) {
            expand(item, false);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Subscribe("expandAllBtn")
    public void onExpandAllBtnClick(ClickEvent<Button> event) {
        for (JmixListMenu.MenuItem item : menu.getMenuItems()) {
            expand(item, true);
        }
    }

    @Subscribe("applicationTitlePlaceholder")
    protected void onApplicationTitleClick(ClickEvent<Div> event) {
        UI.getCurrent().navigate(getClass());
    }

    protected void expand(JmixListMenu.MenuItem item, boolean isExpand) {
        if (item.isMenu()) {
            ((ListMenu.MenuBarItem) item).setOpened(isExpand);

            for (JmixListMenu.MenuItem menuItem : ((ListMenu.MenuBarItem) item).getChildren()) {
                if (menuItem.isMenu()) {
                    expand(menuItem, isExpand);
                }
            }
        }
    }

    protected void expandAllParentRecursively(String id) {
        parentListIdsToExpand.clear();
        fillParentListToExpand(id);

        for (String parentId : parentListIdsToExpand) {
            ListMenu.MenuItem menuItem = menu.getMenuItem(parentId);

            if (menuItem instanceof ListMenu.MenuBarItem menuBarItem) {
                menuBarItem.setOpened(true);
            }
        }
    }

    protected void fillParentListToExpand(String id) {
        UiSamplesMenuItem itemToExpand = menuConfig.getItemById(id);
        if (itemToExpand.getParent() != null) {
            parentListIdsToExpand.add(itemToExpand.getParent().getId());
            fillParentListToExpand(itemToExpand.getParent().getId());
        }
    }

    public static class ThemeChangedEvent extends ApplicationEvent {
        protected final String theme;

        public ThemeChangedEvent(MainView source, String theme) {
            super(source);
            this.theme = theme;
        }

        public String getTheme() {
            return theme;
        }
    }
}
