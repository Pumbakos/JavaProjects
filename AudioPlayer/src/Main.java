import audio.controler.ClipQueue;
import audio.exception.ListNotFoundException;
import audio.exception.NotFoundException;
import audio.file.controller.FileDownloader;
import audio.player.SoundClip;

import java.util.List;

public class Main {
    private static final String DIRECTORY = "D:\\Desktop\\CODE\\JAVA\\AudioPlayer\\music\\wav\\downloaded\\";

    public static void main(String[] args) {
        SoundClip clip = SoundClip.getInstance();
        ClipQueue queue = new ClipQueue();

        clip.setProperties(queue, clip);
        clip.subscribe(queue);

        FileDownloader fileDownloader = new FileDownloader();

        fileDownloader.setLink(fileDownloader.getRequestUrl());

        new Thread(() -> {
            List<String> list = null;
            try {
                list = fileDownloader.getList();
            } catch (ListNotFoundException e) {
                e.printStackTrace();
            }

            try {
                fileDownloader.download(list.get(3));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }).start();

//        clip.menu();
//        clip.cmd();
    }
}

