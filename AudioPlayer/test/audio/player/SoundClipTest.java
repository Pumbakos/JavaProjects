package audio.player;

import audio.controler.ClipQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SoundClipTest {
    SoundClip clip = SoundClip.getInstance();

    @Test
    void setDefaultFolderToNull(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> clip.setDefaultFolder(null));
    }

    @Test
    @DisplayName("Add subscriber to subs list")
    void addSubscriber(){
        assertTrue(clip.subscribe(new ClipQueue()));
    }

    @Test
    @DisplayName("Add null subscriber to subs list")
    void addNullSubscriber(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> clip.subscribe(null));
    }

    @Test
    @DisplayName("Remove subscriber from subs list")
    void removeSubscriber(){
        ClipQueue cq = new ClipQueue();
        clip.subscribe(cq);
        assertTrue(clip.unsubscribe(cq));
    }

    @Test
    @DisplayName("Remove null subscriber from subs list")
    void removeNullSubscriber(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> clip.unsubscribe(null));
    }

    @Test
    @DisplayName("Remove subscriber from empty subs list")
    void removeSubscriberFromEmptyList(){
        ClipQueue cq = new ClipQueue();
        clip.subscribe(cq);
        clip.removeAllSubscribers();
        NullPointerException e = assertThrows(NullPointerException.class, () -> clip.unsubscribe(cq));
    }

    @Test
    @DisplayName("Remove null subscriber from empty subs list")
    void removeNullSubscriberWhenListIsEmpty(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> clip.unsubscribe(null));
    }
}