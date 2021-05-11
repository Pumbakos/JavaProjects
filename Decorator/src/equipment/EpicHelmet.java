package equipment;

public class EpicHelmet extends Equipment {
    private final int ATTACK = 15;
    private final int HP = 5;
    private final int DEFENSE = 5;
    private final String NAME = EquipmentType.EPIC_HELMET.toString();
    private boolean isPutOn = false;
    private Equipment equipment;

    public EpicHelmet(Equipment equipment) {
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
