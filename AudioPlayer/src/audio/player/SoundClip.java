package audio.player;

import audio.controler.Controller;
import audio.controler.Observer;
import audio.controler.Subscriber;
import audio.file.controler.FileController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SoundClip extends Controller implements LineListener, Observer {
    private final int AMENDMENT = 100;
    private Thread mainClip;
    private FileController fileController = new FileController("D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\");
    private List<Subscriber> subscribers = new ArrayList<>();
    private volatile boolean playbackCompleted = false; //this flag indicates whether the playback completes or not
    private volatile Clip audioClip;
    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;
    private volatile int frameStoppedAt;


    private SoundClip() {
    }

    public static SoundClip getInstance() {
        return Wrapper.instance;
    }

    /**
     * Play a given audio file.
     *
     * @param audioFilePath Path of the audio file.
     *                      TODO: need to implement GC to delete obsolete played songs
     */
    private void prepareClip(String audioFilePath) {
        if (audioFilePath == null) {
            throw new IllegalArgumentException();
        }

        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);

            mainClip = new Thread(() -> {
                audioClip.start();
                playbackCompleted = false;

                while (!playbackCompleted) {
                    sleep(1000);
                }
                audioClip.stop();
            });

        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        }
    }

    public final void play() {
        if (mainClip == null || !mainClip.isAlive()) {
            prepareClip(getDefaultFolder() + currentSong);
            mainClip.start();
        }
    }

    /**
     * @throws NullPointerException when did not choose song
     */
    public final void stop() throws NullPointerException {
        if (!playbackCompleted) {
            audioClip.stop();
        } else {
            System.out.println("Playback already completed.");
        }
    }

    /**
     * @throws NullPointerException when did not choose song
     */
    public final void pause() throws NullPointerException {
        if (!playbackCompleted) {
            frameStoppedAt = audioClip.getFramePosition();
            stop();
        } else System.out.println("Already paused.");
    }

    public final void resume() {
        if (playbackCompleted && audioClip != null) {
            audioClip.setFramePosition(frameStoppedAt - AMENDMENT);
            audioClip.start();
            playbackCompleted = false;

            while (!playbackCompleted) {
                sleep(1000);
            }
            audioClip.stop();
        }
    }

    public final void next() {
        if (audioClip == null) {
            prepareClip(getDefaultFolder() + currentSong);
        }

        if (!playbackCompleted) {
            stop();
        }

        currentSong = fileController.setCurrentSong(getIndex() + 1);

        setNextSong();
        nextSong = getNextSong();

        setPreviousSong();
        previousSong = getPreviousSong();
        play();
    }

    public final void previous() {
        if (audioClip == null) {
            prepareClip(getDefaultFolder() + currentSong);
        }

        if (!playbackCompleted) {
            stop();
        }
        currentSong = fileController.setCurrentSong(getIndex() - 1);

        setNextSong();
        nextSong = getNextSong();

        setPreviousSong();
        previousSong = getPreviousSong();
        play();
    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public final void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            playbackCompleted = false;
        } else if (type == LineEvent.Type.STOP) {
            playbackCompleted = true;
            mainClip.interrupt();
            audioClip.close();
            notifySubscribers();
        }
    }

    @Override
    public boolean subscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null.");
        }
        return subscribers.add(subscriber);
    }

    @Override
    public boolean unsubscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null.");
        }

        if (subscribers.isEmpty()) {
            throw new NullPointerException("Subscribers list is empty.");
        }

        return subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public synchronized void removeAllSubscribers() {
        Iterator<Subscriber> it = subscribers.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    private int generateID() {
        return new SecureRandom().nextInt(new Random().nextInt(Integer.MAX_VALUE));
    }

    public String getDefaultFolder() {
        return fileController.getDefaultFolder();
    }

    public void setDefaultFolder(String path) {
        fileController.setDefaultFolder(path);
    }

    public String getPreviousSong() {
        return previousSong;
    }

    private void setPreviousSong() {
        previousSong = fileController.getPreviousSong();
    }

    public String getNextSong() {
        return nextSong;
    }

    private void setNextSong() {
        nextSong = fileController.getNextSong();
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public int getIndex() {
        return fileController.getIndex();
    }

    public void list() {
        fileController.listSongs();
    }

    /**
     * @usageOf: setPreviousSong(), setNextSong() : we want to set next and previous song everytime we set new current one
     */
    public final void setCurrentSong() {
        currentSong = fileController.setCurrentSong();
        setPreviousSong();
        setNextSong();
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public String getLastCommand() {
        return super.getLastCommand();
    }

    private static class Wrapper {
        static SoundClip instance = new SoundClip();
    }
}