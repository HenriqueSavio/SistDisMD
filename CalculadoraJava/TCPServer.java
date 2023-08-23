import java.io.*;
import java.net.*;
public class TCPServer {
	    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7896);
            
            System.out.println("Server listening on port 7896...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String request = in.readLine();
                String[] parts = request.split(",");
                
                if (parts.length != 3) {
                    out.println("so pode 3 parametros exemplo:+,10,5");
                    clientSocket.close();
                    continue;
                }

                String operation = parts[0];
                double num1 = Double.parseDouble(parts[1]);
                double num2 = Double.parseDouble(parts[2]);
                
                double result = 0.0;
                
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            out.println("não existe divisão por 0");
                            clientSocket.close();
                            continue;
                        }
                        break;
                    default:
                        out.println("error");
                        clientSocket.close();
                        continue;
                }
                
                out.println("Resultado: " + result);
                
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
