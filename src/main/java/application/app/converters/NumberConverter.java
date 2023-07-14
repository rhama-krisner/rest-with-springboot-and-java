package application.app.converters;

public class NumberConverter {
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null) {
            throw new IllegalArgumentException("A string de número é nula.");
        }

        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }

        throw new IllegalArgumentException("A string de número não é um valor numérico válido.");
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }
        return strNumber.matches("-?\\d+(\\.\\d+)?");
    }
}
