package spreadsheet.cell.object.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import spreadsheet.Spreadsheet;

public class DoubleCellObjectTest {

    private Spreadsheet setup() {
        String[][] sheet = new String[2][2];
        return new Spreadsheet(sheet);
    }

    @Test
    public void parseDouble() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, "3.0");
        sheet.setValue(0, 1, " 3.0 ");
        sheet.setValue(1, 0, "3.");
        sheet.setValue(1, 1, "3");

        assertEquals(3.0, sheet.getValue(0, 0), 0.);
        assertEquals(3.0, sheet.getValue(0, 1), 0.);
        assertEquals(3.0, sheet.getValue(1, 0), 0.);
        assertEquals(3.0, sheet.getValue(1, 1), 0.);
    }
}
