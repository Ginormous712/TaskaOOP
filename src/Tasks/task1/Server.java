package Tasks.task1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            // Deserialize object from client
            Object receivedObject = objectInputStream.readObject();

            if (receivedObject instanceof SerializableObject) {
                SerializableObject deserializedObject = (SerializableObject) receivedObject;
                System.out.println("Object received from client: " + deserializedObject.toString());
            } else {
                System.out.println("Unexpected object type received from client.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

