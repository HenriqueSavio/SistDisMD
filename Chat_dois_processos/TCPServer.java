import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Servidor aguardando conexão...");

            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection cc = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Listen socket: " + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    PrintWriter out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            System.err.println("Connection: " + e.getMessage());
        }
    }

    public void run() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Cliente diz: " + message);

                // Responda ao cliente
                out.println("Servidor recebeu sua mensagem: " + message);

                // Leia uma mensagem para enviar ao cliente
                System.out.print("Digite uma resposta para o cliente: ");
                String serverResponse = userInput.readLine();
                out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o socket do cliente: " + e.getMessage());
            }
        }
    }
}
