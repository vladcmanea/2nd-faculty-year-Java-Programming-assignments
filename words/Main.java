/*
 * Main
 * Copyright
 * 25.02.2010
 * v0.1
 * 
 * TIME COMPLEXITY
 * initial complexity per character added: O(|dictionary| * average(dictionary word))
 * final complexity per character added: O(log2(|dictionary|))
 */
import java.io.*;
public class Main {
	/*
	 * variables
	 */
	private static final String dictionary[] = {"abcd", "abc", "abdabc", "c", "f",
		"adb", "addab", "addd", "bcd", "bdabc", "dabc", "dbf", "ddfa", "efff"}; // words
	private static final String alphabet = "abcdef"; // alphabet

	private static int left[]; // left boundary for each student
	private static int rite[]; // rite boundary for each student
	private static boolean failed[]; // failed for each student
	private static StringBuilder letters[]; // letters for each student
	private static int N; // number of students
	/*
	 * main method
	 */
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in); // create input reader
		BufferedReader stdin = new BufferedReader(isr); // create buffer reader
		try { 
			/* error prone */
			System.out.println("Insert number of students: "); // write number of students
			String n = stdin.readLine(); // read n
			try {
				N = Integer.parseInt(n); // parse N
				if (N <= 0) { 
					/* invalid */
				} else { 
					/* N is ok */
					left = new int[N]; // alloc left
					rite = new int[N]; // alloc right
					failed = new boolean[N]; // alloc fail
					letters = new StringBuilder[N]; // alloc letters
					for (int i = 0; i < N; ++i) {
						/* iterate through students */
						left[i] = 0; // set zero
						rite[i] = dictionary.length - 1; // set maximum
						failed[i] = false; // set failed
						letters[i] = new StringBuilder(""); // set letters
					}
					int fail = 0;
					while (fail < N) { 
						/* read while it's ok */
						for (int s = 0; s < N; ++s) {
							if (failed[s] == false) {
								/* student can win */
								char c = alphabet.charAt((int)(alphabet.length() * Math.random()));
								letters[s].append(c);
								System.out.println("Student " + (s + 1) + " has word " 
										+ letters[s] + " by adding character " + c); // write

								int lpos = lsearch(c, letters[s].length(), left[s], rite[s]); // binary search
								int rpos = rsearch(c, letters[s].length(), left[s], rite[s]); // binary search
								left[s] = lpos; // left position update
								rite[s] = rpos; // rite position update

								if (0 <= lpos && lpos < dictionary.length &&
										0 <= rpos && rpos < dictionary.length) {
									/* lpos and rpos are valid */
									if (dictionary[lpos].length() == letters[s].length()) {
										System.out.println("Winner is student " + (s + 1) 
											+ " with word " + dictionary[lpos]); // write
										return; // done
									}
								}

								if ((left[s] >= dictionary.length) || (rite[s] < 0)) {
									/* one more failed */
									++fail; // count failed
									failed[s] = true;
								}
							}
						}
					}
					System.out.println("Everybody loses"); // write
				}
			} catch(NumberFormatException e) {
				// do nothing
			}
		} catch(IOException e) {
			// do nothing
		}		
	}
	/*
	 * left binary search string
	 */
	private static int lsearch(char c, int pos, int left, int rite) {
		if (left < 0) {
			/* invalid */
			return dictionary.length; // wrong
		} else if (rite < 0) {
			/* invalid */
			return dictionary.length; // wrong
		} else if (left > rite){
			/* invalid */
			return dictionary.length; // wrong
		} else {
			/* valid */
			int mid = (left + rite) >> 1; // compute middle
			if (dictionary[mid].length() >= pos) {
				/* large enough */
				if (dictionary[mid].charAt(pos - 1) < c) {
					/* too small, look in the right */
					return lsearch(c, pos, mid + 1, rite); // return recursively
				} else if (dictionary[mid].charAt(pos - 1) > c) {
					/* too big, look in the left */
					return lsearch(c, pos, left, mid - 1);
				} else {
					/* equal, can go leftwards */
					return Math.min(mid, lsearch(c, pos, left, mid - 1));
				}
			} else {
				/* not large enough */
				return lsearch(c, pos, mid + 1, rite); // wrong
			}
		}
	}
	/*
	 * rite binary search string
	 */
	private static int rsearch(char c, int pos, int left, int rite) {
		if (left < 0) {
			/* invalid */
			return -1; // wrong
		} else if (rite < 0) {
			/* invalid */
			return -1; // wrong
		} else if (left > rite){
			/* invalid */
			return -1; // wrong
		} else {
			/* valid */
			int mid = (left + rite) >> 1; // compute middle
			if (dictionary[mid].length() >= pos) {
				/* large enough */
				if (dictionary[mid].charAt(pos - 1) < c) {
					/* too small, look in the right */
					return rsearch(c, pos, mid + 1, rite); // return recursively
				} else if (dictionary[mid].charAt(pos - 1) > c) {
					/* too big, look in the left */
					return rsearch(c, pos, left, mid - 1);
				} else {
					/* equal, can go ritewards */
					return Math.max(mid, rsearch(c, pos, mid + 1, rite));
				}
			} else {
				/* not large enough */
				return rsearch(c, pos, mid + 1, rite); // return recursively
			}
		}
	}
}
