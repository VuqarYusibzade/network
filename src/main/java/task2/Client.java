package task2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8081);
            String fileName = "C:\\Users\\yusib\\IdeaProjects\\HomeWorkServerClientSocket\\src\\main\\java\\task2\\image.jpg";
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(fileName);
            FileInputStream fileInputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[100];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                clientSocket.getOutputStream().write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            System.out.println("Image sent to server: " + fileName);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
