import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPane implements Sleep {
    private String login;
    private String password;
    private String email;

    public LoginPane() {
        //checkLogin();
        //checkPassword();
    }

    public String checkLogin() {
        Pattern passPattern = Pattern.compile("^(?=.*[a-zA-z])((?=.*[0-9])*)(?!.*\\s)(?!.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)])(?!.*[\\_\\=\\+\\-\\,\\.\\>\\<\\?\\`\\~]).{5,14}$");
        this.login = "wrongLogin";
        String enteredLogin = "login";

        System.out.print("ENTER LOGIN: ");
        try {
            enteredLogin = new Scanner(System.in).nextLine();
        } catch (NumberFormatException e) {
            this.login = "wrongLogin";
        }

        Matcher passMatcher = passPattern.matcher(enteredLogin);

        if (passMatcher.matches()) {
            this.login = enteredLogin;
        }

        return this.login;
    }

    public String checkPassword() {
        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\*\\/\\.\\,\\>\\<])(?!=*\\s\\n).{8,15}$");
        this.password = "wrongPass";
        String enteredPass = "password";

        System.out.print("ENTER PASSWORD: ");
        try {
            enteredPass = new Scanner(System.in).nextLine();
        } catch (NumberFormatException e) {
            this.password = "wrongPass";
        }

        Matcher passMatcher = passPattern.matcher(enteredPass);
        if (passMatcher.matches()) {
            this.password = enteredPass;
        }

        return this.password;
    }

    public boolean checkPropriety() {
        String properPass, properLogin;
        boolean checker;
        int signQuestion = 0;
        do {
            properLogin = checkLogin();
            properPass = checkPassword();

            if (!properLogin.equals("wrongLogin") && (!properPass.equals("wrongPass"))) {
                checker = true;
            } else {
                System.out.println("Wrong data, try again");
                signQuestion++;
                if (signQuestion >= 2) {
                    System.out.print("Do you want to sign up?\tY/N ");
                    char c = '3';
                    try {
                        c = new Scanner(System.in).nextLine().charAt(0);
                    } catch (Exception e) {
                        System.out.println("  !!!  ParseError while try/catch in chechPropesty() at LoginPane");
                        e.getClass();
                    }
                    switch (c) {
                        case 'Y':
                        case 'y':
                            SignUpPane signupPane = new SignUpPane();
                            break;

                        case 'N':
                        case 'n':
                            System.exit(0);
                            break;

                        default:
                            System.out.println("default");
                            break;
                    }

                }
                checker = false;
            }
        } while (!checker);

        return true;
    }

    @Override
    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Sorry I can't sleep");
        }
    }
}
