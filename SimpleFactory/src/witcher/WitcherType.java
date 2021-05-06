package witcher;

public enum WitcherType {
    WOLF_GUILD_WITCHER("Wolf Guild Witcher"),
    CAT_GUILD_WITCHER("Cat Guild Witcher");

    private String name;

    WitcherType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
