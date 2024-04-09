package fr.hetic;

class Calculator {
    public static void main(String[] args) {
        try {
            Double number1 = Double.parseDouble(args[0]);
            Double number2 = Double.parseDouble(args[1]);
            String operator = args[2];

            Double result = calculate(number1, number2, operator);

            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Arguments should be <number1> <number2> <operator>");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Double calculate(Double number1, Double number2, String operator) {
        return switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            default -> throw new IllegalArgumentException("Operator accepted: +, -, *");
        };
    }
}