package Test;

import Tasks.task1.Client;
import Tasks.task1.Server;
import Tasks.task1.SerializableObject;
import org.junit.jupiter.api.Test;

class ClientServerTest {

    @Test
    void testClientServerCommunication() {
        //Start the server in a separate thread
        new Thread(() -> Server.main(null)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Launch the client
        Client client = new Client();
        SerializableObject message = new SerializableObject("Hello from client!");
        client.sendMessageToServer(message);

        // Delay to complete server execution
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
