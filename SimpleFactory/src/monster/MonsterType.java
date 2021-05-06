package monster;

public enum MonsterType {
    NEKKER("Nekker"),
    GOLEM("Golem"),
    ALGHOUL("Alghoul");

    private String name;

    MonsterType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
