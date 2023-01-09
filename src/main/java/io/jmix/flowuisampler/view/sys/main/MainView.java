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

package io.jmix.flowuisampler.view.sys.main;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
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
import io.jmix.flowuisampler.config.SamplerMenuConfig;
import io.jmix.flowuisampler.config.SamplerMenuItem;
import io.jmix.flowuisampler.view.sys.browser.SampleBrowser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
@AnonymousAllowed
public class MainView extends StandardMainView {

    @ViewComponent
    protected JmixListMenu menu;
    @ViewComponent
    protected TypedTextField<String> searchField;

    @Autowired
    protected SamplerMenuConfig menuConfig;
    @Autowired
    protected UiComponents uiComponents;

    protected List<JmixListMenu.MenuItem> foundItems = new ArrayList<>();
    protected List<String> parentListIdsToExpand = new ArrayList<>();

    @Subscribe
    public void onInit(InitEvent event) {
        initSideMenu();
    }

    protected void initSideMenu() {
        menu.removeAllMenuItems();
        initMenuItems();
        initSearchField();

        searchField.focus();
    }

    protected void initMenuItems() {
        List<SamplerMenuItem> menuItems = menuConfig.getRootItems();

        for (SamplerMenuItem item : menuItems) {
            ListMenu.MenuBarItem menuItem = new ListMenu.MenuBarItem(item.getId())
                    .withTitle(menuConfig.getMenuItemTitle(item.getId()));

            loadMenuItems(item, menuItem);

            menu.addMenuItem(menuItem);
        }
        UI.getCurrent().addAfterNavigationListener(event -> updateTitle());
    }

    protected void loadMenuItems(SamplerMenuItem parentSamplerItem,
                                 ListMenu.MenuBarItem parentSideMenuItem) {
        for (SamplerMenuItem currentItem : parentSamplerItem.getChildren()) {
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
                        .withControllerClass(SampleBrowser.class)
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
        searchButton.addThemeVariants(ButtonVariant.LUMO_ICON);

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
                    ListMenu.MenuItem menuItem = menu.getMenuItem(item.getId());
                    if (menuItem != null) {
                        UnorderedList content = menuItem.getMenuComponent().getContent();
                        expand(menuItem, content, true);
                    }
            }

            removeNotRequestedItems(menu.getMenuItems(), null, null, searchValue);
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
                                           @Nullable UnorderedList content,
                                           String searchValue) {
        for (JmixListMenu.MenuItem item : list) {
            content = content == null
                    ? item.getMenuComponent().getContent()
                    : (UnorderedList) getChildContent(content).orElse(null);

            if (item.isMenu()) {
                Optional<Component> childDetail = getChildDetail(content);

                if (childDetail.isPresent() && !((Details) childDetail.get()).isOpened()) {
                    if (parentItem != null) {
                        parentItem.removeChildItem(item);
                    }
                    menu.removeMenuItem(item);
                } else if (!StringUtils.containsIgnoreCase(item.getTitle(), searchValue)) {
                    ListMenu.MenuBarItem menuBarItem = (ListMenu.MenuBarItem) item;
                    removeNotRequestedItems(menuBarItem.getChildren(), menuBarItem, content, searchValue);
                }
            } else if (!StringUtils.containsIgnoreCase(item.getTitle(), searchValue)) {
                if (parentItem != null) {
                    parentItem.removeChildItem(item);
                }
                menu.removeMenuItem(item);
            }
        }
    }


    @SuppressWarnings("ConstantConditions")
    @Subscribe("collapseAllBtn")
    public void onCollapseAllBtnClick(ClickEvent<Button> event) {
        for (JmixListMenu.MenuItem item : menu.getMenuItems()) {
            UnorderedList content = item.getMenuComponent().getContent();
            expand(item, content, false);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Subscribe("expandAllBtn")
    public void onExpandAllBtnClick(ClickEvent<Button> event) {
        for (JmixListMenu.MenuItem item : menu.getMenuItems()) {
            UnorderedList content = item.getMenuComponent().getContent();
            expand(item, content, true);
        }
    }

    protected void expand(JmixListMenu.MenuItem item, UnorderedList content, boolean isExpand) {
        if (item.isMenu()) {
            findDetailAndExpand(content, isExpand);

            for (JmixListMenu.MenuItem menuItem : ((ListMenu.MenuBarItem) item).getChildren()) {
                Optional<Component> childContent = getChildContent(content);
                if (menuItem.isMenu() && childContent.isPresent()) {
                    expand(menuItem, ((UnorderedList) childContent.get()), isExpand);
                }
            }
        }
    }

    protected void findDetailAndExpand(UnorderedList content, boolean isExpand) {
        content.getChildren()
                .forEach(component -> component.getChildren().findAny()
                        .ifPresent(component1 -> ((Details)component1).setOpened(isExpand))
                );
    }

    protected Optional<Component> getChildContent(UnorderedList content) {
        return getChildDetail(content)
                .flatMap(component -> ((Details) component).getContent().findAny());
    }

    protected Optional<Component> getChildDetail(UnorderedList content) {
        return content.getChildren()
                .findAny()
                .flatMap(component -> component.getChildren().findAny());
    }
}
