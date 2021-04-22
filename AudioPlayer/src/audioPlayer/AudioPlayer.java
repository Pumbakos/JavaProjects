package audioPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudioPlayer {
    private List<File> musicFiles = new ArrayList();

    public void openFolder(String path){}

    public String chooseSong(){return null;}

    public void play(){}

    public void pause(){}

    public void resume(){}

    public void stop(){}

    public void next(){}

    public void previous(){}

    public void listFilesForFolder(final File folder) {
        for (final File file : folder.listFiles()) {
            if (file.isDirectory()) {
//                listFilesForFolder(file);
                continue;
            } else {
                musicFiles.add(new File(file.getName()));
            }
        }
    }

    public void listFiles(){
        for (File file : this.musicFiles) {
            System.out.println(file);
        }
    }
}