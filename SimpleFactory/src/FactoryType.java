public enum FactoryType {
    WITCHER_SCHOOL("Witcher School"),
    SORCERESS_LODGE("Sorceress Lodge"),
    NEST("Nest");

    private String name;

    FactoryType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
