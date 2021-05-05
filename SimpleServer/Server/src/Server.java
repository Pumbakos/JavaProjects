import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    private List clientList;
    PrintWriter printWriter;

    public void openServer() {
        clientList = new ArrayList(); //* users are being stored in this list
        try {
            System.out.println("Server starts");
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                Socket socket = serverSocket.accept(); //* accept all incomes

                System.out.println("Listening " + serverSocket);
                printWriter = new PrintWriter(socket.getOutputStream());
                clientList.add(printWriter);

                Thread thread = new Thread(new ServerClient(socket));
                thread.start();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    class ServerClient implements Runnable {
        Socket socket;
        BufferedReader bufferedReader;

        public ServerClient(Socket clientSocket) {
            try {
                System.out.println("Connected.");
                socket = clientSocket;
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //* reads sent data

            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            PrintWriter printWriter;

            try {
                while ((message = bufferedReader.readLine()) != null) {
                    //shows received message
                    System.out.println(message);

                    for (Object obj : clientList) {
                        printWriter = (PrintWriter) obj;
                        printWriter.println(message);
                        printWriter.flush();
                    }

                }
            } catch (Exception e) {
                //* do nothing
            }
        }
    }
}
