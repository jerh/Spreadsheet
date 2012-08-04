package spreadsheet.cell.object.impl;

import spreadsheet.CellObject;

public class DoubleCellObject implements CellObject {
    private double value;

    public DoubleCellObject(String value) {
        this.value = Double.parseDouble(value.trim());
    }

    public double getValue() {
        return value;
    }
}

