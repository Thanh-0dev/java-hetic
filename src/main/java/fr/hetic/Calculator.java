package fr.hetic;

class Calculator {
    public static void main(String[] args) {
        try {
            Integer number1 = Integer.parseInt(args[0]);
            Integer number2 = Integer.parseInt(args[1]);
            String operator = args[2];

            Integer result = calculate(number1, number2, operator);

            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Arguments should be <number1> <number2> <operator>");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Integer calculate(Integer number1, Integer number2, String operator) {
        return switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            default -> throw new IllegalArgumentException("Operator accepted: +, -, *");
        };
    }
}