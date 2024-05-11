public class Writer extends Thread {
    private SharedVariable sv;

    public Writer(SharedVariable sv) {
        this.sv = sv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            while (sv.turn != 0)
                Thread.yield();

            sv.inc();
            sv.turn = 1;
        }
    }
}
