package audio.player;

import audio.controler.Controller;

public class Main {
    public static void main(String[] args){
        SoundClip player = new SoundClip();
        Controller controller = new Controller(player);
        controller.menu();
        controller.cmd();
    }
}
