package audioPlayer;

import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AudioPlayer {
    private final String MUSIC_FOLDER = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\";
    private final Scanner scanner = new Scanner(System.in);
    private AdvancedPlayer player;
    private Thread backgroundPlay;
    private List<File> musicList = new ArrayList();
    private String currentSong;

    /**
     * Opens default music folder - my local actually :)
     */
    public void openFolder() {
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

    /**
     * Displays the songs in the current folder and waits for the user's keyboard input
     * @deprecated return full path to song
     */
    public void chooseSong() {
        int usersSongChoice;
//        String pathToSong;
        listSongs();

        System.out.print("\nChoose song(1-" + musicList.size() + "): ");
        usersSongChoice = scanner.nextInt();
        currentSong = musicList.get(usersSongChoice - 1).getName();

//        pathToSong = this.MUSIC_FOLDER + currentSong;
//        return pathToSong;
    }

    /**
     * Displays songs saved at @see musicList
     */
    private void listSongs() {
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i).getName().substring(0, musicList.get(i).getName().length() -4).replace('_',' '));
        }
    }

    /**
     * Prepares app for background activity
     * Waits until the start of the thread
     * @param path : full path to song
     */
    private void prepareSong(String path){
        backgroundPlay = new Thread(() -> {
            try {
                BufferedInputStream buffer = new BufferedInputStream(
                        new FileInputStream(path));
                player = new AdvancedPlayer(buffer);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public synchronized void play() {
        prepareSong(MUSIC_FOLDER+currentSong);
        try {
            backgroundPlay.start();
        }catch (IllegalThreadStateException e){
            e.printStackTrace();
        }
    }

    public void pause() {
    }

    public void resume() {
    }

    /**
     * stops background current playing song
     */
    public void stop() {
        try {
            backgroundPlay.interrupt();
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }

    public void next() {
    }

    public void previous() {
    }

    /**
     * lists all files within given folder - excludes sub-folders
     * @param path: full path to folder where stored songs
     */
    private void listFilesForFolder(File path) {
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
//                listFilesForFolder(file);
                continue;
            } else {
                musicList.add(new File(file.getName()));
            }
        }
    }

    public String getCurrentSong(){
        return currentSong;
    }

    /**
     * @return full path to chosen song
     */
    private String getPathToSong(){
        return MUSIC_FOLDER.concat(currentSong);
    }
}