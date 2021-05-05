import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int PORT = 5000;
    public static final String IP = "127.0.0.1"; //* the actual address where the server is located

    BufferedReader bufferedReader;
    String name;

    public void openClientServer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        try {
            Socket socket = new Socket(IP, PORT);
            System.out.println("Connected to " + socket);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread thread = new Thread(new Receiver());
            thread.start();

            while (true) {
                System.out.print(">> ");
                String s = scanner.nextLine();
                if (!s.equalsIgnoreCase("q")) {
                    printWriter.println(name + ": " + s);
                    printWriter.flush();
                } else {
                    printWriter.println(name + " disconnected");
                    printWriter.flush();
                    printWriter.close();
                    scanner.close();
                    socket.close();
                }
            }
        } catch (Exception e) {
            //* do nothing
        }
    }

    class Receiver implements Runnable {
        @Override
        public void run() {
            String message = "";

            try {
                while ((message = bufferedReader.readLine()) != null) {
                    String subMessage[] = message.split(":");
                    if (!subMessage[0].equals(name)) {
                        System.out.println(message);
                        System.out.print(">> ");
                    }
                }
            } catch (Exception e) {
                System.out.println("Disconnected");
            }
        }
    }

}
