package io.jmix.uisamples.view.flowui.components.codeeditor.customsuggestions;

import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.flowui.kit.component.codeeditor.autocomplete.Suggester;
import io.jmix.flowui.kit.component.codeeditor.autocomplete.Suggestion;
import io.jmix.flowui.view.Install;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.uisamples.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ViewController("code-editor-custom-suggestions")
@ViewDescriptor("code-editor-custom-suggestions.xml")
public class CodeEditorCustomSuggestionsSample extends StandardView {

    @Autowired
    private DataManager dataManager;

    @Install(to = "codeEditor", subject = "suggester")
    public List<Suggestion> codeEditorSuggestions(Suggester.SuggestionContext context) {
        return loadCustomers().stream()
                .map(customer -> new Suggestion(customer.getInstanceName(), customer.getInstanceName(), "Customer"))
                .toList();
    }

    private List<Customer> loadCustomers() {
        return dataManager.load(Customer.class)
                .all()
                .fetchPlan(FetchPlan.INSTANCE_NAME)
                .list();
    }
}
