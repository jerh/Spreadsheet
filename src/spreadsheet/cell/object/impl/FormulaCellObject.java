package spreadsheet.cell.object.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spreadsheet.CellObject;
import spreadsheet.SpreadsheetCell;
import spreadsheet.utils.Operand;
import spreadsheet.utils.Util;

public class FormulaCellObject implements CellObject {
    private SpreadsheetCell inSpreadsheetCell;
    List<CellObject> values;
    List<Operand> operands;

    public FormulaCellObject(SpreadsheetCell cell, String value) {
        inSpreadsheetCell = cell;
        values = new ArrayList<CellObject>();
        operands = new ArrayList<Operand>();
        parseFormula(value.trim());
    }


    public double getValue() {
        double value = values.get(0).getValue();

        for (int i=1, j=0; i<values.size() && j<operands.size(); i++, j++) {
            double curValue = values.get(i).getValue();
            switch (operands.get(j)) {
            case PLUS:
                value += curValue;
                break;
            case MINUS:
                value -= curValue;
            case MULTIPLY:
                value *= curValue;
                break;
            case DIVIDE:
                value /= curValue;
                break;
            default:
                throw new RuntimeException("Error getting cell value");
            }
        }

        return value;
    }

    private void parseFormula(String value) {
        String formula = value.replaceAll("\\s", "");

        if (formula.matches("\\w+[\\+\\-\\*\\/]{2,}\\w+")) {
            throw new RuntimeException(value+" is not a valid formula");
        }

        String[] variables = formula.split("[\\+\\-\\*\\/]");
        for (String variable : variables) {
            if (Util.isNumber(variable)) {
                values.add(new DoubleCellObject(variable));
            } else {
                values.add(new ReferenceCellObject(inSpreadsheetCell.getSpreadsheet(),
                                                 variable));
            }
        }

        Pattern operands_pattern = Pattern.compile("([\\+\\-\\*\\/]+)");
        Matcher m = operands_pattern.matcher(value);
        while (m.find()) {
            String operator = m.group(1);
            operands.add(Operand.valueOf(operator.charAt(0)));
        }
    }
}
