
import java.util.LinkedList;
import java.util.List;

import es.uma.ada.problem.algebra.diophantine.pythagorean.PythagoreanTriple;
import es.uma.ada.problem.algebra.diophantine.pythagorean.PythagoreanTripleFinder;
import es.uma.ada.problem.algebra.diophantine.DiophantineUtil;
/**
 * Finds primitive Pythagorean triples by naive brute force, i.e., by
 * considering all possible values under the limit
 * 
 * @author ccottap
 * @version 1.0
 */
public class NaivePythagoreanTripleFinder extends PythagoreanTripleFinder {

	@Override
	protected List<PythagoreanTriple> _run(long l) {
		LinkedList<PythagoreanTriple> lp = new LinkedList<PythagoreanTriple>();
		// TODO Complete this function
		// Check all suitable pairs (a,b), to see if they define
		// a Pythagorean triple (a, b, c)
		
		// O(l^3) algorithm
//		for (int a = 1; a < l-2; a++) {
//			for (int b = a + 1; b < l-1; b++) {
//				for (int c = b + 1; c < l; c++) {
//					if (c*c == a*a + b*b && DiophantineUtil.gcd(a, b) == 1) {
//						lp.add(new PythagoreanTriple(a, b, c));
//					}
//				}
//			}
//		}

		
		// O(l^2) algorithm
//		for(int a = 1; a < l-2; a++) {
//			for(int b = a+1; b < l-1; b++) {
//				int h = a*a + b*b;
//				int c = (int) Math.floor(Math.sqrt(h));
//				if (c*c == h && c< l && DiophantineUtil.gcd(a, b) == 1) {
//					lp.add(new PythagoreanTriple(a, b, c));
//				}
//			}
//		}
		
		// O(l^2) algorithm with improvements
		for(int a = 1; a < l-2; a++) {
			int lim = (int) Math.floor(Math.sqrt(l*l- a*a));
			for(int b = a+1; b < lim; b++) {
				int h = a*a + b*b;
				int c = (int) Math.floor(Math.sqrt(h));
				if (c*c == h && c< l && DiophantineUtil.gcd(a, b) == 1) {
					lp.add(new PythagoreanTriple(a, b, c));
				}
			}
		}
		
		return lp;
	}

	@Override
	public String getName() {
		return "NaivePythagoreanTripleFinder";
	}
}