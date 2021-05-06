package sorceress;

public class Attacker implements Sorceress {
    private int health = Sorceress.health - 450;
    private int attack = Sorceress.attack + 130;
    private int defense = Sorceress.defense + 30;
    private int stamina = Sorceress.stamina - 170;

    /**
     * @deprecated: attacker attacks not heal
     */
    @Deprecated
    public void heal() {
        System.out.println("I cannot heal...");
    }
}
