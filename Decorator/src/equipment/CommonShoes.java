package equipment;

public class CommonShoes extends Equipment {
    private final int ATTACK = 3;
    private final int HP = 1;
    private final int DEFENSE = 2;
    private final String NAME = EquipmentType.COMMON_SHOES.toString();
    private boolean isPutOn = false;
    private Equipment equipment;

    public CommonShoes(Equipment equipment) {
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
