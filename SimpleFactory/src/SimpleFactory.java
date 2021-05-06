import monster.Monster;
import monster.MonsterType;
import monster.Nest;
import sorceress.Sorceress;
import sorceress.SorceressLodge;
import sorceress.SorceressType;
import witcher.Witcher;
import witcher.WitcherSchool;
import witcher.WitcherType;

public class SimpleFactory {
    private WitcherSchool kaerMorhen;
    private SorceressLodge sorceressLodge;
    private Nest nest;

    public void createFactory(FactoryType type) {
        switch (type) {
            case WITCHER_SCHOOL -> {
                System.out.println("Built " + type);
                kaerMorhen = new WitcherSchool();
            }
            case SORCERESS_LODGE -> {
                System.out.println("Built " + type);
                sorceressLodge = new SorceressLodge();
            }
            case NEST -> {
                System.out.println("Built " + type);
                nest = new Nest();
            }
        }
    }

    public Witcher createWitcher(WitcherType witcher) {
        if (!isWitcherSchoolBuilt()){
            throw new NullPointerException();
        }

        System.out.println("Created " + witcher.toString());
        return kaerMorhen.createWitcher(witcher);
    }

    public Sorceress createSorceress(SorceressType sorceress) {
        if(!isSorceressLodgeBuilt()){
            throw new NullPointerException();
        }

        System.out.println("Created " + sorceress.toString());
        return sorceressLodge.createSorceress(sorceress);
    }

    public Monster createMonster(MonsterType monster) {
        if(!isNestBuilt()){
            throw new NullPointerException();
        }

        System.out.println("Created " + monster.toString());
        return nest.createMonster(monster);
    }

    boolean isWitcherSchoolBuilt() {
        return kaerMorhen != null;
    }

    boolean isSorceressLodgeBuilt() {
        return sorceressLodge != null;
    }

    boolean isNestBuilt() {
        return nest != null;
    }

    public WitcherSchool getWitcherSchool() {
        return kaerMorhen;
    }

    public SorceressLodge getSorceressLodge() {
        return sorceressLodge;
    }

    public Nest getNest() {
        return nest;
    }
}
