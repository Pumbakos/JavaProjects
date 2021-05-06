package witcher;

public class WolfGuildWitcher implements Witcher {
    int health = Witcher.health;
    int attack = Witcher.attack;
    int defense = Witcher.defense;
    int stamina = Witcher.stamina;


    @Override
    public void attack() {
        System.out.println("WolfGuildWitcher attacks by " + attack + " points.");
    }

    @Override
    public void dodge() {
        System.out.println("WolfGuildWitcher dodges attack.");
    }
}
