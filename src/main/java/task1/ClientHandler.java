package task1;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = reader.readLine();
            System.out.println("task1.Server received request: " + request);

            String response = processRequest(request);
            System.out.println("task1.Server sent response: " + response);
            writer.println(response);

            log(request, response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        if (request.startsWith("/sayMeHello:")) {
            String name = request.substring("/sayMeHello:".length());
            return "Hi, " + name;
        } else if (request.equals("/listCommands")) {
            return "add\nget\nset";
        } else {
            return "Invalid request";
        }
    }

    private void log(String request, String response) {
        try (PrintWriter logWriter = new PrintWriter(new FileWriter("server_log.txt", true))) {
            logWriter.println("Request: " + request);
            logWriter.println("Response: " + response);
            logWriter.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
