package spreadsheet.cell.object.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import spreadsheet.Spreadsheet;


public class FormulaCellObjectTest {

    private Spreadsheet setup() {
        String[][] sheet = new String[2][2];
        return new Spreadsheet(sheet);
    }

    @Test
    public void valueOnlyFormula() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, "5+3");
        sheet.setValue(0, 1, "3+3/3");
        sheet.setValue(1, 0, "3+3*2+3/3");

        assertEquals(8.0, sheet.getValue(0, 0), 0.001);
        assertEquals(2.0, sheet.getValue(0, 1), 0.001);
        assertEquals(5.0, sheet.getValue(1, 0), 0.001);
    }

    @Test
    public void simpleReferenceFormula() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, "A2");
        sheet.setValue(0, 1, "5");

        assertEquals(5.0, sheet.getValue(0, 0), 0.001);
    }

    @Test
    public void referenceWithValueFormula() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, "1+A2");
        sheet.setValue(0, 1, "5");
        sheet.setValue(1, 0, "A1+A2");

        assertEquals(6.0, sheet.getValue(0, 0), 0.001);
        assertEquals(11.0, sheet.getValue(1, 0), 0.001);
    }

    @Test
    public void parseFormulaWithWhitespace() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, " 5 + 3 ");
        sheet.setValue(0, 1, " 3 + 3 / 3 ");
        sheet.setValue(1, 0, " 3 + 3 * 2 + 3 / 3");

        assertEquals(8.0, sheet.getValue(0, 0), 0.001);
        assertEquals(2.0, sheet.getValue(0, 1), 0.001);
        assertEquals(5.0, sheet.getValue(1, 0), 0.001);
    }

    @Test(expected=RuntimeException.class)
    public void parseFormulaWithInvalidFormula() {
        Spreadsheet sheet = setup();
        sheet.setValue(0, 0, " 5++3 ");
        sheet.getValue(0, 0);
    }
}
