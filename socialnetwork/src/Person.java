/**
 * Person
 * 
 * @author Vlad Tudose
 * @author Vlad Manea
 * @version 0.1
 */

/**
 * Person
 */
public class Person {
	
	/**
	 * Data
	 */
	private String firstName = ""; // first name
	private String lastName = ""; // last name

	/** 
	 * First Name Get Method
	 * used publicly  to get first name of person 
	 * @return first name of person
	 * @see String
	 */
	public String getFirstName() {
		
		return firstName; // get first name
	}
	
	/** 
	 * First Name Set Method
	 * used publicly to set first name of person
	 * @param firstName first name of person in String format
	 * @see String
	 */
	public void setFirstName(String firstName) {
		
		this.firstName = firstName; // set first name 
	}

	/** 
	 * Last Name Get Method
	 * used publicly  to get last name of person 
	 * @return last name of person
	 * @see String
	 */
	public String getLastName() {
		
		return lastName; // get last name
	}
	
	/** 
	 * Last Name Set Method
	 * used publicly to set last name of person
	 * @param lastName last name of person in String format
	 * @see String
	 */
	public void setLastName(String lastName) {
		
		this.lastName = lastName; // set last name 
	}
}
