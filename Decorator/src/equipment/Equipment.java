package equipment;

public class Equipment {
    private int hp;
    private int attack;
    private int defense;
    private String name;
    private boolean isPutOn;

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public void putOn() {
        isPutOn = true;
    }

    public void putOff() {
        isPutOn = false;
    }

    public boolean isPutOn() {
        return isPutOn;
    }
}
