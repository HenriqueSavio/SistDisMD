import java.io.*;
import java.net.*;
public class TCPServer {
	    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7896);
            
            System.out.println("porta aberta 7896...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String request = in.readLine();

                try {
                    double result = Calculadora.handleRequest(request);
                    out.println("Resultado: " + result);                        
                } catch(IllegalArgumentException e){
                    out.println("Error: " + e.getMessage());
                } catch (ArithmeticException e){
                    out.println("Error: " + e.getMessage());
                }
                
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
