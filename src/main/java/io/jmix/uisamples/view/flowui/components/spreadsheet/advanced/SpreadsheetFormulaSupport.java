package io.jmix.uisamples.view.flowui.components.spreadsheet.advanced;

import com.vaadin.flow.component.spreadsheet.Spreadsheet;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("uisamples_SpreadsheetFormulaSupport")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SpreadsheetFormulaSupport {

    private final Spreadsheet spreadsheet;

    public SpreadsheetFormulaSupport(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public void setupRevenueFormulas() {
        spreadsheet.getCell(14, 3).setCellFormula("SUM(D12:D14)");
        spreadsheet.getCell(14, 4).setCellFormula("SUM(E12:E14)");

        spreadsheet.getCell(11, 5).setCellFormula("E12-D12");
        spreadsheet.getCell(12, 5).setCellFormula("E13-D13");
        spreadsheet.getCell(13, 5).setCellFormula("E14-D14");
        spreadsheet.getCell(14, 5).setCellFormula("SUM(F12:F14)");

        spreadsheet.getCell(11, 6).setCellFormula("(E12-D12)/D12");
        spreadsheet.getCell(12, 6).setCellFormula("(E13-D13)/D13");
        spreadsheet.getCell(13, 6).setCellFormula("(E14-D14)/D14");
        spreadsheet.getCell(14, 6).setCellFormula("(E15-D15)/D15");
    }

    public void setupCostOfSalesFormulas() {
        spreadsheet.getCell(23, 3).setCellFormula("SUM(D19:D23)");
        spreadsheet.getCell(23, 4).setCellFormula("SUM(E19:E23)");

        spreadsheet.getCell(18, 5).setCellFormula("E19-D19");
        spreadsheet.getCell(19, 5).setCellFormula("E20-D20");
        spreadsheet.getCell(20, 5).setCellFormula("E21-D21");
        spreadsheet.getCell(22, 5).setCellFormula("E23-D23");
        spreadsheet.getCell(23, 5).setCellFormula("SUM(F19:F21)");

        spreadsheet.getCell(18, 6).setCellFormula("(E19-D19)/D19");
        spreadsheet.getCell(19, 6).setCellFormula("(E20-D20)/D20");
        spreadsheet.getCell(20, 6).setCellFormula("(E21-D21)/D21");
        spreadsheet.getCell(22, 6).setCellFormula("(E23-D23)/D23");
        spreadsheet.getCell(23, 6).setCellFormula("(E24-D24)/D24");
    }

    public void setupGrossProfitFormulas() {
        spreadsheet.getCell(25, 3).setCellFormula("D15-D24");
        spreadsheet.getCell(25, 4).setCellFormula("E15-E24");
        spreadsheet.getCell(25, 5).setCellFormula("E26-D26");
        spreadsheet.getCell(25, 6).setCellFormula("(E26-D26)/D26");
    }

    public void setupOperatingExpensesFormulas() {
        spreadsheet.getCell(36, 3).setCellFormula("SUM(D31:D36)");
        spreadsheet.getCell(36, 4).setCellFormula("SUM(E31:E36)");

        spreadsheet.getCell(30, 5).setCellFormula("E31-D31");
        spreadsheet.getCell(31, 5).setCellFormula("E32-D32");
        spreadsheet.getCell(32, 5).setCellFormula("E33-D33");
        spreadsheet.getCell(34, 5).setCellFormula("E35-D35");
        spreadsheet.getCell(35, 5).setCellFormula("E36-D36");
        spreadsheet.getCell(36, 5).setCellFormula("SUM(F31:F36)");

        spreadsheet.getCell(30, 6).setCellFormula("(E31-D31)/D31");
        spreadsheet.getCell(31, 6).setCellFormula("(E32-D32)/D32");
        spreadsheet.getCell(32, 6).setCellFormula("(E33-D33)/D33");
        spreadsheet.getCell(34, 6).setCellFormula("(E35-D35)/D35");
        spreadsheet.getCell(35, 6).setCellFormula("(E36-D36)/D36");
        spreadsheet.getCell(36, 6).setCellFormula("(E37-D37)/D37");
    }

    public void setupGeneralExpensesFormulas() {
        spreadsheet.getCell(44, 3).setCellFormula("SUM(D41:D44)");
        spreadsheet.getCell(44, 4).setCellFormula("SUM(E41:E44)");

        spreadsheet.getCell(40, 5).setCellFormula("E41-D41");
        spreadsheet.getCell(41, 5).setCellFormula("E42-D42");
        spreadsheet.getCell(42, 5).setCellFormula("E43-D43");
        spreadsheet.getCell(43, 5).setCellFormula("E44-D44");
        spreadsheet.getCell(44, 5).setCellFormula("SUM(F41:F44)");

        spreadsheet.getCell(40, 6).setCellFormula("(E41-D41)/D41");
        spreadsheet.getCell(41, 6).setCellFormula("(E42-D42)/D42");
        spreadsheet.getCell(42, 6).setCellFormula("(E43-D43)/D43");
        spreadsheet.getCell(43, 6).setCellFormula("(E44-D44)/D44");
        spreadsheet.getCell(44, 6).setCellFormula("(E45-D45)/D45");
    }

    public void setupTotalOperatingExpenses() {
        spreadsheet.getCell(46, 3).setCellFormula("D37+D45");
        spreadsheet.getCell(46, 4).setCellFormula("E37+E45");
        spreadsheet.getCell(46, 5).setCellFormula("E47-D47");
        spreadsheet.getCell(46, 6).setCellFormula("(E47-D47)/D47");
    }

    public void setupNetIncomeBeforeTaxesFormulas() {
        spreadsheet.getCell(48, 3).setCellFormula("D26-D47");
        spreadsheet.getCell(48, 4).setCellFormula("E26-E47");
        spreadsheet.getCell(48, 5).setCellFormula("E49-D49");
        spreadsheet.getCell(48, 6).setCellFormula("(E49-D49)/D49");
    }
}
