import java.util.List;

import es.uma.ada.problem.algebra.diophantine.pythagorean.PythagoreanTriple;

public class mainnn {
	public static void main(String[] args) {
		NaivePythagoreanTripleFinder n = new NaivePythagoreanTripleFinder();
		
		List<PythagoreanTriple> l = n._run(1000);
		int i = 1;
		for(PythagoreanTriple p : l) {
			System.out.println(i + ": " + p);
			i++;
		}
	}
}
