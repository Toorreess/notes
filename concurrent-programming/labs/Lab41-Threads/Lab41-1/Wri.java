public class Wri extends Thread {
    private static int turn = 0;
    private int count;
    private char letter;
    private int id;

    public Wri(int id, int t, char c) {
        this.count = t;
        this.letter = c;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            while (turn != id) {
                Thread.yield();
            }
            for (int j = 0; j < id + 1; j++) {
                System.out.print(letter);
            }
            turn = (turn + 1) % 3;
        }
    }
}
