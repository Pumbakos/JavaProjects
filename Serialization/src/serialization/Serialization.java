package serialization;

import com.sun.istack.internal.NotNull;
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

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(object);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean serialize(Object object, @NotNull String fileName) {
        if(!fileName.endsWith(".bin")){
            fileName += ".bin";
        }

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
    public static Object deserialize(@NotNull String fileName) {
        if(!fileName.endsWith(".bin")){
            throw new IllegalArgumentException("Filename must ends with 'bin' extension");
        }

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
