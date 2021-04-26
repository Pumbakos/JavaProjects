package audioPlayer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AudioPlayer player = new AudioPlayer();

        player.openFolder();

        player.chooseSong();

        player.play();

        System.out.print(">> ");
        if (new Scanner(System.in).nextInt() == 1){
            player.stop();
        }
    }
}
