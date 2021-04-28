package audio.player.opener;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Opener {
    private final Scanner scanner = new Scanner(System.in);
    private String defaultFolder;
    private List<File> musicList = new ArrayList();
    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;
    private volatile int index;

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
     * Displays songs saved at musicList
     */
    private synchronized void listSongs() {
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i).getName().
                    substring(0, musicList.get(i).getName().length() - 4).replace('_', ' '));
        }
    }

    /**
     * Displays the songs in the current folder and waits for the user's keyboard input
     * @throws InputMismatchException when the usersSongChoice is a non-number
     * @return chosen song
     */
    public synchronized String setCurrentSong() throws InputMismatchException {
        openFolder();
        listSongs();

        System.out.print("\nChoose song(1-" + musicList.size() + "): ");
        index = scanner.nextInt() -1;

        currentSong = musicList.get(index).getName();

        setPreviousSong(index);
        setNextSong(index);

        return currentSong;
    }

    /**
     * Checks whether currentSong is not the first element of list
     * @param indexOfCurrentSong
     * @throws NullPointerException if 'indexOfCurrentSong' is lower than 0
     * @return previousSong's name
     */
    public synchronized String setPreviousSong(int indexOfCurrentSong) throws NullPointerException{
        if (indexOfCurrentSong == 0){
            previousSong = musicList.get(musicList.size() -1).toString();
        }
        else {
            previousSong = musicList.get(indexOfCurrentSong -1).toString();
        }
        return previousSong;
    }

    /**
     * Checks whether currentSong is not the last element of list
     * @param indexOfCurrentSong
     * @throws IndexOutOfBoundsException if 'indexOfCurrentSong' is greater than size of list -1
     * @return nextSong's name
     */
    public synchronized String setNextSong(int indexOfCurrentSong) throws IndexOutOfBoundsException{
        if (indexOfCurrentSong == musicList.size() -1){
            nextSong = musicList.get(0).toString();
        }
        else {
            nextSong = musicList.get(indexOfCurrentSong +1).toString();
        }
        return nextSong;
    }

    public String getPreviousSong() {
        return previousSong;
    }

    public String getNextSong() {
        return nextSong;
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

    public int getIndex() {
        return index;
    }
}
