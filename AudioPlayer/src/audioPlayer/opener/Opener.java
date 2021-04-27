package audioPlayer.opener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Opener {
    private final Scanner scanner = new Scanner(System.in);
    private String defaultFolder;
    private List<File> musicList = new ArrayList();
    private String currentSong;

    public Opener(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }

    /**
     * Opens default music folder - my local actually :)
     * @return a list of read files
     */
    public synchronized void openFolder() throws NullPointerException {
        File file = new File(defaultFolder);
        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                musicList.add(new File(f.getName()));
            }
        }
    }

    /**
     * Displays songs saved at @see musicList
     */
    private synchronized void listSongs() {
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i).getName().
                    substring(0, musicList.get(i).getName().length() - 4).replace('_', ' '));
        }
    }

    /**
     * Displays the songs in the current folder and waits for the user's keyboard input
     * @return chosen song
     */
    public synchronized String setCurrentSong() {
        int usersSongChoice;
        openFolder();
        listSongs();
        System.out.print("\nChoose song(1-" + musicList.size() + "): ");
        usersSongChoice = scanner.nextInt();
        currentSong = musicList.get(usersSongChoice - 1).getName();

        return currentSong;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public String getDefaultFolder() {
        return defaultFolder;
    }

    public void setDefaultFolder(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }
}
