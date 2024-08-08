package task1;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            String serverAddress = "localhost";
            int serverPort = 8080;
            Socket clientSocket = new Socket(serverAddress, serverPort);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendRequest(writer, "/sayMeHello:Vugar");
            sendRequest(writer, "/listCommands");
            sendRequest(writer, "/unknownRequest");
            receiveResponse(reader);
            receiveResponse(reader);
            receiveResponse(reader);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void sendRequest(PrintWriter writer, String request) {
        writer.println(request);
        System.out.println("task1.Client sent request: " + request);
    }
    private static void receiveResponse(BufferedReader reader) throws IOException {
        String response = reader.readLine();
        System.out.println("task1.Client received response: " + response);
    }
}
