package audio.player;
import audio.player.opener.Opener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class AudioPlayer extends PlaybackListener{
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

//    private AudioPlayer(InputStream inputStream) throws JavaLayerException {
//        super(inputStream);
//    }

    public AudioPlayer() throws JavaLayerException{
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

    private void setListener(AdvancedPlayer player){
        player.setPlayBackListener(new PlaybackListener(){
            @Override
            public void playbackStarted(PlaybackEvent event) {
            }

            @Override
            public void playbackFinished(PlaybackEvent event) {
                frameStoppedAt = event.getFrame();
            }
        });
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
        setListener(player);

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
        //this getter should not be removed
        //without it the event is not set
        getEventSource();

        backgroundPlayback.start();
    }

    //FIXME: event need to return frame it stopped at
    public synchronized void pause() {
        System.out.println("FRAME: " + frameStoppedAt);
        System.out.println("ID: " + event.getId());
        System.out.println("SOURCE: " + event.getSource());
    }

    //FIXME: related to pause()
    public void resume() {
    }

    /**
     * stops the currently playing song
     */
    public synchronized void stop() throws SecurityException, NullPointerException {
//        this.player.close();
        playbackFinished(event);
        player.stop();
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
        previousSong = opener.getPreviousSong();
    }

    public String getNextSong() {
        return nextSong;
    }

    private void setNextSong() {
//        this.nextSong = opener.setNextSong(opener.getIndex());
        nextSong = opener.getNextSong();
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

    private synchronized PlaybackEvent getEvent() {
        return event;
    }
    private synchronized AdvancedPlayer getEventSource() {
        return event.getSource();
    }
}