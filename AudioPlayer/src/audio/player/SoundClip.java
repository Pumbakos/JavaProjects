package audio.player;

import audio.file.controler.FileController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class SoundClip implements LineListener {
    private static Thread mainClip;
    private static volatile boolean playbackCompleted = false; //this flag indicates whether the playback completes or not.
    private final int AMENDMENT = 100;
    private FileController fileController = new FileController("D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\");
    private volatile Clip audioClip;
    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;
    private volatile int frameStoppedAt;

    public SoundClip() {
    }

    /**
     * Play a given audio file.
     *
     * @param audioFilePath Path of the audio file.
     *                      TODO: need to implement GC to delete obsolete songs
     */
    private void prepareClip(String audioFilePath) {
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


    public void play() {
        if (mainClip == null || !mainClip.isAlive()) {
            prepareClip(getDefaultFolder() + currentSong);
            mainClip.start();
        }
    }

    /**
     * @throws NullPointerException when did not choose song
     */
    public void stop() throws NullPointerException {
        if (!playbackCompleted) {
            audioClip.stop();
        } else {
            System.out.println("Playback already completed.");
        }
    }

    /**
     * @throws NullPointerException when did not choose song
     */
    public void pause() throws NullPointerException {
        if (!playbackCompleted) {
            frameStoppedAt = audioClip.getFramePosition();
            stop();
        } else System.out.println("Already paused.");
    }

    public void resume() {
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

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            playbackCompleted = false;
        } else if (type == LineEvent.Type.STOP) {
            playbackCompleted = true;
        }
    }

    public void next() {
        if(audioClip == null){
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

    public void previous() {
        if(audioClip == null){
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
    public void setCurrentSong() {
        currentSong = fileController.setCurrentSong();
        setPreviousSong();
        setNextSong();
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}