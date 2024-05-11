
public class Lake {
	private volatile int level = 0;
	private Peterson sRiver = new Peterson();
	private Peterson sDam = new Peterson();
	private Peterson sLake = new Peterson();

	public void increment(int id) {
		if (id == 0)
			sRiver.preProt0();
		else
			sRiver.preProt1();

		sLake.preProt0();
		level++;
		System.out.println("River " + id + " has increased the level to " + level);
		sLake.postProt0();

		if (id == 0)
			sRiver.postProt0();
		else
			sRiver.postProt1();
	}

	public void decrement(int id) {
		boolean canDecrease = true;
		do {
			if (id == 0)
				sDam.preProt0();
			else
				sDam.preProt1();

			sLake.preProt1();
			if (level == 0) {
				System.out.println("Dam " + id + " cannot decrease the level of the river.");
				canDecrease = false;
			} else {
				level--;
				System.out.println("Dam " + id + " has decreased the level to " + level);
				canDecrease = true;
			}
			sLake.postProt1();
			if (id == 0)
				sDam.postProt0();
			else
				sDam.postProt1();

			if (!canDecrease)
				Thread.yield();
		} while (!canDecrease);
	}

	public int getlevel() {
		return level;
	}
}
