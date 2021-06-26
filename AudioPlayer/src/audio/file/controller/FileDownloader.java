package audio.file.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileDownloader {
    //    private static final String DIRECTORY = "src/audio/file/controller/";
    private static final String DIRECTORY = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\downloaded\\";
    private static final int MAX_FILE_SIZE = 50332648;
    //    static String requestUrl = RemotePath.SERVER.getName() + RemotePath.FILE.getName() + RemotePath.DOWNLOAD.getName() + "/";
    static String requestUrl = "http://localhost:8080/file/download/";
    private String link;
    private File out;

    public FileDownloader(String link, File out) {
        this.link = link;
        this.out = out;
    }

    public FileDownloader() {

    }

    public static String getDirectory() {
        return DIRECTORY;
    }

    public static String getRequestUrl() {
        return requestUrl;
    }

    public static void setRequestUrl(String requestUrl) {
        FileDownloader.requestUrl = requestUrl;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void download(String filename) {
        try {
            setOut(new File(DIRECTORY + filename));
            String request = requestUrl + filename;

            URL url = new URL(request);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 49152);

            byte[] buffer = new byte[49152];
            int read = 0;

            while ((read - in.read(buffer, 0, 49152)) >= 0) {
                bos.write(buffer, 0, read);
            }

            bos.close();
            in.close();

            System.out.println("COMPLETED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadWav(String filename) throws IOException {
        setOut(new File(DIRECTORY + filename));
        String request = requestUrl + filename;

        URL url = new URL(request);
        InputStream in = url.openStream();
        ReadableByteChannel urlChannel = Channels.newChannel(in);

        FileChannel channel = new FileOutputStream(getOut()).getChannel();
        channel.transferFrom(urlChannel, 0, MAX_FILE_SIZE);
    }

    public List<String> getList() {
        String listFromServer = getListFromServer();
        List<String> list = convertToList(Objects.requireNonNull(listFromServer));
        return list;
    }

    private String getListFromServer() {
        try {
            StringBuilder result = new StringBuilder();

            URL url = new URL(RemotePath.SERVER + RemotePath.FILE + RemotePath.DOWNLOAD + RemotePath.LIST);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");

            try (var reader = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            } catch (Exception e) {
                return null;
            }
            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> convertToList(String listAsString) {
        List<String> list = Arrays.asList(listAsString.replace("[", "")
                .replace("]", "").replace("\"", "").split(","));
        return list;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public File getOut() {
        return out;
    }

    public void setOut(File out) {
        this.out = out;
    }
}
