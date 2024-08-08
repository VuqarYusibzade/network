package task2;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String fileName = reader.readLine();
            System.out.println("Server received image name: " + fileName);
            Path filePath = Path.of("server_images", fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile());
            byte[] buffer = new byte[100];
            int bytesRead;
            while ((bytesRead = clientSocket.getInputStream().read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.close();
            System.out.println("Image saved on the server: " + filePath);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
