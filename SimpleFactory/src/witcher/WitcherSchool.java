package witcher;

public class WitcherSchool {
    public Witcher createWitcher(WitcherType witcher) {
        return switch (witcher) {
            case WOLF_GUILD_WITCHER -> new WolfGuildWitcher();
            case CAT_GUILD_WITCHER -> new CatGuildWitcher();
        };
    }
}
