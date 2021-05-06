package witcher;

public class CatGuildWitcher implements Witcher {
    int health = Witcher.health - 500;
    int attack = Witcher.attack + 100;
    int defense = Witcher.defense - 70;
    int stamina = Witcher.stamina + 150;


    @Override
    public void attack() {
        System.out.println("CatGuildWitcher attacks by " + attack + " points.");
    }

    @Override
    public void dodge() {
        System.out.println("CatGuildWitcher dodges attack.");
    }
}
