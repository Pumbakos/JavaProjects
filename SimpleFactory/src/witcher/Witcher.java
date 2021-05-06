package witcher;

import java.util.Random;

public interface Witcher {
    int health = 3000;
    int attack = 240;
    int defense = 400;
    int stamina = 500;

    void attack();

    void dodge();

    default void spell() {
        int spell = new Random().nextInt(WitcherSpell.values().length - 1);

        switch (spell) {
            case 0 -> System.out.println("Witcher casts " + WitcherSpell.YRDEN.name());
            case 1 -> System.out.println("Witcher casts " + WitcherSpell.QUEN.name());
            case 2 -> System.out.println("Witcher casts " + WitcherSpell.IGNI.name());
            case 3 -> System.out.println("Witcher casts " + WitcherSpell.AXII.name());
            case 4 -> System.out.println("Witcher casts " + WitcherSpell.AARD.name());
        }
    }
}
