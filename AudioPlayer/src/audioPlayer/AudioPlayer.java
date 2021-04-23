package audioPlayer;

import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AudioPlayer {
    private List<File> musicList = new ArrayList();
    private final String MUSIC_FOLDER = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\";
    private final Scanner scanner = new Scanner(System.in);
    private String currentSong;


    public void openFolder(){
        File path = new File(MUSIC_FOLDER);
        System.out.println(path);
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
//                listFilesForFolder(file);
                continue;
            } else {
                musicList.add(new File(file.getName()));
            }
        }
    }

    public String chooseSong(){
        int song;
        String pathToSong;

        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i+1) + ". " + musicList.get(i));
        }

        System.out.print("\nChoose song(1-"+ musicList.size() +"): ");
        song = scanner.nextInt();
        currentSong = musicList.get(song -1).getName();

        pathToSong = this.MUSIC_FOLDER + currentSong;

        return pathToSong;
    }

    public void play(String path){
        try {
            BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(path));
            AdvancedPlayer player = new AdvancedPlayer(buffer);
            player.play();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause(){}

    public void resume(){}

    public void stop(){}

    public void next(){}

    public void previous(){}

    private void listFilesForFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
//                listFilesForFolder(file);
                continue;
            } else {
                musicList.add(new File(file.getName()));
            }
        }
    }

    public void listFiles(){
        for (File file : this.musicList) {
            System.out.println(file);
        }
    }
}