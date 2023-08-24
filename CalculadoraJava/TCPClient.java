import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7896);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String request = "mult,10,5";

            out.println(request);
            
            String result = in.readLine();
            System.out.println(result);
            
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
