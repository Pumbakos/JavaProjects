package audio.player;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, JavaLayerException {
        AudioPlayer player = new AudioPlayer();

        player.setCurrentSong();

        player.play();

//        player.setNextSong();

//        System.out.println("EVENT: " + player.getEvent());
//        System.out.println("SOURCE: " + player.getEventSource());

        System.out.println("PREV: " + player.getPreviousSong());
        System.out.println("NEXT: " + player.getNextSong());

        System.out.print(">> ");
        if (new Scanner(System.in).nextInt() == 1){
            player.previous();
            System.out.println("PREV: " + player.getPreviousSong());
            System.out.println("NEXT: " + player.getNextSong());
        }
        if (new Scanner(System.in).nextInt() == 2){
            player.next();
            System.out.println("PREV: " + player.getPreviousSong());
            System.out.println("NEXT: " + player.getNextSong());
        }
    }
}
