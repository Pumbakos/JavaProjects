package monster;

public interface Monster {
    int health = 7000;
    int attack = 160;
    int defense = 370;

    void attack();

    void dodge();

    void bite();
}
