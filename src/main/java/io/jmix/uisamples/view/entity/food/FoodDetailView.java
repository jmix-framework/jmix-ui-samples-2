package io.jmix.uisamples.view.entity.food;


import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.component.SupportsTypedValue;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.upload.FileUploadField;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Food;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.Objects;

@DialogMode(minWidth = "15em", minHeight = "10pm")
@EditedEntityContainer("foodDc")
@ViewController("Food.detail")
@ViewDescriptor("food-detail-view.xml")
public class FoodDetailView extends StandardDetailView<Food> {

    @ViewComponent
    private Avatar foodIcon;
    @ViewComponent
    private FileUploadField foodIconUpload;
    @ViewComponent
    private H2 foodTitle;

    @Subscribe
    public void obBeforeShow(final BeforeShowEvent event) {
        initIconFields();
    }

    @Subscribe("titleField")
    public void onNameFieldTypedValueChange(final SupportsTypedValue.TypedValueChangeEvent<TypedTextField<String>, String> event) {
        foodTitle.setText(event.getValue());
    }

    @Subscribe("foodIconUpload")
    public void onFoodIconUploadFileUploadSucceeded(final FileUploadSucceededEvent<FileUploadField> event) {
        getEditedEntity().setIcon(Objects.requireNonNull(foodIconUpload.getValue()));
        getViewData().getDataContext().setModified(getEditedEntity(), true);
        initIconFields();
    }

    private void initIconFields() {
        if (StringUtils.isNotBlank(getEditedEntity().getTitle())) {
            foodTitle.setText(getEditedEntity().getTitle());
        }
        byte[] iconArray = getEditedEntity().getIcon();
        if (iconArray != null && iconArray.length > 0) {
            try {
                String iconFileName = "%s.png".formatted(getEditedEntity().getTitle());
                foodIcon.setImageResource(new StreamResource(iconFileName, () -> new ByteArrayInputStream(getEditedEntity().getIcon())));
                foodIconUpload.setValue(iconArray);
            } catch (RuntimeException e) {
                throw new RuntimeException("Cannot init fields on food", e);
            }
        }
    }
}