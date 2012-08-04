package spreadsheet.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

    @Test
    public void test() {
        assertTrue(Util.isNumber("3"));
        assertTrue(Util.isNumber("3."));
        assertTrue(Util.isNumber("3.0"));

        assertTrue(Util.isNumber(" 3. "));

        assertFalse(Util.isNumber("A3"));
    }

}
