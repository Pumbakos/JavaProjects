package audioPlayer;

public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
//        String bandyta = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\src\\audioPlayer\\music\\bandyta.mp3";

        player.openFolder();
        player.listFiles();

        String song = player.chooseSong();

        player.play(song);
//        System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "music/");
    }
}
