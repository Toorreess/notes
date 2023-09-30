import java.io.IOException;

/**
 * Lab Project for Unit 0: Brute-Force Matrix Multiplication 
 * @author ccottap
 * @version 1.0
 *
 */
public class LabProject0 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-m|-r) <i>parameters</i>.  
	 * The options are:
	 * <ul>
	 * <li> -m <i>size</i>: Multiplies two random matices of the given size. </li>
	 * <li> -r <i>initsize scalefactor numsizes numtests</i>: Multiplies matrices of different sizes (starting at the initial size indicated,
	 * and increasing this size by a scale factor a number of times). For each size times are measured and dumped to a file for subsequent statistical analysis.</li>
	 * </ul>
	 * @throws IOException if data cannot be written to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestMatrixMultiplication.run(args);
	}

}
