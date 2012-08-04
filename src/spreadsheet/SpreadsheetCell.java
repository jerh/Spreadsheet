package spreadsheet;

import spreadsheet.cell.object.impl.DoubleCellObject;
import spreadsheet.cell.object.impl.FormulaCellObject;
import spreadsheet.utils.Util;

public class SpreadsheetCell {
    private final Spreadsheet spreadsheet;
    private final int row;
    private final int column;
    private CellObject obj;

    public SpreadsheetCell(Spreadsheet spreadsheet, int row, int column) {
        this.spreadsheet = spreadsheet;
        this.row = row;
        this.column = column;
    }

    public SpreadsheetCell(Spreadsheet spreadsheet, int row, int column, String value) {
        this(spreadsheet, row, column);
        setValue(value);
    }

    public double getValue() {
        if (obj == null) {
            return 0.;
        } else {
          return obj.getValue();
        }
    }

    public void setValue(String value) {
        if (value == null || value.trim() == "") {
            obj = null;
        } else if( Util.isNumber(value)) {
            obj = new DoubleCellObject(value);
        } else {
            obj = new FormulaCellObject(this, value);
        }
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
