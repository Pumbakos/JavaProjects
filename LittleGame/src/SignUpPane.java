public class SignUpPane implements Sleep {
    @Override
    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Sorry I can't sleep");
        }
    }
}
