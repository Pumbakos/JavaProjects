package monster;

public class Alghoul implements Monster {
    private int health = Monster.health - 2500;
    private int attack = Monster.attack + 40;
    private int defense = Monster.defense - 120;

    @Override
    public void attack() {
        bite();
    }

    @Override
    public void dodge() {
        System.out.println("Alghoul dodges the attack.");
    }


    @Override
    public void bite() {
        System.out.println("Alghoul bites by " + attack + " points.");
    }
}
