package sorceress;

public class SorceressLodge {
    public Sorceress createSorceress(SorceressType sorceress){
        return switch (sorceress){
            case HEALER -> new Healer();
            case DEFENDER -> new Defender();
            case ATTACKER -> new Attacker();
        };
    }
}
