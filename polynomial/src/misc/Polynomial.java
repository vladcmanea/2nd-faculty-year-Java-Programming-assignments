package misc;
/**
 * Polynomial class
 * @author Vlad
 *
 */
public class Polynomial {
	
	// quotients
	float quotients[] = null;
	
	/**
	 * Polynomial method
	 * @param quotients
	 */
	public Polynomial(float quotients[]) {
		
		// initialize quotients
		this.quotients = new float[quotients.length];
		
		// iterate quotients
		for (int i = 0; i < quotients.length; ++i) {
			
			// copy quotient
			this.quotients[i] = quotients[i];
			
		}
		
	}
	
	/**
	 * get method
	 * @param x
	 * @return
	 */
	public float get(float x) {
		
		// initialize power
		float power = 1;
		
		// initialize sum
		float sum = 0;
		
		// iterate quotients
		for (int i = 0; i < quotients.length; ++i, power *= x) {
			
			// update sum
			sum += power * quotients[i];

		}
		
		// return answer
		return sum;
		
	}

}
