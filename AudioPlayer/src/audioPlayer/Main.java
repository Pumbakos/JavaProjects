package audioPlayer;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.listFilesForFolder(new File("D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\src\\audioPlayer"));
        player.listFiles();
    }
}
