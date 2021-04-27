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
    private int ID;
    private int index;

    private volatile AdvancedPlayer player = null;
    private volatile PlaybackEvent event = null;
    private volatile Thread backgroundPlayback;
    private volatile int frameStoppedAt;

    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;

    public AudioPlayer(){
        defaultFolder = opener.getDefaultFolder();
        ID = generateID();
    }

    private synchronized void setPlayer(String path) {
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
            player = new AdvancedPlayer(buffer);
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    private void setEvent(AdvancedPlayer player) {
        event = new PlaybackEvent(player, ID, 0);
        event.setSource(player);
        event.setId(ID);
        event.setFrame(0);
    }

    /**
     * Prepares app for background activity
     * Waits until the start of the thread
     *
     * @param path : full path to song
     */
    private synchronized void setBackgroundPlayback(String path) {
        setPlayer(path);
        setEvent(player);
        backgroundPlayback = new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
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
        System.out.println(event.getFrame());
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

    public String getPreviousSong() {
        return previousSong;
    }

    private void setPreviousSong() {
//        this.previousSong = opener.setPreviousSong(opener.getIndex());
        nextSong = opener.getPreviousSong();
    }

    public String getNextSong() {
        return nextSong;
    }

    private void setNextSong() {
//        this.nextSong = opener.setNextSong(opener.getIndex());
        previousSong = opener.getNextSong();
    }

    public String getCurrentSong() {
        return currentSong;
    }

    /**
     *  @usageOf: setPreviousSong(), setNextSong() : we want to set next and previous song everytime we set new one
     */
    public void setCurrentSong() {
        currentSong = opener.setCurrentSong();
        setPreviousSong();
        setNextSong();
    }
}