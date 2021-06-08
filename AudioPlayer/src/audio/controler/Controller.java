package audio.controler;

import audio.player.AudioPlayer;
import audio.player.SoundClip;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private AudioPlayer player;
    private SoundClip clip;
    private String command;

    public Controller(AudioPlayer player){
        this.player = player;
    }
    public Controller(SoundClip clip){
        this.clip = clip;
    }

    public void menu() {
        System.out.println("\t\t\tMENU");
        System.out.println("Enter 'play' to start playing.");
        System.out.println("Enter 'stop' to stop playing.");
        System.out.println("Enter 'pause' to pause.");
        System.out.println("Enter 'resume' to resume.");
        System.out.println("Enter 'next' for next song.");
        System.out.println("Enter 'previous' for previous song.");
        System.out.println("Enter 'list' for listing all songs.");
        System.out.println("Enter 'help' for help");
//        System.out.println("Enter 'queue' to see current queue");

        System.out.println("\nHere are songs from your folder, choose one:");
        clip.list();
        clip.setCurrentSong();
    }

    public void help() {
        System.out.println("Incorrect value.");
        System.out.println("Enter 'help' for help");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void simpleCmd() {
        do{
            System.out.print(">> ");
            command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "", " ","  " -> {}

                case "play" -> {
                    player.play();
                }
                case "stop", "exit" -> {
                    player.stop();
                }
                case "next" -> {
                    player.next();
                }
                case "previous" -> {
                    player.previous();
                }
                case "pause" -> {
                    player.pause();
                }
                case "list" -> {
                    player.list();
                }
                default -> help();
            }
        }while(!command.equalsIgnoreCase("exit"));
    }

    public void cmd() {
        do{
            System.out.print(">> ");
            command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "", " ","  " -> {}

                case "play" -> {
                    clip.play();
                }
                case "stop", "exit" -> {
                    clip.stop();
                }
                case "next" -> {
                    clip.next();
                }
                case "previous" -> {
                    clip.previous();
                }
                case "resume" -> {
                    clip.resume();
                }
                case "pause" -> {
                    clip.pause();
                }
                case "list" -> {
                    clip.list();
                }
                default -> help();
            }
        }while(!command.equalsIgnoreCase("exit"));
    }
}
