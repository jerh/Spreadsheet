package spreadsheet.utils;

public enum Operand {
	PLUS, MINUS, MULTIPLY, DIVIDE;

	public static Operand valueOf(char c) {
		if (c == '+') {
			return PLUS;
		} else if (c == '-') {
			return MINUS;
		} else if (c == '*') {
			return MULTIPLY;
		} else if (c == '/') {
			return DIVIDE;
		} else {
			throw new RuntimeException(c+" is an invalid operand");
		}
	}
}
