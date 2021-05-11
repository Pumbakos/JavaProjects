import equipment.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char input;

        Character character = new Character();
        Equipment rareGloves = new RareGloves(character.getEquipment());
        Equipment commonShoes = new CommonShoes(character.getEquipment());
        Equipment trousers = new Trousers(character.getEquipment());
        Equipment epicHelmet = new EpicHelmet(character.getEquipment());
        Equipment legendarySword = new LegendarySword(character.getEquipment());

        menu();

        for (; ; ) {
            input = scanner.nextLine().charAt(0);
            switch (input) {
                case '1' -> {
                    character.born();
                }

                case 'q' -> {
                    if (!character.getEquipment().isPutOn())
                        character.addEquipment(rareGloves);
                }

                case 'w' -> {
                    if (!character.getEquipment().isPutOn())
                        character.addEquipment(commonShoes);
                }

                case 'e' -> {
                    if (!character.getEquipment().isPutOn())
                        character.addEquipment(trousers);
                }

                case 'r' -> {
                    if (!character.getEquipment().isPutOn())
                        character.addEquipment(epicHelmet);
                }

                case 't' -> {
                    if (!character.getEquipment().isPutOn())
                        character.addEquipment(legendarySword);
                }

                case 'h' -> {
                    menu();
                }

                case 'x', '0' -> {
                    System.exit(0);
                }
            }
        }
    }

    public static void menu() {
        System.out.println("Press '1' to create character.");
        System.out.println("Press 'q' to add/remove " + EquipmentType.RARE_GLOVES);
        System.out.println("Press 'w' to add/remove " + EquipmentType.COMMON_SHOES);
        System.out.println("Press 'e' to add/remove " + EquipmentType.TROUSERS);
        System.out.println("Press 'r' to add/remove " + EquipmentType.EPIC_HELMET);
        System.out.println("Press 't' to add/remove " + EquipmentType.LEGENDARY_SWORD);
        System.out.println("Press 'h' to show help.");
        System.out.println("Press 'x' to exit.");
    }
}
