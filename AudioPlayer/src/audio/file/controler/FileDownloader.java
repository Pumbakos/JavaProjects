package audio.file.controler;

import audio.RemotePath;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {
    private static final String DIRECTORY = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\downloaded\\";
    static String requestUrl = RemotePath.SERVER.getName() + RemotePath.FILE.getName() + RemotePath.DOWNLOAD.getName() + "\\";
    private String link;
    private File out;

    public FileDownloader(String link, File out) {
        this.link = link;
        this.out = out;
    }

    public void download(String filename) {
        try {
            URL url = new URL(requestUrl.concat(filename));
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 4096);

            byte[] buffer = new byte[4096];
            int read = 0;

            while ((read - in.read(buffer, 0, 1024)) >= 0) {
                bos.write(buffer, 0, read);
            }

            bos.close();
            in.close();

            System.out.println("COMPLETED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
