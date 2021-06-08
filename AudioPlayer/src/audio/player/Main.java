package audio.player;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        AudioPlayer player = new AudioPlayer();
//        Controller controller = new Controller(player);

        String audioFilePath = "D:\\Steam\\steamapps\\common\\Counter-Strike Global Offensive\\csgo\\sound\\kodua\\fortnite_emotes\\athena_emote_founders_music.wav";
        SoundClip player = new SoundClip();
        player.play();

//        controller.menu();
//        controller.simpleCmd();
    }
}
