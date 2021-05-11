import equipment.Equipment;
import org.jetbrains.annotations.NotNull;

public class Character {
    private final Equipment equipment;
    private int hp = 100;
    private int attack = 10;
    private int defense = 10;
    private boolean isAlive = false;

    public Character() {
        this.equipment = new Equipment();
    }

    public void addEquipment(@NotNull Equipment equipment) {
        if (!equipment.isPutOn() && isAlive) {
            this.attack += equipment.getAttack();
            this.defense += equipment.getDefense();
            this.hp += equipment.getHp();

            equipment.putOn();
            System.out.println("Added " + equipment.getName());
            showStats();
        } else if (equipment.isPutOn()) {
            removeEquipment(equipment);
        } else {
            System.out.println("Character is dead.");
        }
    }

    public void removeEquipment(@NotNull Equipment equipment) {
        this.attack -= equipment.getAttack();
        this.defense -= equipment.getDefense();
        this.hp -= equipment.getHp();
        showStats();
        System.out.println("Removed " + equipment.getName());
        equipment.putOff();
    }

    public void showStats() {
        System.out.printf("HP: %d, Attack: %d, Defense: %d%n", hp, attack, defense);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void born() {
        System.out.println("Character is now alive.");
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
