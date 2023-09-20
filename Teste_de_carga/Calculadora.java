public class Calculadora {
    public static double handleRequest(String request) {
        String[] parts = request.split(",");
        
        if (parts.length != 3) {
            throw new IllegalArgumentException("so pode 3 parametros exemplo:+,10,5");
        }
        
        String operation = parts[0];
        double num1 = Double.parseDouble(parts[1]);
        double num2 = Double.parseDouble(parts[2]);
        
        switch (operation) {
            case "add":
                return num1 + num2;
            case "sub":
                return num1 - num2;
            case "mult":
                return num1 * num2;
            case "div":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("nao existe divisão por 0");
                }
            default:
                throw new IllegalArgumentException("error");
        }
    }
}
