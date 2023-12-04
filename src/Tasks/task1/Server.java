import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Tasks.task1.*;

public class ClientServerTest {
    private static final int PORT = 12345;
    private static ServerSocket serverSocket;

    @BeforeAll
    public static void setupServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @AfterAll
    public static void closeServer() throws IOException {
        serverSocket.close();
    }

    @Test
    public void testClientServerInteraction() {
        // Run the server in a separate thread
        Thread serverThread = new Thread(() -> {
            try (Socket clientSocket = new Socket("localhost", PORT)) {
                // Create and serialize object
                SerializableObject serializableObject = new SerializableObject("Test message");

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(serializableObject);
                objectOutputStream.flush();

                // Send the serialized object to the server
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        // Wait for a while to ensure the server has enough time to process the client's request
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the server received the object correctly
        assertEquals("Test message", getServerOutput());
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

    private static String getServerOutput() {
        try {
            InputStream inputStream = serverSocket.accept().getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            return new String(buffer, 0, bytesRead);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
