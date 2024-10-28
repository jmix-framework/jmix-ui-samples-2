package io.jmix.uisamples.view.flowui.fragments.renderer;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.uisamples.entity.Employee;

@FragmentDescriptor("employee-info-fragment.xml")
@RendererItemContainer("employeeDc")
public class EmployeeInfoFragment extends FragmentRenderer<VerticalLayout, Employee> {
}
