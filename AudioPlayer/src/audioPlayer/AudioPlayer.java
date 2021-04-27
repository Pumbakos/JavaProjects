package audioPlayer;

import audioPlayer.opener.Opener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class AudioPlayer{
    private final Opener opener = new Opener("D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\");
    private String defaultFolder;
    private static int ID;

    private volatile AdvancedPlayer player = null;
    private volatile PlaybackEvent event = null;
    private volatile Thread backgroundPlayback;
    private volatile int frameStoppedAt;

    private String currentSong;

    public AudioPlayer(){
        defaultFolder = opener.getDefaultFolder();
        ID = generateID();
    }

    /**
     * Prepares app for background activity
     * Waits until the start of the thread
     *
     * @param path : full path to song
     */
    private synchronized void setBackgroundPlayback(String path) {
//        opener.openFolder();
        backgroundPlayback = new Thread(() -> {
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
                player = new AdvancedPlayer(buffer);
                event = new PlaybackEvent(player, ID, 0);
                event.setSource(player);
                event.setId(ID);
                event.setFrame(0);
                player.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * starts set thread
     * @throws IllegalThreadStateException if the thread was already started
     */
    public synchronized void play() throws IllegalThreadStateException {
        setBackgroundPlayback(defaultFolder + currentSong);

        backgroundPlayback.start();
    }

    public synchronized void pause() {
    }

    public void resume() {
    }

    /**
     * stops the currently playing song
     */
    public synchronized void stop() throws SecurityException, NullPointerException {
        this.player.close();
        this.backgroundPlayback.interrupt();
    }

    public void next() {
    }

    public void previous() {
    }

    private int generateID() {
        return new SecureRandom().nextInt(new Random().nextInt(Integer.MAX_VALUE));
    }

    public String getDefaultFolder() {
        return opener.getDefaultFolder();
    }

    public void setDefaultFolder(String path) {
        opener.setDefaultFolder(path);
    }

    public String getCurrentSong() {
        return opener.getCurrentSong();
    }

    public void setCurrentSong() {
        currentSong = opener.setCurrentSong();
    }
}