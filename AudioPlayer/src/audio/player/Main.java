package audio.player;

import audio.controler.Controller;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JavaLayerException {
        AudioPlayer player = new AudioPlayer();
        Controller controller = new Controller(player);

        controller.menu();
        controller.simpleCmd();
    }
}
