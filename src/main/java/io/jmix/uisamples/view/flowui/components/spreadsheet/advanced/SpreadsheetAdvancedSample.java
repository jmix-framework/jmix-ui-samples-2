package io.jmix.uisamples.view.flowui.components.spreadsheet.advanced;

import com.vaadin.flow.component.spreadsheet.Spreadsheet;
import io.jmix.core.DateTimeTransformations;
import io.jmix.flowui.view.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.awt.Color;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.entry;

@ViewController("spreadsheet-advanced")
@ViewDescriptor("spreadsheet-advanced.xml")
public class SpreadsheetAdvancedSample extends StandardView {

    private static final XSSFColor HEADER_BRIGHT_BLUE = new XSSFColor(new Color(40, 114, 192), null);
    private static final XSSFColor BRIGHT_YELLOW = new XSSFColor(new Color(255, 243, 204), null);
    private static final XSSFColor BACKGROUND_BRIGHT_BLUE = new XSSFColor(new Color(180, 198, 231), null);
    private static final XSSFColor BACKGROUND_BLUE = new XSSFColor(new Color(0, 112, 192), null);
    private static final XSSFColor WHITE = new XSSFColor(new Color(255, 255, 255), null);

    private static final XSSFColor GREEN = new XSSFColor(new Color(0, 176, 80), null);
    private static final XSSFColor RED = new XSSFColor(new Color(192, 0, 0), null);

    @ViewComponent
    private Spreadsheet spreadsheet;

    @Autowired
    private DateTimeTransformations dateTimeTransformations;

    private SpreadsheetStyler spreadsheetStyler;
    private SpreadsheetFormulaSupport spreadsheetFormulaSupport;
    private SpreadsheetRangeSupport spreadsheetRangeSupport;

    private final Set<Cell> cellsToUpdate = new HashSet<>();
    private Workbook workbook;

    @Subscribe
    public void onInit(InitEvent event) {
        spreadsheetStyler = getApplicationContext().getBean(SpreadsheetStyler.class, spreadsheet);
        spreadsheetFormulaSupport = getApplicationContext().getBean(SpreadsheetFormulaSupport.class, spreadsheet);
        spreadsheetRangeSupport = getApplicationContext().getBean(SpreadsheetRangeSupport.class, spreadsheet);

        spreadsheet.setActiveSheetProtected(UUID.randomUUID().toString());
    }

    @Subscribe
    public void onReady(ReadyEvent event) {
        workbook = spreadsheet.getWorkbook();

        setupCellWidth();

        initHeader();
        initHeaderGrid();
        initHint();
        initRevenueGrid();
        initCostOfSalesGrid();
        initCrossProfitRow();
        initOperatingExpensesGrid();
        initGeneralExpensesGrid();
        initTotalOperatingExpensesRow();
        initNetIncomeBeforeTaxesRow();

        setupConditionalFormating();

        spreadsheet.refreshCells(cellsToUpdate);
        cellsToUpdate.clear();
    }

    private void setupCellWidth() {
        for (int i = 1; i < 7; ++i) {
            spreadsheet.setColumnWidth(i, 200);
        }

        spreadsheet.setColumnWidth(0, 30);
    }

    private void setupConditionalFormating() {
        SheetConditionalFormatting sheetCf = spreadsheet.getActiveSheet().getSheetConditionalFormatting();
        ConditionalFormattingRule lessRule = sheetCf.createConditionalFormattingRule(ComparisonOperator.LE, "0");
        ConditionalFormattingRule greaterRule = sheetCf.createConditionalFormattingRule(ComparisonOperator.GT, "0");

        lessRule.createFontFormatting().setFontColor(RED);
        greaterRule.createFontFormatting().setFontColor(GREEN);

        CellRangeAddress[] regions = {
                CellRangeAddress.valueOf("F12:G15"),
                CellRangeAddress.valueOf("F19:G24"),
                CellRangeAddress.valueOf("F26:G26"),
                CellRangeAddress.valueOf("F31:G33"),
                CellRangeAddress.valueOf("F35:G37"),
                CellRangeAddress.valueOf("F41:G45"),
                CellRangeAddress.valueOf("F47:G47"),
                CellRangeAddress.valueOf("F49:G49"),
        };

        sheetCf.addConditionalFormatting(regions, lessRule, greaterRule);
    }

