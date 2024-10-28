package io.jmix.uisamples.view.flowui.cookbook.wizard.step;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.data.binding.HtmlContainerReadonlyDataBinding;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragment.FragmentUtils;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.ViewData;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.uisamples.entity.Employee;
import io.jmix.uisamples.view.flowui.cookbook.wizard.WizardStepFragment;
import org.springframework.beans.factory.annotation.Autowired;

@FragmentDescriptor("third-step.xml")
public class ThirdStep extends Fragment<VerticalLayout> implements WizardStepFragment {

    @Autowired
    private HtmlContainerReadonlyDataBinding htmlDataBinding;

    @ViewComponent
    private Div employeeData;
    @ViewComponent
    private Div addressData;

    @Override
    public void setupData(ViewData viewData) {
        InstanceContainer<Employee> employeeDc = viewData.getContainer("employeeDc");
        InstanceContainer<Employee> addressDc = viewData.getContainer("addressDc");

        bindChildData(employeeData, employeeDc);
        bindChildData(addressData, addressDc);
    }

    private void bindChildData(Div div, InstanceContainer<?> dataContainer) {
        div.getChildren().forEach(component -> {
            if (component instanceof HtmlContainer htmlContainer) {
                FragmentUtils.getComponentId(htmlContainer)
                        .ifPresent(id -> bindHtmlContainer(htmlContainer, id, dataContainer));
            }
        });
    }

    private void bindHtmlContainer(HtmlContainer htmlContainer, String id, InstanceContainer<?> dataContainer) {
        htmlDataBinding.bind(htmlContainer, dataContainer, id);
    }
}
