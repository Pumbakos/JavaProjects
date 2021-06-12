package audio.player;

import audio.controler.Controller;
import audio.controler.ClipQueue;

public class Main {
    public static void main(String[] args) {
        SoundClip clip = SoundClip.getInstance();
        ClipQueue queue = new ClipQueue(clip);
        Controller controller = new Controller(clip, queue);

        clip.subscribe(queue);

        controller.menu();
        controller.cmd();

        clip.getLastCommand();
    }
}
