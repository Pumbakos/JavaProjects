package monster;

public class Golem implements Monster {
    private int health = Monster.health + 3000;
    private int attack = Monster.attack + 240;
    private int defense = Monster.defense + 300;

    @Override
    public void attack() {
        System.out.println("Golem attacks by " + attack + " points.");
    }

    @Override
    public void dodge() {
        System.out.println("Golem dodges the attack.");
    }

    /**
     * @deprecated: Golem does not bite
     */
    @Deprecated
    @Override
    public void bite() {
    }
}
