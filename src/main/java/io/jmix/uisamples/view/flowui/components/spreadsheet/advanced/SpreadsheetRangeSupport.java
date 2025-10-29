package io.jmix.uisamples.view.flowui.components.spreadsheet.advanced;

import com.vaadin.flow.component.spreadsheet.Spreadsheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Component("uisamples_SpreadsheetRangeSupport")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SpreadsheetRangeSupport {

    private final Spreadsheet spreadsheet;

    public SpreadsheetRangeSupport(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public void createRange(int startRow, int endRow, int startCol, int endCol,
                            Map<Pair<Integer, Integer>, Object> valuesMap) {
        walkRange(startRow, endRow, startCol, endCol,
                (r, c) -> spreadsheet.createCell(r, c, valuesMap.get(Pair.of(r, c)))
        );
    }

    public void applyToRange(int startRow, int endRow, int startCol, int endCol,
                             Consumer<Cell> visitor) {
        walkRange(startRow, endRow, startCol, endCol,
                (r, c) -> visitor.accept(spreadsheet.getCell(r, c)));
    }

    private void walkRange(int startRow, int endRow, int startCol, int endCol, BiConsumer<Integer, Integer> visitor) {
        for (int r = startRow; r <= endRow; ++r) {
            for (int c = startCol; c <= endCol; ++c) {
                visitor.accept(r, c);
            }
        }
    }
}
