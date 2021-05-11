package equipment;

public class Trousers extends Equipment {
    private final int ATTACK = 5;
    private final int HP = 3;
    private final int DEFENSE = 2;
    private final String NAME = EquipmentType.TROUSERS.toString();
    private boolean isPutOn = false;
    private Equipment equipment;

    public Trousers(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public int getAttack() {
        return equipment.getAttack() + ATTACK;
    }

    @Override
    public int getHp() {
        return equipment.getHp() + HP;
    }

    @Override
    public int getDefense() {
        return equipment.getDefense() + DEFENSE;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
