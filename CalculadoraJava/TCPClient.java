import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 7896);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                System.out.println("Operação (add,sub,div,mult): ");
                String operation = scanner.nextLine();

                if (operation.equalsIgnoreCase("exit")) {
                    break;
                }
            
                System.out.println("Numero 1: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.println("Numero 2: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                String request = operation + "," + num1 + "," + num2;

                out.println(request);
                
                String result = in.readLine();
                System.out.println(result);
            }

            socket.close();
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
