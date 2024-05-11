public class Reader extends Thread {
    private SharedVariable sv;

    public Reader(SharedVariable sv) {
        this.sv = sv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            while (sv.turn != 1)
                Thread.yield();

            System.out.print(sv.getVal() + " ");
            sv.turn = 0;
        }
    }
}
