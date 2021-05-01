package audio.player;

import audio.opener.Opener;
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
    private int ID;

    private volatile long threadID;
    private volatile String threadName;

    private volatile AdvancedPlayer player = null;
    private volatile PlaybackEvent event = null;
    private volatile Thread backgroundPlayback;
//            = new Thread(() -> {
//        try {
//            player.play();
//        } catch (JavaLayerException e) {
//            e.printStackTrace();
//        }
//    });

    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;
    private volatile int frameStoppedAt;
    private volatile boolean isRunning;

    public AudioPlayer() throws JavaLayerException{
        ID = generateID();
    }

    private void setPlayer(String path) {
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
     * *Prepares app for background activity
     * *Waits until the start of the thread
     * @param path : full path to song
     * @throws NullPointerException if you would turn on another song without stopping the previous one
     */
    private void setBackgroundPlayback(String path) throws NullPointerException{
        setPlayer(path);
        setEvent(player);
        setListener(player);

        backgroundPlayback = new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                //!TODO: need here to log exception and report it via ex: mail
            }catch(NullPointerException ex){
                System.out.println("If you want to play next/previous song please enter 'next'/'previous' command.");
            }
        });
    }

    /**
     * *starts set thread
     * @throws IllegalThreadStateException if the thread was already started
     * @throws SecurityException if interrupt wasn't successful
     * @see Thread
     */
    public void play() throws IllegalThreadStateException, SecurityException {
        setBackgroundPlayback(getDefaultFolder() + currentSong);
        //!this getter should not be removed
        //*without it the event is not set
        getEventSource();

        System.out.println("playing '" + currentSong.substring(0, currentSong.length() -4).replace('_', ' ') + "' ...");
        //?use reflection
        if(isRunning){
            this.player.close();
            this.backgroundPlayback.interrupt();
        }

        isRunning = true;
        backgroundPlayback.start();
        System.out.println("next");
    }

    /**
     * *stops the currently playing song
     */
    public void stop() throws SecurityException, NullPointerException {
        if(player != null){
            playbackFinished(event);
            this.player.close();
            this.backgroundPlayback.interrupt();
            isRunning = false;
        }
    }

    //FIXME: event need to return frame it stopped at
    public void pause() {
        /*
        System.out.println("FRAME: " + frameStoppedAt);
        System.out.println("ID: " + event.getId());
        System.out.println("SOURCE: " + event.getSource());
        */
    }

    //FIXME: related to pause()
    public void resume() {
    }

    public void next() {
        if(isRunning){
            stop();
            backgroundPlayback.interrupt();
        }
        currentSong = opener.setCurrentSong(getIndex() +1);

        setNextSong();
        nextSong = getNextSong();

        setPreviousSong();
        previousSong = getPreviousSong();
        play();
    }

    public void previous() {
        if(isRunning){
            stop();
            backgroundPlayback.interrupt();
        }
        currentSong =  opener.setCurrentSong(getIndex() -1);

        setNextSong();
        nextSong = getNextSong();

        setPreviousSong();
        previousSong = getPreviousSong();
        play();
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
        previousSong = opener.getPreviousSong();
    }

    public String getNextSong() {
        return nextSong;
    }

    private void setNextSong() {
        nextSong = opener.getNextSong();
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public int getIndex(){
        return opener.getIndex();
    }

    public void list(){
        opener.listSongs();
    }

    /**
     *  @usageOf: setPreviousSong(), setNextSong() : we want to set next and previous song everytime we set new current one
     */
    public void setCurrentSong() {
        currentSong = opener.setCurrentSong();
        setPreviousSong();
        setNextSong();
    }

    private PlaybackEvent getEvent() {
        return event;
    }
    private AdvancedPlayer getEventSource() {
        return event.getSource();
    }
}