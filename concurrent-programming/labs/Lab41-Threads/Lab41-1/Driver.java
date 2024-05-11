public class Driver {
    public static void main(String[] args) {
        Wri a = new Wri(0, 100, 'A');
        Wri b = new Wri(1, 100, 'B');
        Wri c = new Wri(2, 100, 'C');

        a.start();
        b.start();
        c.start();
    }
}