    private void initHeader() {
        spreadsheet.addMergedRegion("B2:E2");
        Cell cell = spreadsheet.createCell(1, 1, "COMPANY PROFIT AND LOSS PLANNING FORM");

        CellStyle style = spreadsheetStyler.createStyle()
                .withFontContext(
                        spreadsheetStyler.createFont()
                                .withFontHeightInPoints((short) 26)
                                .withColor(HEADER_BRIGHT_BLUE)
                )
                .toStyle();

        cell.setCellStyle(style);

        spreadsheet.setRowHeight(1, 30);
        cellsToUpdate.add(cell);
    }

    private void initHeaderGrid() {
        CellStyle captionCellStyle = spreadsheetStyler.createStyle()
                .withAllBorderStyle(BorderStyle.THIN)
                .withVerticalAlignment(VerticalAlignment.BOTTOM)
                .toStyle();

        CellStyle valueCellStyle = spreadsheetStyler.createStyle()
                .asClone(captionCellStyle)
                .withAlignment(HorizontalAlignment.RIGHT)
                .withDataFormat(workbook.createDataFormat().getFormat("dd.MM.yyyy"))
                .toStyle();

        spreadsheetRangeSupport.createRange(3, 6, 1, 2, Map.of(
                Pair.of(3, 1), "Company:",
                Pair.of(3, 2), "HyperDev",
                Pair.of(4, 1), "Manager:",
                Pair.of(4, 2), "John Doe",
                Pair.of(5, 1), "Date prepared:",
                Pair.of(5, 2), dateTimeTransformations.transformToType(LocalDate.of(2025, 1, 31), Date.class, null),
                Pair.of(6, 1), "Planned Year:",
                Pair.of(6, 2), "2025"
        ));

        spreadsheetRangeSupport.applyToRange(3, 6, 1, 2, cellsToUpdate::add);
        spreadsheetRangeSupport.applyToRange(3, 3, 1, 2,
                cell -> {
                    Cell aboveCell = spreadsheetStyler.addBorderToCellAbove(cell);
                    cellsToUpdate.add(aboveCell);
                });
        spreadsheetRangeSupport.applyToRange(3, 6, 1, 1,
                cell -> {
                    Cell cellToLeft = spreadsheetStyler.addBorderToLeftCell(cell);
                    cellsToUpdate.add(cellToLeft);

                    cell.setCellStyle(captionCellStyle);
                });
        spreadsheetRangeSupport.applyToRange(3, 6, 2, 2,
                cell -> cell.setCellStyle(valueCellStyle));
    }

    private void initHint() {
        Cell hintCell = spreadsheet.createCell(3, 6, "Fill in the cells");

        CellStyle cellStyle = spreadsheetStyler.createStyle()
                .withAlignment(HorizontalAlignment.CENTER)
                .withBackgroundColor(BRIGHT_YELLOW)
                .toStyle();

        hintCell.setCellStyle(cellStyle);

        cellsToUpdate.add(hintCell);
    }

    private void initRevenueGrid() {
        initRevenueGridData();
        applyTableStyles(9, 14, 1, 6, false);
        spreadsheetFormulaSupport.setupRevenueFormulas();

        spreadsheet.getCell(11, 3).setCellValue(3500000);
        spreadsheet.getCell(12, 3).setCellValue(300000);
        spreadsheet.getCell(13, 3).setCellValue(100000);
    }

    private void initRevenueGridData() {
        Map<Pair<Integer, Integer>, Object> map = Map.ofEntries(
                entry(Pair.of(9, 1), "REVENUE"),
                entry(Pair.of(9, 3), "Actual 2024, USD"),
                entry(Pair.of(9, 4), "Planned 2025, USD"),
                entry(Pair.of(9, 5), "Y2Y, USD"),
                entry(Pair.of(9, 6), "Y2Y, %"),
                entry(Pair.of(10, 1), "Gross Sales"),
                entry(Pair.of(11, 2), "Development"),
                entry(Pair.of(12, 2), "Consultancy"),
                entry(Pair.of(13, 2), "Training"),
                entry(Pair.of(14, 1), "NET SALES")
        );
        spreadsheetRangeSupport.createRange(9, 14, 1, 6, map);
    }

