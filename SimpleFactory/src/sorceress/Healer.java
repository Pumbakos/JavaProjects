package sorceress;

public class Healer implements Sorceress {
    private int health = Sorceress.health - 250;
    private int attack = Sorceress.attack - 65;
    private int defense = Sorceress.defense + 10;
    private int stamina = Sorceress.stamina + 500;

    @Override
    public void heal() {
        System.out.println("Sorceress heals by " + 8 * ((stamina / defense) + (health % attack)) + " hp");
    }
}
