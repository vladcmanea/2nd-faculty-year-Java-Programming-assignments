import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		File file = new File("examples");
		Explorer.exploreDirectory(file, ".+\\.java");
	}
}
