package week1;

public class EnglishCalculator {

	public static double calculate(String s) {
		String[] sp = s.split(" ");
		double num1 = Double.parseDouble(sp[0]);
		double num2 = (sp.length == 3) ? Double.parseDouble(sp[2]) : Double.parseDouble(sp[3]);
		switch (sp[1]) {
		case "times":
			return num1 * num2;
		case "plus":
			return num1 + num2;
		case "minus":
			return num1 - num2;
		case "divided":
			if (num2 != 0)
				return (num1 / num2);
			else
				throw new IllegalArgumentException("cannot divide by zero");
		default:
			throw new IllegalArgumentException("not a valid operation");
		}
	}
}