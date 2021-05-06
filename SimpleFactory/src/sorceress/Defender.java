package sorceress;

public class Defender implements Sorceress {
    private int health = Sorceress.health + 350;
    private int attack = Sorceress.attack;
    private int defense = Sorceress.defense + 150;
    private int stamina = Sorceress.stamina + 100;

    @Override
    public void heal() {
        System.out.println("Sorceress heals by " + ((stamina / defense) + (health % attack)) + " hp");
    }
}
