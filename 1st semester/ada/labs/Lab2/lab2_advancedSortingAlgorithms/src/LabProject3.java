import java.io.IOException;


/**
 * Lab Project for Unit 3: Divide and Conquer
 * @author ccottap
 * @version 1.0
 *
 */
public class LabProject3 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-r|-t) <i>parameters</i>.  
	 * The options are:
	 * <ul>
	 * <li> -t <i>algorithm size numtests</i>: Uses the algorithm indicated to sort a list of the size indicated the number of times indicated. </li>
	 * <li> -r <i>algorithm [initsize numsizes scalefactor numtests])</i>: Uses the algorithm indicated to sort lists of different sizes (starting at the initial size indicated,
	 * and increasing this size by a scale factor a number of times). For each size times are measured and dumped to a file for subsequent statistical analysis.</li>
	 * </ul>
	 * @throws IOException if data cannot be written to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestSortingAlgorithms.run(args);
	}

}
