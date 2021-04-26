package audioPlayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AudioPlayer {
    private final String MUSIC_FOLDER = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\";
    private final Scanner scanner = new Scanner(System.in);
    private volatile AdvancedPlayer player;
    private volatile PlaybackEvent event;
    private volatile PlaybackListener listener;
    private volatile Thread backgroundPlay;
    private List<File> musicList = new ArrayList();
    private String currentSong;
    private int ID;

    /**
     * Opens default music folder - my local actually :)
     */
    public void openFolder() throws IOException, FileNotFoundException {
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
     *
     * @return full path to song -> @deprecated
     */
    public void chooseSong() {
        int usersSongChoice;

        listSongs();
        System.out.print("\nChoose song(1-" + musicList.size() + "): ");
        usersSongChoice = scanner.nextInt();
        currentSong = musicList.get(usersSongChoice - 1).getName();
    }

    /**
     * Displays songs saved at @see musicList
     */
    private void listSongs() {
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i).getName().substring(0, musicList.get(i).getName().length() - 4).replace('_', ' '));
        }
    }

    /**
     * Prepares app for background activity
     * Waits until the start of the thread
     *
     * @param path : full path to song
     */
    private synchronized void prepareSong(String path) {
        backgroundPlay = new Thread(() -> {
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
                player = new AdvancedPlayer(buffer);
                event = new PlaybackEvent(player, ID, 0);
                player.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });
    }

    private void generateID() {
        this.ID = new SecureRandom().nextInt(new Random().nextInt());
    }


    public synchronized void play() throws IllegalThreadStateException {
        prepareSong(MUSIC_FOLDER + currentSong);

        backgroundPlay.start();

    }

    public void pause() {
    }

    public void resume() {
    }

    /**
     * stops background current playing song
     */
    public synchronized void stop() throws SecurityException, NullPointerException {
        this.player.close();
        this.backgroundPlay.interrupt();
    }

    public void next() {
    }

    public void previous() {
    }

    /**
     * lists all files within given folder - excludes sub-folders
     *
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

    public String getCurrentSong() {
        return currentSong;
    }

    /**
     * @return full path to chosen song
     */
    private String getPathToSong() {
        return MUSIC_FOLDER.concat(currentSong);
    }
}