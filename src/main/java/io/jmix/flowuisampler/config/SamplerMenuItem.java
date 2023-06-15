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

package io.jmix.flowuisampler.config;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SamplerMenuItem {

    protected final SamplerMenuItem parent;
    protected final List<SamplerMenuItem> children = new ArrayList<>();
    protected final String id;

    protected String url;
    protected String page;
    protected String anchor;
    protected String image;
    protected boolean splitEnabled;
    protected List<String> otherFiles;
    protected Map<String, Object> viewParams;

    protected boolean isMenu = false;

    public SamplerMenuItem(@Nullable SamplerMenuItem parent, String id) {
        this.parent = parent;
        this.id = id;
    }

    @Nullable
    public SamplerMenuItem getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    public boolean isSplitEnabled() {
        return splitEnabled;
    }

    public void setSplitEnabled(boolean splitEnabled) {
        this.splitEnabled = splitEnabled;
    }

    public List<String> getOtherFiles() {
        if (otherFiles == null)
            return Collections.emptyList();
        return otherFiles;
    }

    public void setOtherFiles(List<String> otherFiles) {
        this.otherFiles = otherFiles;
    }

    @Nullable
    public String getUrl() {
        SamplerMenuItem currentParent = parent;
        while (url == null && currentParent != null) {
            url = currentParent.getUrl();
            currentParent = currentParent.getParent();
        }

        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getPage() {
        return page;
    }

    public void setPage(@Nullable String page) {
        this.page = page;
    }

    @Nullable
    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(@Nullable String anchor) {
        this.anchor = anchor;
    }

    public List<SamplerMenuItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(SamplerMenuItem item) {
        children.add(item);
    }

    public Map<String, Object> getViewParams() {
        return viewParams != null
                ? Collections.unmodifiableMap(viewParams)
                : Collections.emptyMap();
    }

    public void setViewParams(Map<String, Object> viewParams) {
        this.viewParams = viewParams;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return id;
    }
}
