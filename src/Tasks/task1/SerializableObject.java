package Tasks.task1;

import java.io.Serializable;

public class SerializableObject implements Serializable {
    private String message;

    public SerializableObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SerializableObject{" +
                "message='" + message + '\'' +
                '}';
    }
}

