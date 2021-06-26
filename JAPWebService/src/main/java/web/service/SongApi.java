package web.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SongApi {
    List<Song> songs;

    public SongApi() {
        Song bandyta = new Song("Bandyta", "Sobel", "Pułapka na motyle", "2021");
        Song zaczarowany = new Song("Zaczarowany Mózg", "Sobel", "Pułapka na motyle", "2021");
        Song toJa = new Song("To ja", "Sobel", "Pułapka na motyle", "2021");
        songs = Arrays.asList(bandyta, zaczarowany, toJa);
    }

    @PostMapping("/api/song/add")
    public void addSong(@RequestBody Song song) {
        songs.add(song);
    }

    @GetMapping("/api/song/get")
    public List<Song> getSong(){
        return songs;
    }
}
