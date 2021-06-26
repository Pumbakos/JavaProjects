import audio.controler.ClipQueue;
import audio.player.SoundClip;

public class Main {
    public static void main(String[] args) {
        SoundClip clip = SoundClip.getInstance();
        ClipQueue queue = new ClipQueue();


        clip.setProperties(queue, clip);
        clip.subscribe(queue);

        clip.menu();
        clip.cmd();

        System.out.println(clip.getLastCommand());
    }
}
