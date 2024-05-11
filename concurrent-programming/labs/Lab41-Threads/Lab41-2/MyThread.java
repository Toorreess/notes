public class MyThread extends Thread {
    private static final int LOOPS = 1000;
    private SharedVariable sv;

    public MyThread(SharedVariable sv) {
        this.sv = sv;
    }

    @Override
    public void run() {
        for (int i = 0; i < LOOPS; i++) {
            sv.inc();
        }
    }
}