    private void initCostOfSalesGrid() {
        initCostOfSalesGridData();
        applyTableStyles(16, 23, 1, 6, false);
        spreadsheetFormulaSupport.setupCostOfSalesFormulas();

        spreadsheet.getCell(18, 3).setCellValue(150000);
        spreadsheet.getCell(19, 3).setCellValue(250000);
        spreadsheet.getCell(20, 3).setCellValue(50000);
        spreadsheet.getCell(22, 3).setCellValue(150000);
    }

    private void initCostOfSalesGridData() {
        Map<Pair<Integer, Integer>, Object> map = Map.ofEntries(
                entry(Pair.of(16, 1), "COST OF SALES"),
                entry(Pair.of(16, 3), "Actual 2024, USD"),
                entry(Pair.of(16, 4), "Planned 2025, USD"),
                entry(Pair.of(16, 5), "Y2Y, USD"),
                entry(Pair.of(16, 6), "Y2Y, %"),
                entry(Pair.of(17, 1), "Marketing"),
                entry(Pair.of(18, 2), "Digital Ads"),
                entry(Pair.of(19, 2), "Events"),
                entry(Pair.of(20, 2), "Content Marketing"),
                entry(Pair.of(21, 1), "Sales"),
                entry(Pair.of(22, 2), "Comissions"),
                entry(Pair.of(23, 1), "TOTAL COST OF SALES")
        );
        spreadsheetRangeSupport.createRange(16, 23, 1, 6, map);
    }

    private void initCrossProfitRow() {
        initCrossProfitData();
        applyTotalRowStyles(25, 1, 6);
        spreadsheetFormulaSupport.setupGrossProfitFormulas();
    }

    private void initCrossProfitData() {
        spreadsheetRangeSupport.createRange(25, 25, 1, 6,
                Map.of(Pair.of(25, 1), "GROSS PROFIT"));
    }

    private void initOperatingExpensesGrid() {
        initOperatingExpensesGridData();
        applyTableStyles(28, 36, 1, 6, false);
        spreadsheetFormulaSupport.setupOperatingExpensesFormulas();

        spreadsheet.getCell(30, 3).setCellValue(350000);
        spreadsheet.getCell(31, 3).setCellValue(29000);
        spreadsheet.getCell(32, 3).setCellValue(50000);
        spreadsheet.getCell(34, 3).setCellValue(1500000);
        spreadsheet.getCell(35, 3).setCellValue(50000);
    }

    private void initOperatingExpensesGridData() {
        Map<Pair<Integer, Integer>, Object> map = Map.ofEntries(
                entry(Pair.of(28, 1), "OPERATING EXPENSES"),
                entry(Pair.of(28, 3), "Actual 2024, USD"),
                entry(Pair.of(28, 4), "Planned 2025, USD"),
                entry(Pair.of(28, 5), "Y2Y, USD"),
                entry(Pair.of(28, 6), "Y2Y, %"),
                entry(Pair.of(29, 1), "Marketing and Sales Team"),
                entry(Pair.of(30, 2), "Salaries and Wages"),
                entry(Pair.of(31, 2), "Travel Expenses"),
                entry(Pair.of(32, 2), "Other"),
                entry(Pair.of(33, 1), "Development Team"),
                entry(Pair.of(34, 2), "Salaries and Wages"),
                entry(Pair.of(35, 2), "Other"),
                entry(Pair.of(36, 1), "TOTAL SELLING AND DEV EXPENSES")
        );
        spreadsheetRangeSupport.createRange(28, 36, 1, 6, map);
    }

    private void initGeneralExpensesGrid() {
        initGeneralExpensesGridData();
        applyTableStyles(39, 44, 1, 6, true);
        spreadsheetFormulaSupport.setupGeneralExpensesFormulas();

        spreadsheet.getCell(40, 3).setCellValue(200000);
        spreadsheet.getCell(41, 3).setCellValue(700000);
        spreadsheet.getCell(42, 3).setCellValue(20000);
        spreadsheet.getCell(43, 3).setCellValue(300000);
    }

