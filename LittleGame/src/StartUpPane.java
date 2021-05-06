import java.util.InputMismatchException;
import java.util.Scanner;

public class StartUpPane implements Sleep {
    public void startPage() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. LOG IN\t2. SIGN UP\n");
        int i = 3;


        try {
            i = scanner.nextInt();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("System.in Error, StartUp Pane in try/catch at startPage");
            System.out.println((e.getClass()));
        }

        switch (i) {
            case 1: {
                LoginPane login = new LoginPane();
                //System.out.println(login.checkLogin());
                //System.out.println(login.checkPassword());
                System.out.println(login.checkPropriety());
            }
            break;

            case 2: {
                new SignUpPane();
            }
            break;

            default:
                System.out.println("A CHUJ Z TOBĄ NIEUDACZNIKU");
                break;
        }
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
