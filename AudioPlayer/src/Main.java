import audio.controler.ClipQueue;
import audio.file.controller.FileDownloader;
import audio.player.SoundClip;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String DIRECTORY = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\downloaded\\";

    public static void main(String[] args) {
        SoundClip clip = SoundClip.getInstance();
        ClipQueue queue = new ClipQueue();

        clip.setProperties(queue, clip);
        clip.subscribe(queue);

        FileDownloader fileDownloader = new FileDownloader();

        fileDownloader.setLink(FileDownloader.getRequestUrl());

        new Thread(() -> {
            List<String> list = fileDownloader.getList();
            for (String s : list) {
                System.out.println(s);
            }
            try {
                fileDownloader.downloadWav(list.get(4));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

//        clip.menu();
//        clip.cmd();
    }
}

