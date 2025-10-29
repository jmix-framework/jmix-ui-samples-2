В это примере показана возможность кастомизации `Workbook` для компонента `Spreadsheet`.

К таблице применены различные стили, формулы, форматы и условные форматирования.
Чтобы посмотреть компонент в действии необходимо заполнить поля в жёлтых ячейках. Отклонения значений будут посчитаны
автоматически.

Для реализации примера использованы следующие сервисы:

- [SpreadsheetStyler]({currentPath}?tab=SpreadsheetStyler.java) для создания шрифтов и стилей `Workbook`
- [SpreadsheetRangeSupport]({currentPath}?tab=SpreadsheetRangeSupport.java) для работы с диапозоном значений
- [SpreadsheetFormulaSupport]({currentPath}?tab=SpreadsheetFormulaSupport.java) для задания формул
