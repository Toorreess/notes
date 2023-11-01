import java.io.IOException;




/**
 * Lab Project for Unit 4: Dynamic Programming
 * @author ccottap
 * @version 1.0
 */
public class LabProject4 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-f|-t) <i>parameters</i> 
	 * The options are:
	 * <ul>
	 * <li> -f <i>markov-file seq-file</i>: Reads a Markov model and a sequence of observations
	 *     from two files and computes the maximum likelihood sequence of states. </li>
	 * <li> -t <i>num-outputs states-initial states-steps observation-initial observation-steps numtests</i> :
		Generates Markov models and sequences of observations of different sizes and measures the time required
		for computing the maximum likelihood sequence of states in each case.</li>
	 * </ul>
	 * @throws IOException if data cannot be read/written from/to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestMaxLikelihood.run(args);
	}

}
