import file.opener.Reader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader o = new Reader();

        o.getId(15);
        o.printId();
    }
}
