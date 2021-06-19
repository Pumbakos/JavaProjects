package audio.file.controler;

import java.io.File;
import java.util.*;

public class FileController {
    private final Scanner scanner = new Scanner(System.in);
    private String defaultFolder;
    private List<File> musicList = new ArrayList();
    private volatile String currentSong;
    private volatile String previousSong;
    private volatile String nextSong;
    private volatile int index;

    protected FileController(){}

    public FileController(String defaultFolder) {
        this.defaultFolder = defaultFolder;
        openFolder();
    }

    public final void openFolder() {
        File file = new File(defaultFolder);
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (!f.isDirectory()) {
                musicList.add(new File(f.getName()));
            }
        }
    }

    public final void listSongs() {
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i).getName().
                    substring(0, musicList.get(i).getName().length() - 4).replace('_', ' '));
        }
    }

    /**
     * *Displays the songs in the current folder and waits for the user's keyboard input
     * @throws InputMismatchException when the usersSongChoice is a non-number
     * @return chosen song
     */
    public final String setCurrentSong() throws InputMismatchException {
        System.out.print("\nChoose song (1-" + musicList.size() + "): ");
        String input = scanner.nextLine();
        try {
            index = Integer.parseInt(input) -1;
        }catch(NumberFormatException e){
            System.err.println("Please choose between the range (1-" + musicList.size() + "): ");
        }
        if (index > musicList.size() || index < 0){
            throw new InputMismatchException("Out of the range.");
        }

        currentSong = musicList.get(index).getName();

        setPreviousSong(index);
        setNextSong(index);

        return currentSong;
    }

    public final String setCurrentSong(int index){
        if (index < 0){
            currentSong = musicList.get(musicList.size() -1).getName();
            this.index = musicList.size() -1;

            setPreviousSong(this.index);
            setNextSong(this.index);

            return currentSong = musicList.get(this.index).getName();
        }
        if (index > musicList.size() -1){
            currentSong = musicList.get(0).getName();
            this.index = 0;

            setPreviousSong(this.index);
            setNextSong(this.index);

            return currentSong = musicList.get(this.index).getName();
        }
        this.index = index;
        setPreviousSong(this.index);
        setNextSong(this.index);
        return currentSong = musicList.get(this.index).getName();
    }

    /**
     * *Checks whether currentSong is not the first element of list
     * @param indexOfCurrentSong defines which song to play previous
     * * @code if (indexOfCurrentSong < 1) : we have here '< 1'  because in 'else' we have -1
     * * and then it throws exception described below. If we had '< 0' then 'previousSong' would have -1 index
     * @throws NullPointerException if 'indexOfCurrentSong' is lower than 0
     * @return previousSong's name
     */
    public final String setPreviousSong(int indexOfCurrentSong) throws NullPointerException{
        if (indexOfCurrentSong < 1){
            previousSong = musicList.get(musicList.size() -1).toString();
        }
        else {
            previousSong = musicList.get(indexOfCurrentSong -1).toString();
        }
        return previousSong;
    }

    /**
     * Checks whether currentSong is not the last element of list
     * @param indexOfCurrentSong defines which song to play next
     * * if (indexOfCurrentSong > musicList.size() -2) : we have here '-2' because in 'else' we have +1
     * * and then it throws exception described below. If we had '- 1' then 'nextSong' would have musicList.size() index
     * @throws ArrayIndexOutOfBoundsException if 'indexOfCurrentSong' is greater than size of list -1
     * @return nextSong's name
     */
    public final String setNextSong(int indexOfCurrentSong) throws ArrayIndexOutOfBoundsException{
        if (indexOfCurrentSong > musicList.size() -2){
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

    public void setDefaultFolder(String defaultFolder){
        if (defaultFolder == null){
            throw new IllegalArgumentException();
        }
        this.defaultFolder = defaultFolder;
    }

    public int getIndex() {
        return index;
    }

    public List<File> getMusicList(){
        return musicList;
    }
}
