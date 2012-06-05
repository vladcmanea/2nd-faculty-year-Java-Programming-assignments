import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Explorer class
 */
public class Explorer {
	
	/**
	 * <b>Description</b>: explores directory recursively
	 * @param directory directory in File format
	 * @param regex regex filter in String format
	 */
	public static void exploreDirectory(File file, String regex) {
		/* explores directory */
		
		if (file.isDirectory() == true) {
			/* directory is directory */
			
			File files[] = file.listFiles(new ExplorerFilter(regex)); // get inner files
			for (int i = 0; i < files.length; ++i) {
				/* iterate files */
				exploreDirectory(files[i], regex); // explore recursively
			}
		} else if (file.isFile() == true) {
			/* directory is file */
			checkFile(file); // check file
		}
	}
	
	/**
	 * <b>Description</b>: checks file
	 * @param file to be checked in File format
	 */
	public static void checkFile(File file) {
		/* checks file */
		
		System.out.print("[" + file.getPath() + "] "); // print file name

		if(file.isFile() == false) {
			/* file is not file */
			System.out.println("ERROR: Directory"); // print error 
		} else {
			/* file is file */
			
			FileReader fileReader = null; // primitive stream
			BufferedReader bufferedReader = null; // processing stream
			Integer braces = 0; // paranthesis
			
			try {
				fileReader = new FileReader(file); // primitive stream
				bufferedReader = new BufferedReader(fileReader); // processing stream

				for (
						String line = bufferedReader.readLine(); 
						line != null; 
						line = bufferedReader.readLine()
				) {
					/* read line by line */
					
					for (int i = 0; i < line.length(); ++i) {
						/* iterate all characters */
				
						if (line.charAt(i) == '{') {
							/* open curly brace found */
							++braces; // increase braces
						} else if (line.charAt(i) == '}') {
							/* closed curly brace found */
							--braces; // decrease braces
						}
						
						if (braces < 0) {
							/* there are too many closed curly braces */
							throw new FileException(); // error
						}
					}
				}
	
				if (braces != 0) {
					/* there are too many/few open/closed curly braces */
					throw new FileException(); // error
				}
				System.out.println("OK: Paranthesis Closure");
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: Not Found");
			} catch (IOException e) {
				System.out.println("ERROR: IO Problem");
			} catch (FileException e) {
				System.out.println("ERROR: Paranthesis Closure");
			} finally {
				try {
					if (fileReader != null) {
						fileReader.close();
					} 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
