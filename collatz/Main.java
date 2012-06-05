/*
 * Main
 * Copyright
 * v0.1
 * 25.02.2010
 */

/*
 * Main class
 */
public class Main {

	/*
	 * data
	 */
	private static long N;
	
	/*
	 * main method
	 */
	public static void main(String[] args) {
		
		N = (long)(Math.random() * Integer.MAX_VALUE); // long number
		System.out.println("Initial N: " + N);
		if (N < 100) { // first case
			conjecture(N, true); // call
		} else {
			long ini = System.currentTimeMillis(); // initial time
			int ite = conjecture(N, false); // call
			long fin = System.currentTimeMillis(); // final time
			
			System.out.println("Number of milliseconds: " + (fin - ini)); // write time
			System.out.println("Number of iterations: " + ite); // write iterations
		}
	}
	
	/*
	 * conjecture method
	 */
	public static int conjecture(long N, boolean write) {

		if (write == true) { // can write
			System.out.println(N); // write N
		}
		
		if (N <= 1) { // finished 
			return 1;
		} else if ((N & 1) == 1) { // odd
			return 1 + conjecture(N * 3 + 1, write);
		} else { // even
			return 1 + conjecture(N >> 1, write);
		}
	}
}