    private void initGeneralExpensesGridData() {
        Map<Pair<Integer, Integer>, Object> map = Map.ofEntries(
                entry(Pair.of(39, 1), "GENERAL EXPENSES"),
                entry(Pair.of(39, 3), "Actual 2024, USD"),
                entry(Pair.of(39, 4), "Planned 2025, USD"),
                entry(Pair.of(39, 5), "Y2Y, USD"),
                entry(Pair.of(39, 6), "Y2Y, %"),
                entry(Pair.of(40, 2), "Salaries and Wages"),
                entry(Pair.of(41, 2), "Payroll Taxes"),
                entry(Pair.of(42, 2), "Insurance"),
                entry(Pair.of(43, 2), "Infrastructure"),
                entry(Pair.of(44, 1), "TOTAL GENERAL EXPENSES")
        );
        spreadsheetRangeSupport.createRange(39, 44, 1, 6, map);
    }

    private void initTotalOperatingExpensesRow() {
        initTotalOperatingExpensesRowData();
        applyTotalRowStyles(46, 1, 6);
        spreadsheetFormulaSupport.setupTotalOperatingExpenses();
    }

    private void initTotalOperatingExpensesRowData() {
        spreadsheetRangeSupport.createRange(46, 46, 1, 6,
                Map.of(Pair.of(46, 1), "TOTAL OPERATING EXPENSES"));
    }

    private void initNetIncomeBeforeTaxesRow() {
        initNetIncomeBeforeTaxesRowData();
        applyTotalRowStyles(48, 1, 6);
        spreadsheetFormulaSupport.setupNetIncomeBeforeTaxesFormulas();
    }

    private void initNetIncomeBeforeTaxesRowData() {
        spreadsheetRangeSupport.createRange(48, 48, 1, 6,
                Map.of(Pair.of(48, 1), "NEW INCOME BEFORE TAXES"));
    }

    private void applyTableStyles(int startRow, int endRow, int startCol, int endCol, boolean includeFirstContentRow) {
        initGridHeaderFooterStyles(startRow, endRow, startCol, endCol);
        initGridContentStyles(startRow, endRow, startCol, endCol, includeFirstContentRow);
        initLastColumn(startRow, endRow, endCol, includeFirstContentRow);
    }

    private void initLastColumn(int startRow, int endRow, int endCol, boolean includeFirstContentRow) {
        CellStyle cellStyle = spreadsheetStyler.createStyle()
                .withDataFormat(workbook.createDataFormat().getFormat("0.00%"))
                .toStyle();
        int targetStartRow = startRow + (includeFirstContentRow ? 1 : 2);

        spreadsheetRangeSupport.applyToRange(targetStartRow, endRow, endCol, endCol,
                cell -> {
                    cell.setCellStyle(cellStyle);
                    cellsToUpdate.add(cell);
                }
        );

        CellStyle footerCellStyle = spreadsheetStyler.createStyle()
                .withDataFormat(workbook.createDataFormat().getFormat("0.00%"))
                .withFontContext(spreadsheetStyler.createFont()
                        .withColor(WHITE)
                        .withFontHeightInPoints((short) 16))
                .withBackgroundColor(BACKGROUND_BRIGHT_BLUE)
                .toStyle();

        Cell lastCell = spreadsheet.getCell(endRow, endCol);
        lastCell.setCellStyle(footerCellStyle);

        cellsToUpdate.add(lastCell);
    }

