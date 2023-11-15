import java.io.IOException;





/**
 * Lab Project for Unit 5: Greedy Algorithms
 * @author ccottap
 * @version 1.0
 */
public class LabProject5 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-f|-t) <i>parameters</i> 
	 * The options are:
	 * <ul>
	 * <li> -f <i>algorithm filename</i>: Runs a certain algorithm (allowed options are "naive" and "bidirectional")
	 * on a data instance read from a file. </li>
	 * <li> -t <i>algorithm seq0 seqi seqs read0 readn numtests</i> :
		Tries an algorithm on data instances of increasing size (sequence length starting at seq0, scaled up by a factor seqi
		in each iteration for seqs iterations; read length ranging from read0 to readn). For each size, numtests
		instances are generated and both the time taken by the algorithm and the features of the reconstructed sequence
		are measured. These data are then dumped to a file for subsequent analysis.</li>
	 * </ul>
	 * @throws IOException if data cannot be read/written from/to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestDNASequencing.run(args);
	}

}
