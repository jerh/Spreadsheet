package spreadsheet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spreadsheet.utils.Operand;

/**
 * Spreadsheet
 * Each cell has the identifier RCol where R and Col is the identifier of a cell
 * where R = {A, B, ..., Z} and Col is a number >= 0
 * @author jerh
 *
 */

public class Spreadsheet {

    private SpreadsheetCell[][] cells;
    private final int num_rows;
    private final int num_columns;

    public Spreadsheet(String[][] spreadsheetCells) {
        num_rows = spreadsheetCells.length;
        num_columns = spreadsheetCells[0].length;

        cells = new SpreadsheetCell[num_rows][num_columns];
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_columns; col++) {
                cells[row][col] = new SpreadsheetCell(this, row, col);
            }
        }

        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_columns; col++) {
                cells[row][col].setValue(spreadsheetCells[row][col]);
            }
        }
    }

    public SpreadsheetCell getCell(int row, int column) {
        return cells[row][column];
    }

    public double getValue(int row, int column) {
        if (row > cells.length || column > cells[0].length)
            throw new RuntimeException("Cell <"+row+","+column+"> is out of bound");

        return cells[row][column].getValue();
    }

    public void setValue(int row, int column, String value) {
        if (row >= num_rows || column >= num_columns) {
            throw new RuntimeException("Cell <"+row+","+column+"> is out of bound");
        }

        cells[row][column].setValue(value);
    }

}
