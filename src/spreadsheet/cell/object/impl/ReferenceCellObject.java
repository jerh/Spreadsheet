package spreadsheet.cell.object.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spreadsheet.CellObject;
import spreadsheet.Spreadsheet;
import spreadsheet.SpreadsheetCell;

public class ReferenceCellObject implements CellObject {
    private SpreadsheetCell spreadsheetCell;

    public ReferenceCellObject(Spreadsheet sheet, String identifier) {
        this.spreadsheetCell =  parseCellIdentifier(sheet, identifier);
    }

    public double getValue() {
        return spreadsheetCell.getValue();
    }

    private SpreadsheetCell parseCellIdentifier(Spreadsheet sheet, String identifier) {
        Pattern identifer_pattern = Pattern.compile("([A-Z])(\\d+)");
        Matcher m = identifer_pattern.matcher(identifier.trim());
        if (m.find()) {
            int row = (int) m.group(1).charAt(0)-((int)'A');
            int column = Integer.parseInt(m.group(2))-1;
            return sheet.getCell(row, column);
        } else {
            return null;
        }
    }
}
