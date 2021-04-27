package audioPlayer;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, JavaLayerException {
        AudioPlayer player = new AudioPlayer();

        player.setCurrentSong();

        player.play();


        System.out.print(">> ");
        if (new Scanner(System.in).nextInt() == 1){
//            player.pause();
            player.stop();
        }
    }
}
