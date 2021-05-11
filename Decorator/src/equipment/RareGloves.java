package equipment;

public class RareGloves extends Equipment {
    private final int ATTACK = 10;
    private final int HP = 3;
    private final int DEFENSE = 4;
    private final String NAME = EquipmentType.RARE_GLOVES.toString();
    private boolean isPutOn = false;
    private Equipment equipment;

    public RareGloves(Equipment equipment) {
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
