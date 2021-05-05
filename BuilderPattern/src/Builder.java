public class Builder {
    private String name;
    private String surname;
    private String PESEL;
    private String birthDate;
    private int age;
    private int growth;

    public static class InnerBuilder {
        private String name = null;
        private String surname = null ;
        private String PESEL = null ;
        private String birthDate = null;
        private int age = 0;
        private int growth = 0;

        public InnerBuilder name(String name){
            this.name = name;
            return this;
        }

        public InnerBuilder surname(String surname){
            this.surname = surname;
            return this;
        }

        public InnerBuilder PESEL(String PESEL){
            this.PESEL = PESEL;
            return this;
        }

        public InnerBuilder birthDate(String birthDate){
            this.birthDate = birthDate;
            return this;
        }

        public InnerBuilder age(int age){
            this.age = age;
            return this;
        }

        public InnerBuilder growth(int growth){
            this.growth = growth;
            return this;
        }

        public Builder build(){
            return new Builder(this);
        }



    }

    private Builder(InnerBuilder innerBuilder){
        this.name = innerBuilder.name;
        this.surname = innerBuilder.surname;
        this.PESEL = innerBuilder.PESEL;
        this.birthDate = innerBuilder.birthDate;
        this.age = innerBuilder.age;
        this.growth = innerBuilder.growth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public int getGrowth() {
        return growth;
    }
}
