package audio.player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 *
 * @author www.codejava.net
 */
public class SoundClip implements LineListener {
    private boolean playCompleted; //this flag indicates whether the playback completes or not.
    private Clip audioClip;

    /**
     * Play a given audio file.
     *
     * @param audioFilePath Path of the audio file.
     * TODO: need to implement GC to delete obsolete songs
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


    void play() {
        prepareClip("D:\\Steam\\steamapps\\common\\Counter-Strike Global Offensive\\csgo\\sound\\kodua\\fortnite_emotes\\athena_emote_founders_music.wav");
        audioClip.start();
        while(!playCompleted){
            sleep(audioClip.getMicrosecondLength() + 500);
        }
        audioClip.stop();
        System.gc();
    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}