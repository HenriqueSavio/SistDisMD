import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Uso: TCPClient <hostname>");
            return;
        }

        Socket socket = null;
        try {
            String hostname = args[0];
            int serverPort = 7896;
            socket = new Socket(hostname, serverPort);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while (true) {
                System.out.print("Digite uma mensagem: ");
                message = userInput.readLine();
                out.println(message);

                String response = in.readLine();
                System.out.println("Servidor diz: " + response);
            }
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o socket: " + e.getMessage());
            }
        }
    }
}
