public class Main {
    public static void main(String[] args) {
        Builder builder = new Builder.InnerBuilder()
                .name("Dawid")
                .age(23)
                .birthDate("03.12.1997")
                .surname("Pałubiak")
                .growth(189)
                .build();

        System.out.print(builder.getName());
        System.out.print(" " + builder.getSurname());
        System.out.print(", lat " +  builder.getAge());
        System.out.println(", urodzony " + builder.getBirthDate() );
    }
}
