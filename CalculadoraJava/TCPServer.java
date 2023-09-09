import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7896);
            System.out.println("Porta aberta 7896...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Crie uma instância da classe Connection para lidar com o cliente
                new Connection(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Connection extends Thread {
    BufferedReader in;
    PrintWriter out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Conexão:" + e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                // Read input from the client (request string)
                String request = in.readLine();

                if (request == null) {
                    break; // Cliente fechou a conexão
                }

                try {
                    double result = Calculadora.handleRequest(request);
                    out.println("Result: " + result);
                } catch (IllegalArgumentException e) {
                    out.println("Error: " + e.getMessage());
                } catch (ArithmeticException e) {
                    out.println("Error: " + e.getMessage());
                }
            }

            // Feche o socket do cliente
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
