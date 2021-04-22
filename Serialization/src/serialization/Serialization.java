package serialization;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public abstract class Serialization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static boolean serialize(Object object) {
        String fileName = generateFileName(object);

        System.out.println(fileName);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(object);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object deserialize(Object object) {
        String fileName = generateFileName(object);

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            Object deserializedObject = (Object) input.readObject();
            return deserializedObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String generateFileName(Object object) {
        return object.getClass().toGenericString().replace(' ', '.').replace('.', '_').concat(".bin");
    }
}
