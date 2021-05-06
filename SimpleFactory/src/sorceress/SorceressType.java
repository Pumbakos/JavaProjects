package sorceress;

public enum SorceressType {
    HEALER("Healer"),
    DEFENDER("Defender"),
    ATTACKER("Attacker");

    private String name;

    SorceressType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
