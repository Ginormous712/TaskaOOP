package Test;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

import Tasks.task1.SerializableObject;

class SerializableObjectTest {

    @Test
    void testSerializationDeserialization() {
        // Arrange
        String originalMessage = "Test message";
        SerializableObject originalObject = new SerializableObject(originalMessage);
        String filename = "testSerialization.ser";

        // Act: Serialize the object
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(originalObject);
        } catch (IOException e) {
            fail("Serialization failed: " + e.getMessage());
        }

        // Act: Deserialize the object
        SerializableObject deserializedObject = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedObject = (SerializableObject) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            fail("Deserialization failed: " + e.getMessage());
        }

        // Assert: Check if the deserialized object is equal to the original
        assertNotNull(deserializedObject);
        assertEquals(originalMessage, deserializedObject.getMessage());

        // Clean up: Delete the temporary file
        File file = new File(filename);
        if (!file.delete()) {
            System.out.println("Failed to delete the test file.");
        }
    }
}

