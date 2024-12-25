import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client_socket {

    private static void sendMessage(BufferedWriter out, String message) throws IOException {
        System.out.println("Action: " + message);
        out.write(message);
        out.newLine();
//        out.flush();
    }

    public static void main(String[] args) {
        System.out.println("Client started...");

        try (Socket so = new Socket("127.0.0.1", 9001);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()))) {

            // send messages
            sendMessage(out, "Kick my ass");
            sendMessage(out, "isPrime 999");
            sendMessage(out, "isPrime 17");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
