public class Buffer {
    private int[] items;
    private int size;
    private int p = 0;
    private int c = 0;
    private int nItem = 0;
    private Peterson s = new Peterson();

    public Buffer(int i) {
        this.size = i;
        items = new int[size];
    }

    public void loads(int data) {
        while (nItem == size)
            Thread.yield();

        s.preProtp();
        // ! Start Critical Section
        items[p] = data;
        nItem++;
        p = (p + 1) % size;
        System.out.println("Producer puts " + data + " into " + p);
        // ! End Critical Section
        s.postProtp();
    }

    public int takes() {
        while (nItem == 0)
            Thread.yield();

        s.preProtc();
        // ! Start Critical Section
        int data = items[c];
        nItem--;
        c = (c + 1) % size;
        System.out.println("Consumer takes " + data + " from " + c);
        // ! End Critical Section
        s.postProtc();
        return 0;
    }

}
