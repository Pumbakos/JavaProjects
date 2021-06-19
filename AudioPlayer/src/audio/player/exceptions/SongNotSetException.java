package audio.player.exceptions;

public class SongNotSetException extends RuntimeException{
    public SongNotSetException(){
        super();
    }

    public SongNotSetException(String message){
        super(message);
    }
}
