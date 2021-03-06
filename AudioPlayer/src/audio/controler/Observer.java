package audio.controler;

public interface Observer {
    boolean subscribe(Subscriber subscriber);
    boolean unsubscribe(Subscriber subscriber);
    void notifySubscribers();
}
