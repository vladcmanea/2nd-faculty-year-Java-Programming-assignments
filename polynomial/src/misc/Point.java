package misc;
/**
 * Point class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class Point {
	
	// coordinates
	private float x, y;
	
	/**
	 * Point method
	 * @param x
	 * @param y
	 */
	public Point(float x, float y) {
		
		// copy x
		this.x = x;
		
		// copy y
		this.y = y;
	
	}

	/**
	 * getX method
	 * @return
	 */
	public float getX() {
		
		// return x
		return x;
		
	}

	/**
	 * setX method
	 * @param x
	 */
	public void setX(float x) {
		
		// copy x
		this.x = x;
		
	}

	/**
	 * getY method
	 * @return
	 */
	public float getY() {
		
		// return y
		return y;
		
	}

	/**
	 * setY method
	 * @param y
	 */
	public void setY(float y) {
		
		// copy y
		this.y = y;
	
	}

}
