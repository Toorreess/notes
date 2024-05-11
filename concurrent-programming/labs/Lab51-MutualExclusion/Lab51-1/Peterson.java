public class Peterson {
    private volatile int turn = 0;
    private volatile boolean fp = false;
    private volatile boolean fc = false;

    public void preProtp() {
        fp = true;
        turn = 1;
        while (fc && turn == 1) {
            Thread.yield();
        }
    }

    public void postProtp() {
        fp = false;
    }

    public void preProtc() {
        fc = true;
        turn = 0;
        while (fp && turn == 0) {
            Thread.yield();
        }
    }

    public void postProtc() {
        fc = false;
    }
}
