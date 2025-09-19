package io.jmix.uisamples.view.flowui.components.spreadsheet.advanced;

import com.vaadin.flow.component.spreadsheet.Spreadsheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("uisamples_SpreadsheetStyler")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SpreadsheetStyler {

    private final Spreadsheet spreadsheet;
    private final Workbook workbook;

    public SpreadsheetStyler(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
        this.workbook = spreadsheet.getWorkbook();
    }

    public StyleContext createStyle() {
        return new StyleContext();
    }

    public CellStyle createCellStyle(StyleContext styleContext) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setLocked(true);

        if (styleContext.getClone() != null) {
            cellStyle.cloneStyleFrom(styleContext.getClone());
        }

        if (styleContext.getFontContext() != null) {
            Font font = createFont(styleContext.getFontContext());
            cellStyle.setFont(font);
        } else if (styleContext.getClone() == null) {
            // setup default
            Font font = createFont()
                    .withFontHeightInPoints((short) 16)
                    .toFont();
            cellStyle.setFont(font);
        }

        if (styleContext.getAllBorderStyle() != null) {
            cellStyle.setBorderTop(styleContext.getAllBorderStyle());
            cellStyle.setBorderRight(styleContext.getAllBorderStyle());
            cellStyle.setBorderLeft(styleContext.getAllBorderStyle());
            cellStyle.setBorderBottom(styleContext.getAllBorderStyle());
        }

        if (styleContext.getVerticalAlignment() != null) {
            cellStyle.setVerticalAlignment(styleContext.getVerticalAlignment());
        }

        if (styleContext.getAlignment() != null) {
            cellStyle.setAlignment(styleContext.getAlignment());
        }

        if (styleContext.getFormat() != null) {
            cellStyle.setDataFormat(styleContext.getFormat());
        }

        if (styleContext.getBackgroundColor() != null) {
            cellStyle.setFillBackgroundColor(styleContext.getBackgroundColor());
        }

        return cellStyle;
    }

    public FontContext createFont() {
        return new FontContext();
    }

    public Font createFont(FontContext fontContext) {
        XSSFFont font = (XSSFFont) workbook.createFont();

        if (fontContext.getFontHeightInPoints() != null) {
            font.setFontHeightInPoints(fontContext.getFontHeightInPoints());
        }

        if (fontContext.getColor() != null) {
            font.setColor(fontContext.getColor());
        }

        if (fontContext.getBold() != null) {
            font.setBold(fontContext.getBold());
        }

        return font;
    }

    public Cell addBorderToCellAbove(Cell targetCell) {
        Cell cell = spreadsheet.createCell(targetCell.getRowIndex() - 1, targetCell.getColumnIndex(), null);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);

        return cell;
    }

    public Cell addBorderToLeftCell(Cell targetCell) {
        Cell cell = spreadsheet.createCell(targetCell.getRowIndex(), targetCell.getColumnIndex() - 1, null);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);

        return cell;
    }

    public class StyleContext {

        private FontContext fontContext;
        private BorderStyle allBorderStyle;
        private HorizontalAlignment alignment;
        private VerticalAlignment verticalAlignment;
        private CellStyle clone;
        private Short format;
        private XSSFColor backgroundColor;

        private StyleContext() {
        }

        FontContext getFontContext() {
            return fontContext;
        }

        public StyleContext withFontContext(FontContext fontContext) {
            this.fontContext = fontContext;
            return this;
        }

        BorderStyle getAllBorderStyle() {
            return allBorderStyle;
        }

        public StyleContext withAllBorderStyle(BorderStyle allBorderStyle) {
            this.allBorderStyle = allBorderStyle;
            return this;
        }

        HorizontalAlignment getAlignment() {
            return alignment;
        }

        public StyleContext withAlignment(HorizontalAlignment alignment) {
            this.alignment = alignment;
            return this;
        }

        VerticalAlignment getVerticalAlignment() {
            return verticalAlignment;
        }

        public StyleContext withVerticalAlignment(VerticalAlignment verticalAlignment) {
            this.verticalAlignment = verticalAlignment;
            return this;
        }

        CellStyle getClone() {
            return clone;
        }

        public StyleContext asClone(CellStyle clone) {
            this.clone = clone;
            return this;
        }

        Short getFormat() {
            return format;
        }

        public StyleContext withDataFormat(Short format) {
            this.format = format;
            return this;
        }

        XSSFColor getBackgroundColor() {
            return backgroundColor;
        }

        public StyleContext withBackgroundColor(XSSFColor backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public CellStyle toStyle() {
            return createCellStyle(this);
        }
    }

    public class FontContext {

        private Short fontHeightInPoints;
        private XSSFColor color;
        private Boolean bold;

        private FontContext() {
        }

        XSSFColor getColor() {
            return color;
        }

        public FontContext withColor(XSSFColor color) {
            this.color = color;
            return this;
        }

        Short getFontHeightInPoints() {
            return fontHeightInPoints;
        }

        public FontContext withFontHeightInPoints(Short fontHeightInPoints) {
            this.fontHeightInPoints = fontHeightInPoints;
            return this;
        }

        Boolean getBold() {
            return bold;
        }

        public FontContext withBold(Boolean bold) {
            this.bold = bold;
            return this;
        }

        public Font toFont() {
            return createFont(this);
        }
    }
}
