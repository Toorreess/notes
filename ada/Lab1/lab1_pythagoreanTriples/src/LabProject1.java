import java.io.IOException;

import es.uma.ada.problem.algebra.diophantine.pythagorean.TestPythagoreanTripleFinder;




/**
 * Lab Project for Unit 1: Brute Force
 * @author ccottap
 * @version 1.0
 *
 */
public class LabProject1 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-r|-t) <i>parameters</i>.  
	 * The options are:
	 * <ul>
	 * <li> -t <i>maxval</i>: Finds all Pythagorean triples up to <i>maxval</i>. </li>
	 * <li> -r <i>maxval</i>: Tries to find Pythagorean triples successively from a small limit
	 * up to <i>maxval</i> and measures time in each case. These data are dumped into a
	 * file for subsequent statistical analysis-</li>
	 * </ul>
	 * @throws IOException if data cannot be written to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestPythagoreanTripleFinder.run(args);
	}

}
