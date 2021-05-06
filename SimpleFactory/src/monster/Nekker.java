package monster;

public class Nekker implements Monster{
    private int health = Monster.health;
    private int attack = Monster.attack;
    private int defense = Monster.defense;


    @Override
    public void attack() {
        System.out.println("Nekker attacks by " + attack + " points.");
    }

    @Override
    public void dodge() {
        System.out.println("Nekker dodges the attack.");
    }

    /**
     * @deprecated: Nekker does not bite
     */
    @Deprecated
    @Override
    public void bite() {}
}
