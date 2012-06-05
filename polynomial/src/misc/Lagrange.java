package misc;
/**
 * Lagrange class
 * @author Vlad Manea
 * @author Vlad Tudose
 */
public class Lagrange {
	
	// points
	private Point points[] = null;
	// W
	private float W[] = null;
	
	/**
	 * Lagrange method
	 * @param points
	 */
	public Lagrange(Point points[]) {
		
		// initialize points
		this.points = new Point[points.length];
		
		// iterate points
		for (int i = 0; i < points.length; ++i) {
			
			// copy point
			this.points[i] = new Point(points[i].getX(), points[i].getY());
		
		}
		
		// iterate points
		for (int i = 1; i < points.length; ++i) {
			
			// iterate points
			for (int j = 0; j < i; ++j) {
				
				// test equals
				if (this.points[i] == this.points[j]) {
					
					// message to output
					System.err.println("Two points have equal X");
					
					// exit application
					System.exit(0);
		
				}
			
			}
		
		}
		
		// create W
		W = new float[this.points.length];
		
		// iterate W
		for (int j = 0; j < this.points.length; ++j) {
			
			// initialize product
			W[j] = 1;
			
			// iterate j
			for (int i = 0; i < this.points.length; ++i) {
				
				// is i different to j?
				if (i != j) {
					
					// update product
					W[j] *= points[j].getX() - points[i].getX();
					
				}
				
			}
			
			// invert product
			W[j] = 1 / W[j];
			
		}
	
	}
	
	/**
	 * get method
	 * @param x
	 * @return
	 */
	public float get(float x) {
		
		// initialize first sum
		float sum1 = 0;
		
		// iterate j
		for (int j = 0; j < points.length; ++j) {
			
			// is x different to point?
			if (x != points[j].getX()) {
			
				// update first sum
				sum1 += W[j] * points[j].getY() / (x - points[j].getX());
			
			} else {
				
				// return position
				return points[j].getY();
				
			}
		
		}
		
		// initialize second sum
		float sum2 = 0;
		
		// iterate j
		for (int j = 0; j < points.length; ++j) {
		
			// is x different to point?
			if (x != points[j].getX()) {
			
				// update first sum
				sum2 += W[j] / (x - points[j].getX());
				
			} else {
				
				// return position
				return points[j].getY();
				
			}
			
		}
		
		// return answer
		return sum1 / sum2;

	}

}
