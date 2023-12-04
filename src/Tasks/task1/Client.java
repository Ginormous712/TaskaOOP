package Tasks.task1;

import java.io.*;
import java.net.Socket;

public class Client {
    public void sendMessageToServer(SerializableObject serializableObject) {
        try (Socket socket = new Socket("localhost", 12345)) {
            // Create and serialize an object
            System.out.println("Object to send: " + serializableObject.toString());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(serializableObject);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

