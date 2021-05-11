package equipment;

public enum EquipmentType {
    RARE_GLOVES("RareGloves"),
    COMMON_SHOES("CommonShoes"),
    TROUSERS("Trousers"),
    EPIC_HELMET("EpicHelmet"),
    LEGENDARY_SWORD("LegendarySword");


    private String name;

    EquipmentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
