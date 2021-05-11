package equipment;

public class LegendarySword extends Equipment {
    private final int ATTACK = 25;
    private final int HP = 10;
    private final int DEFENSE = 10;
    private final String NAME = EquipmentType.LEGENDARY_SWORD.toString();
    private boolean isPutOn = false;
    private Equipment equipment;

    public LegendarySword(Equipment equipment) {
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
