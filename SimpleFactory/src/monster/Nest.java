package monster;

public class Nest {
    public Monster createMonster(MonsterType monsterType) {
        return switch (monsterType) {
            case NEKKER -> new Nekker();
            case GOLEM -> new Golem();
            case ALGHOUL -> new Alghoul();
        };
    }
}
