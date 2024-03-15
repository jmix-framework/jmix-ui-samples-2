package io.jmix.uisamples.view.flowui.components.virtuallist.inlineeditor;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.LoadContext;
import io.jmix.core.Messages;
import io.jmix.core.Resources;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.entity.Food;
import io.jmix.uisamples.view.entity.food.FoodDetailView;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ViewController("virtual-list-inline-editor")
@ViewDescriptor("virtual-list-inline-editor.xml")
public class VirtualListInlineEditor extends StandardView {
    private static final String NOOP_DESCRIPTION = "";

    @Autowired
    private Resources resources;
    @Autowired
    private Messages messages;
    @Autowired
    private DialogWindows dialogWindows;

    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionContainer<Food> foodDc;
    @ViewComponent
    private CollectionLoader<Food> foodDl;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        foodDl.load();
    }

    @Supply(to = "foodList", subject = "renderer")
    private Renderer<Food> foodListRenderer() {
        return new ComponentRenderer<>(item -> {
            HorizontalLayout rootCardLayout = new HorizontalLayout();
            rootCardLayout.setMargin(true);
            rootCardLayout.setSpacing(true);

            VerticalLayout infoLayout = new VerticalLayout();
            infoLayout.setMargin(false);
            infoLayout.setSpacing(false);
            infoLayout.setPadding(false);
            infoLayout.setWidth("30%");

            Avatar avatar = new Avatar();
            avatar.addThemeVariants(AvatarVariant.LUMO_XLARGE);

            if(item.getIcon() != null && item.getIcon().length > 0) {
                String iconName = "%s.png".formatted(item.getTitle());
                avatar.setImageResource(new StreamResource(iconName, () -> new ByteArrayInputStream(item.getIcon())));
            }

            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.setWidthFull();

            HorizontalLayout itemDetailLayout = new HorizontalLayout();
            itemDetailLayout.add(new Text(item.getDescription()));
            itemDetailLayout.add(new Html(messages.formatMessage(getClass(), "foodListItemDescription", item.getPrice())));
            itemDetailLayout.setPadding(false);
            itemDetailLayout.setMargin(false);
            itemDetailLayout.setAlignItems(FlexComponent.Alignment.CENTER);

            infoLayout.add(new Html(messages.formatMessage(getClass(), "foodItemTitle", item.getTitle())));
            infoLayout.add(itemDetailLayout);

            VerticalLayout buttonsPanel = new VerticalLayout();
            buttonsPanel.setWidth("AUTO");
            buttonsPanel.setPadding(false);
            buttonsPanel.setMargin(false);
            buttonsPanel.setSpacing(false);

            Button detailButton = new Button(new Icon(VaadinIcon.PENCIL));
            detailButton.setText(messages.getMessage("actions.Edit"));
            detailButton.addClickListener(e -> dialogWindows.detail(this, Food.class)
                    .withViewClass(FoodDetailView.class)
                    .editEntity(item)
                    .withParentDataContext(getViewData().getDataContext())
                    .withAfterCloseListener(closeEvent -> {
                        FoodDetailView detail = closeEvent.getSource().getView();
                        if (closeEvent.closedWith(StandardOutcome.SAVE)) {
                            foodDc.replaceItem(detail.getEditedEntity());
                        }
                    })
                    .build()
                    .open());
            detailButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

            Button removeButton = new Button(new Icon(VaadinIcon.TRASH));
            removeButton.setText(messages.getMessage("actions.Remove"));
            removeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_ERROR);
            removeButton.addClickListener(e -> {
                foodDc.getMutableItems().remove(item);
                dataContext.remove(dataContext.merge(item));
            });


            buttonsPanel.add(detailButton, removeButton);
            rootCardLayout.add(avatar, infoLayout, buttonsPanel);
            return rootCardLayout;
        });

    }

    @Install(to = "foodDl", target = Target.DATA_LOADER)
    private List<Food> foodDlLoadDelegate(final LoadContext<Food> loadContext) {
        return getInitialFoodList();
    }

    private Food createFoodSample(String title,
                                  String description,
                                  int price,
                                  String iconLocation) {

        Food food = dataContext.create(Food.class);
        food.setTitle(title);
        food.setDescription(description);
        food.setPrice(price);
        try (InputStream imageStream = resources.getResourceAsStream(iconLocation)) {
            food.setIcon(imageStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot open a stream", e);
        }
        return food;
    }

    private List<Food> getInitialFoodList() {
        List<Food> foodList = new ArrayList<Food>();
        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.burger.title"),
                messages.getMessage(getClass(), "sample.burger.description"),
                7, "META-INF/resources/icons/components/virtuallist-food/burger.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.cola.title"),
                messages.getMessage(getClass(), "sample.cola.description"),
                4, "META-INF/resources/icons/components/virtuallist-food/cola.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.fries.title"),
                messages.getMessage(getClass(), "sample.fries.description"),
                3, "META-INF/resources/icons/components/virtuallist-food/fries.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.pizza.title"),
                messages.getMessage(getClass(), "sample.pizza.description"),
                15, "META-INF/resources/icons/components/virtuallist-food/pizza.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.sandwich.title"),
                NOOP_DESCRIPTION,
                2, "META-INF/resources/icons/components/virtuallist-food/sandwich.png"));
        return foodList;
    }

    @Subscribe("addAction")
    public void onAddAction(final ActionPerformedEvent event) {
        dialogWindows.detail(this, Food.class)
                .withViewClass(FoodDetailView.class)
                .newEntity()
                .withParentDataContext(getViewData().getDataContext())
                .withAfterCloseListener(closeEvent -> {
                    FoodDetailView detail = closeEvent.getSource().getView();
                    if (closeEvent.closedWith(StandardOutcome.SAVE)) {
                        foodDc.replaceItem(detail.getEditedEntity());
                    }
                })
                .build()
                .open();
    }
}