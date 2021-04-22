package serialization;

public class Main extends Serialization{
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Dawid");

        Serialization.serialize(sb);

        Object deserialized = Serialization.deserialize(sb);

        System.out.println(deserialized);
    }
}