    private void initGridHeaderFooterStyles(int startRow, int endRow, int startCol, int endCol) {
        CellStyle cellStyle = spreadsheetStyler.createStyle()
                .withFontContext(spreadsheetStyler.createFont()
                        .withColor(WHITE)
                        .withFontHeightInPoints((short) 16))
                .withBackgroundColor(BACKGROUND_BRIGHT_BLUE)
                .toStyle();

        Stream.of(
                spreadsheet.getCell(startRow, startCol),
                spreadsheet.getCell(startRow, startCol + 1),
                spreadsheet.getCell(endRow, startCol),
                spreadsheet.getCell(endRow, startCol + 1)
        ).forEach(cell -> {
            cell.setCellStyle(cellStyle);
            cellsToUpdate.add(cell);
        });

        CellStyle headersStyle = spreadsheetStyler.createStyle()
                .asClone(cellStyle)
                .withAlignment(HorizontalAlignment.CENTER)
                .toStyle();

        spreadsheetRangeSupport.applyToRange(startRow, startRow, startCol + 2, endCol,
                cell -> {
                    cell.setCellStyle(headersStyle);
                    cellsToUpdate.add(cell);
                });

        CellStyle footerStyle = spreadsheetStyler.createStyle()
                .asClone(headersStyle)
                .withAlignment(HorizontalAlignment.RIGHT)
                .withDataFormat(workbook.createDataFormat().getFormat("$# ##0.00"))
                .toStyle();

        spreadsheetRangeSupport.applyToRange(endRow, endRow, startCol + 2, endCol - 1,
                cell -> {
                    cell.setCellStyle(footerStyle);
                    cellsToUpdate.add(cell);
                });
    }

    private void applyTotalRowStyles(int row, int startCol, int endCol) {
        CellStyle captionStyle = spreadsheetStyler.createStyle()
                .withFontContext(spreadsheetStyler.createFont()
                        .withColor(WHITE)
                        .withBold(true)
                        .withFontHeightInPoints((short) 16))
                .withBackgroundColor(BACKGROUND_BLUE)
                .toStyle();

        Stream.of(
                spreadsheet.getCell(row, startCol),
                spreadsheet.getCell(row, startCol + 1)
        ).forEach(cell -> {
            cell.setCellStyle(captionStyle);
            cellsToUpdate.add(cell);
        });

        CellStyle valuesStyle = spreadsheetStyler.createStyle()
                .asClone(captionStyle)
                .withAlignment(HorizontalAlignment.RIGHT)
                .withDataFormat(workbook.createDataFormat().getFormat("$# ##0.00"))
                .toStyle();

        spreadsheetRangeSupport.applyToRange(row, row, startCol + 2, endCol - 1,
                cell -> {
                    cell.setCellStyle(valuesStyle);
                    cellsToUpdate.add(cell);
                });

        CellStyle footerCellStyle = spreadsheetStyler.createStyle()
                .withDataFormat(workbook.createDataFormat().getFormat("0.00%"))
                .withFontContext(spreadsheetStyler.createFont()
                        .withColor(WHITE)
                        .withBold(true)
                        .withFontHeightInPoints((short) 16))
                .withBackgroundColor(BACKGROUND_BLUE)
                .toStyle();

        Cell lastCell = spreadsheet.getCell(row, endCol);
        lastCell.setCellStyle(footerCellStyle);

        cellsToUpdate.add(lastCell);
    }

    private void initGridContentStyles(int startRow, int endRow, int startCol, int endCol, boolean includeFirstContentRow) {
        CellStyle cellStyle = spreadsheetStyler.createStyle().toStyle();
        int targetStartRow = startRow + (includeFirstContentRow ? 1 : 2);

        spreadsheetRangeSupport.applyToRange(startRow + 1, endRow - 1, startCol, startCol,
                cell -> {
                    cell.setCellStyle(cellStyle);
                    cellsToUpdate.add(cell);
                });

        spreadsheetRangeSupport.applyToRange(targetStartRow, endRow - 1, startCol + 1, startCol + 1,
                cell -> {
                    cell.setCellStyle(cellStyle);
                    cellsToUpdate.add(cell);
                });

        CellStyle currancyCellStyle = spreadsheetStyler.createStyle()
                .asClone(cellStyle)
                .withDataFormat(workbook.createDataFormat().getFormat("$# ##0.00"))
                .toStyle();

        spreadsheetRangeSupport.applyToRange(startRow + 1, endRow - 1, startCol + 2, endCol - 1,
                cell -> {
                    cell.setCellStyle(currancyCellStyle);
                    cellsToUpdate.add(cell);
                });

        CellStyle fillInStyle = spreadsheetStyler.createStyle()
                .asClone(currancyCellStyle)
                .withBackgroundColor(BRIGHT_YELLOW)
                .toStyle();
        fillInStyle.setLocked(false);

        spreadsheetRangeSupport.applyToRange(targetStartRow, endRow - 1, startCol + 3, startCol + 3,
                cell -> cell.setCellStyle(fillInStyle));
    }
}
