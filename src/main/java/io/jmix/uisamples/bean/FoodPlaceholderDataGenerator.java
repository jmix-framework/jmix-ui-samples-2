package io.jmix.uisamples.bean;

import io.jmix.core.Messages;
import io.jmix.core.Metadata;
import io.jmix.core.Resources;
import io.jmix.uisamples.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("uisamples_FoodPlaceholderDataGenerator")
public class FoodPlaceholderDataGenerator {
    private static final String NOOP_DESCRIPTION = "";

    @Autowired
    private Metadata metadata;
    @Autowired
    private Messages messages;
    @Autowired
    private Resources resources;

    public List<Food> getFoodSamplesList() {
        List<Food> foodList = new ArrayList<>();
        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.food.burger.title"),
                messages.getMessage(getClass(), "sample.food.burger.description"),
                7, "META-INF/resources/icons/components/virtuallist-food/burger.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.food.cola.title"),
                messages.getMessage(getClass(), "sample.food.cola.description"),
                4, "META-INF/resources/icons/components/virtuallist-food/cola.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.food.fries.title"),
                messages.getMessage(getClass(), "sample.food.fries.description"),
                3, "META-INF/resources/icons/components/virtuallist-food/fries.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.food.pizza.title"),
                messages.getMessage(getClass(), "sample.food.pizza.description"),
                15, "META-INF/resources/icons/components/virtuallist-food/pizza.png"));

        foodList.add(createFoodSample(messages.getMessage(getClass(), "sample.food.sandwich.title"),
                NOOP_DESCRIPTION,
                2, "META-INF/resources/icons/components/virtuallist-food/sandwich.png"));
        return foodList;
    }

    private Food createFoodSample(String title,
                                  String description,
                                  int price,
                                  String iconLocation) {
        Food food = metadata.create(Food.class);
        food.setTitle(title);
        food.setDescription(description);
        food.setPrice(price);
        try (InputStream imageStream = resources.getResourceAsStream(iconLocation)) {
            food.setIcon(Objects.requireNonNull(imageStream).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot open a stream", e);
        }
        return food;
    }
}
