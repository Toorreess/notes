
public class Driver {
	public static void main(String[] args) {

		Lake lake = new Lake();
		River r0 = new River(lake, 0);
		River r1 = new River(lake, 1);
		Dam d0 = new Dam(lake, 0);
		Dam d1 = new Dam(lake, 1);

		r0.start();
		r1.start();
		d0.start();
		d1.start();

		try {
			r0.join();
			r1.join();
			d0.join();
			d1.join();
		} catch (InterruptedException ie) {
			System.out.println("Thread interrupted");
		}
		System.out.println("Final level: " + lake.getlevel());
	}
}
