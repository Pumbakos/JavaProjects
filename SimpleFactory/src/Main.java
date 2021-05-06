import monster.MonsterType;
import sorceress.SorceressType;
import witcher.WitcherType;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        Scanner scanner = new Scanner(System.in);
        char input;

        help();

        for(;;) {
            System.out.print(">> ");
            input = scanner.next().charAt(0);

            switch (input) {
                case '1' -> {
                    if (!factory.isWitcherSchoolBuilt())
                        factory.createFactory(FactoryType.WITCHER_SCHOOL);
                    else
                        System.out.println("The Witcher School has already been built");
                }
                case '2' -> {
                    if (!factory.isSorceressLodgeBuilt())
                        factory.createFactory(FactoryType.SORCERESS_LODGE);
                    else
                        System.out.println("The Sorceress Lodge has already been built");
                }
                case '3' -> {
                    if (!factory.isNestBuilt())
                        factory.createFactory(FactoryType.NEST);
                    else
                        System.out.println("The Nest has already been built");
                }
                case 'w' -> {
                    try {
                        factory.createWitcher(WitcherType.WOLF_GUILD_WITCHER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Witcher School.");
                    }
                }
                case 'c' -> {
                    try {
                        factory.createWitcher(WitcherType.CAT_GUILD_WITCHER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Witcher School.");
                    }
                }
                case 'h' -> {
                    try {
                        factory.createSorceress(SorceressType.HEALER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Sorceress Lodge.");
                    }
                }
                case 'a' -> {
                    try {
                        factory.createSorceress(SorceressType.ATTACKER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Sorceress Lodge.");
                    }
                }
                case 'd' -> {
                    try {
                        factory.createSorceress(SorceressType.DEFENDER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Sorceress Lodge.");
                    }
                }
                case 'g' -> {
                    try{
                        factory.createMonster(MonsterType.GOLEM);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Nest.");
                    }
                }
                case 'l' -> {
                    try{
                        factory.createMonster(MonsterType.ALGHOUL);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Nest.");
                    }
                }
                case 'n' -> {
                    try{
                        factory.createMonster(MonsterType.NEKKER);
                    }catch(NullPointerException e){
                        System.out.println("Consider building Nest.");
                    }
                }
                case 'x', 0 -> {
                    System.out.println("Zaraza...\nZanosi się na deszcz");
                    System.exit(0);
                }
                case '/', '?' -> help();
                default -> System.out.println("They don't fight on our side.");
            }

        }
    }

    private static void help() {
        System.out.println("Press '1' to build Witcher's School.");
        System.out.println("Press '2' to build Sorceress Lodge.");
        System.out.println("Press '3' to build Monster nest.");
        System.out.println("Press 'w' to create Wolf Guild Witcher.");
        System.out.println("Press 'c' to create Cat Guild Witcher.");
        System.out.println("Press 'h' to create Sorceress Healer.");
        System.out.println("Press 'a' to create Sorceress Attacker.");
        System.out.println("Press 'd' to create Sorceress Defender.");
        System.out.println("Press 'g' to create Golem.");
        System.out.println("Press 'l' to create Alghoul.");
        System.out.println("Press 'n' to create Nekker.");
        System.out.println("Press '?' to see this again.");
        System.out.println("Press 'x' to exit");
    }
}
