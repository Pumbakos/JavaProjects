package sorceress;

import java.util.Random;

public interface Sorceress {
    int health = 5000;
    int attack = 100;
    int defense = 200;
    int stamina = 1300;

    default void spell(){
        int spell = new Random().nextInt(SorceressSpell.values().length -1);

        switch (spell){
            case 0 -> System.out.println("Sorceress casts " + SorceressSpell.BARRIER.name() + " spell");
            case 1 -> System.out.println("Sorceress casts " + SorceressSpell.ALZURS_SHIELD.name() + " spell");
            case 2 -> System.out.println("Sorceress casts " + SorceressSpell.HEALING.name() + " spell");
            case 3 -> System.out.println("Sorceress casts " + SorceressSpell.ENHANCEMENT.name() + " spell");
            case 4 -> System.out.println("Sorceress casts " + SorceressSpell.FORCE_FIELD.name() + " spell");
            case 5 -> System.out.println("Sorceress casts " + SorceressSpell.ALZURS_THUNDERBOLT.name() + " spell");
        }
    }

    void heal();

    default void createPortal(){
        int portal = new Random().nextInt(SorceressPortal.values().length -1);

        switch(portal){
            case 0 -> System.out.println("Sorceress created " + SorceressPortal.TELEPORTATION.name() + " portal");
            case 1 -> System.out.println("Sorceress created " + SorceressPortal.INTERWORD.name() + " portal");
        }
    }
}
