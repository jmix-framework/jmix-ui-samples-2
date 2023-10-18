package io.jmix.uisamples.view.flowui.components.listbox.customrenderer;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.jmix.core.Resources;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.listbox.JmixListBox;
import io.jmix.flowui.view.*;
import io.jmix.uisamples.record.Simpson;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@ViewController("list-box-custom-renderer")
@ViewDescriptor("list-box-custom-renderer.xml")
public class ListBoxCustomRendererSample extends StandardView {

    protected static final String PATH_PREFIX = "META-INF/resources/icons/";

    @ViewComponent
    protected JmixListBox<Simpson> listBox;

    @Autowired
    protected UiComponents uiComponents;
    @Autowired
    protected Resources resources;

    @Subscribe
    protected void onInit(InitEvent event) {
        listBox.setItems(getListBoxItems());
    }

    @Supply(to = "listBox", subject = "renderer")
    protected ComponentRenderer<HorizontalLayout, Simpson> listBoxRenderer() {
        return new ComponentRenderer<>(simpson -> {
            HorizontalLayout row = uiComponents.create(HorizontalLayout.class);
            row.setAlignItems(FlexComponent.Alignment.CENTER);

            Avatar avatar = uiComponents.create(Avatar.class);
            avatar.setImageResource(getSimpsonImage(simpson));

            Span name = new Span(simpson.name());
            Span profession = new Span(simpson.shortName());
            profession.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL);

            VerticalLayout column = uiComponents.create(VerticalLayout.class);
            column.add(name, profession);
            column.setPadding(false);
            column.setSpacing(false);

            row.add(avatar, column);
            row.addClassName(LumoUtility.LineHeight.MEDIUM);
            return row;
        });
    }

    protected List<Simpson> getListBoxItems() {
        return List.of(
                new Simpson("Homer Jay Simpson", "Homer", "homer-simpson.png"),
                new Simpson("Marjorie Jacqueline Simpson", "Marge", "marge-simpson.png"),
                new Simpson("Bartholomew Jojo Simpson", "Bart", "bart-simpson.png")
        );
    }

    protected StreamResource getSimpsonImage(Simpson simpson) {
        String picture = simpson.picture();
        InputStream image = resources.getResourceAsStream(PATH_PREFIX + picture);

        return new StreamResource(UUID.randomUUID().toString(), () -> image);
    }
}
