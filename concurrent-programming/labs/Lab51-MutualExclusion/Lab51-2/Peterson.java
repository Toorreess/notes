public class Peterson {
    private volatile int turn = 0;
    private volatile boolean f0 = false;
    private volatile boolean f1 = false;

    public void preProt0() {
        f0 = true;
        turn = 1;
        while (f1 && turn == 1) {
            Thread.yield();
        }
    }

    public void postProt0() {
        f0 = false;
    }

    public void preProt1() {
        f1 = true;
        turn = 0;
        while (f0 && turn == 0) {
            Thread.yield();
        }
    }

    public void postProt1() {
        f1 = false;
    }
}
