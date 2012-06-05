/**
 * BigNumber
 * @version 0.1
 * @author Vlad Manea
 */

import java.util.ArrayList;
import java.util.List;

public class BigNumber {
	
	/**
	 * Data
	 */
	private List<Integer> list = new ArrayList<Integer>(); // list of digits
	int sign = 0; // sign {-1, 0, 1}
	
	/**
	 * Constructor Method
	 */
	public BigNumber() {}
	
	/**
	 * Constructor Method from string in String format
	 * @param string string to be converted in String format
	 * @see String
	 */
	public BigNumber(String string) {
		
		this.sign = 1; // set initial sign
		if ((string.length() > 0) && (string.charAt(0) == '-')) {
			/* minus sign exists */
			this.sign = -1; // sign is negative
		}

		// copy each character
		for (int i = string.length() - 1; i > 0; i--) {
			/* for each character */
			if (('0' <= string.charAt(i)) && (string.charAt(i) <= '9')) {
				/* string character is correct */
				this.list.add((Integer)(string.charAt(i) - '0'));
			}
		}
		
		// set character for first position
		if ((string.length() > 0) && ('1' <= string.charAt(0)) && (string.charAt(0) <= '9')) {
			/* string character is correct */
			this.list.add((string.charAt(0) - '0'));
		}

		// drop unnecessary zeroes
		for (int i = this.list.size() - 1; i >= 0; i--) {
			/* iterate through digits */
			if(this.list.get(i).equals(0)) {
				/* element is zero */
			 	this.list.remove(this.list.size() - 1); // remove it
			} else {
				/* element is not zero */
				break; // exit
			}
		}
		
		// set zero
		if (this.list.size() == 0) {
			/* list is zero */
			this.sign = 0; // set sign to zero
		}
	}
	
	/**
	 * Constructor Method from bytes in Byte[] format
	 * @param sign -1 for a < b, 0 for a = b, 1 for a > b in Integer format 
	 * @param bytes bytes to be converted in Byte format
	 * @see Byte 
	 */
	public BigNumber(int sign, Integer bytes[]) {

		// set sign
		if ((sign == 0) || (sign == -1) || (sign == 1)) {
			/* sign is correct */
			this.sign = sign;
		}
		
		if (sign != 0) {
			// copy for each byte
			for (int i = bytes.length - 1; i >= 0; i--) {
				/* for each byte */
				if ((0 <= bytes[i]) && (bytes[i] <= 9)) {
					/* byte is correct */
					this.list.add(bytes[i]);
				}
			}
	
			// drop unnecessary zeroes
			for (int i = this.list.size() - 1; i >= 0; i--) {
				/* iterate through digits */
				if(this.list.get(i).equals(0)) {
					/* element is zero */
				 	this.list.remove(this.list.size() - 1); // remove it
				} else {
					/* element is not zero */
					break; // exit
				}
			}
			
			// set zero
			if (this.list.size() == 0) {
				/* list is zero */
				sign = 0; // set sign to zero
			}
		}
	}
	
	/**
	 * Copy Method
	 * @param number number to be copied in BigNumber format
	 * @return number instance of number copied in BigNumber format
	 */
	public static BigNumber equalTo(BigNumber number) {
		
		BigNumber ans = new BigNumber(); // answer
		ans.sign = number.sign; // sign
		
		// iterate digits
		for (int i = 0; i < number.list.size(); ++i) {
			/* iterate all digits */
			ans.list.add(number.list.get(i));
		}
		
		// return
		return ans; // answer
	}
	
	/**
	 * Add Method
	 * @param a first number to be added in BigNumber format
	 * @param b second number to be added in BigNumber format
	 * @return number sum in BigNumber format 
	 */
	public static BigNumber add(BigNumber a, BigNumber b) {
		
		if (a.sign == 0) {
			/* a is zero */
			return BigNumber.equalTo(b); // answer
		} else if (b.sign == 0) {
			/* b is zero */
			return BigNumber.equalTo(a); // answer
		} else if ((a.sign < 0) && (b.sign > 0)) {
			/* a is the small */
			a.sign = 1; // inverse
			b.sign = -1; // inverse
			BigNumber ans = BigNumber.add(a, b); // answer
			ans.sign = -ans.sign; // inverse
			a.sign = -1; // inverse
			b.sign = 1; // inverse
			return ans; // answer
		} else if ((a.sign > 0) && (b.sign < 0)) {
			/* b is the small */
			int result = BigNumber.compare(b, a, false); // result 
			if (result < 0) { 
				/* b is smaller than a */
				b.sign = 1; // inverse
				BigNumber ans = BigNumber.subtract(a, b); // answer
				b.sign = -1; // inverse
				return ans; // answer
			} else if (result > 0) {
				/* b is larger than a */
				b.sign = 1; // inverse
				BigNumber ans = BigNumber.subtract(b, a); // answer
				ans.sign = -1; // inverse
				b.sign = -1; // inverse
				return ans; // answer
			} else {
				/* numbers are equal */
				return BigNumber.Const0();
			}
		} else {
			/* both have the same sign */
			BigNumber ans = new BigNumber(); // the answer
			int transport = 0; // transport
			
			// iterate
			for (int i = 0; (i < a.list.size()) || (i < b.list.size()) || (transport > 0); ++i) {
				/* iterate through digits */
				transport += (i < a.list.size()? a.list.get(i): 0); // add from a
				transport += (i < b.list.size()? b.list.get(i): 0); // add from b
				ans.list.add(transport % 10); // add remainder
				transport /= 10; // divide transport
			}
			
			ans.sign = a.sign; // sign
			return ans; // answer
		}
	}

	/**
	 * Subtract Method
	 * @param a number to be subtracted from in BigNumber format
	 * @param b number to be subtracted in BigNumber format
	 * @return number difference in BigNumber format 
	 */
	public static BigNumber subtract(BigNumber a, BigNumber b) {
		
		if (a.sign == 0) {
			/* a is zero */
			BigNumber ans = b; // answer
			ans.sign = -b.sign; // inverse
			return ans; // answer
		} else if (b.sign == 0) {
			/* b is zero */
			return BigNumber.equalTo(a);
		} else if ((a.sign < 0) && (b.sign > 0)) {
			/* a is smaller, b is larger than 0 */
			a.sign = 1; // inverse
			BigNumber ans = BigNumber.add(a, b); // answer
			ans.sign = -1; // inverse
			a.sign = -1; // inverse
			return ans; // answer
		} else if ((a.sign > 0) && (b.sign < 0)) {
			/* a is larger than 0, b is smaller than 8 */
			b.sign = 1; // inverse
			BigNumber ans = BigNumber.add(a, b); // answer
			b.sign = -1; // inverse
			return ans; // answer
		} else {
			/* both have the same sign */
			int result = BigNumber.compare(a, b, false); // compare
			if (result < 0) {
				/* a is smaller than b */
				if (a.sign > 0) {
					/* a is larger than 0 */
					BigNumber ans = BigNumber.subtract(b, a); // answer
					ans.sign = -1; // negative
					return ans; // ans
				} else {
					/* a is smaller than 0 */
					b.sign = 1; // inverse
					BigNumber ans = BigNumber.add(b, a); // answer
					b.sign = -1; // inverse
					return ans; // ans
				}
			} else if (result > 0) {
				/* a is larger than b */
				if (a.sign > 0) {
					/* a is larger than 0 */
					// iterate elements
					BigNumber ans = new BigNumber(); // answer
					ans.sign = 1; // sign
					int transport = 0; // transport
					
					// iterate
					for (int i = 0; i < a.list.size(); ++i) {
						/* iterate element */
						ans.list.add(0); // add zero
						if (i < b.list.size()) {
							/* must add to transport */
							transport += b.list.get(i);
						}
						if (a.list.get(i) >= transport) {
							/* can subtract normally */
							ans.list.set(i, a.list.get(i) - transport);
							transport = 0; // transpoert
						} else {
							/* cannot subtract normally */
							ans.list.set(i, 10 + a.list.get(i) - transport);
							transport = 1;
						}
					}
					
					// drop unnecessary zeroes
					for (int i = ans.list.size() - 1; i >= 0; i--) {
						/* iterate through digits */
						if(ans.list.get(i).equals(0)) {
							/* element is zero */
						 	ans.list.remove(ans.list.size() - 1); // remove it
						} else {
							/* element is not zero */
							break; // exit
						}
					}
					
					// return
					return ans;
				} else {
					/* a is smaller than 0 */
					a.sign = 1; // inverse
					b.sign = 1; // inverse
					BigNumber ans = BigNumber.subtract(a, b); // answer
					ans.sign = -1; // inverse
					a.sign = -1; // inverse
					b.sign = -1; // inverse
					return ans;
				}
			} else {
				/* a is equal to b */
				return BigNumber.Const0(); // answer
			}
		}
	}
	
	/**
	 * Multiply Method
	 * @param a number to be multiplied in BigNumber format
	 * @param b number to be multiplied in BigNumber format
	 * @return number product in BigNumber format 
	 */
	public static BigNumber multiply(BigNumber a, BigNumber b) {
		
		if ((a.sign == 0) || (b.sign == 0)) {
			/* one element is zero */
			return BigNumber.Const0(); // answer
		} else if (a.sign < b.sign) {
			/* a is negative */
			a.sign = 1; // inverse
			BigNumber ans = BigNumber.multiply(a, b); // answer
			ans.sign = -1; // inverse
			a.sign = -1; // inverse
			return ans; // answer
		} else if (a.sign > b.sign) {
			/* b is negative */
			b.sign = 1; // inverse
			BigNumber ans = BigNumber.multiply(a, b); // answer
			ans.sign = -1; // inverse
			b.sign = -1; // inverse
			return ans; // answer 
		} else if (a.sign < 0) {
			/* they are equal to -1 */
			a.sign = 1; // inverse
			b.sign = 1; // inverse
			BigNumber ans = BigNumber.multiply(a, b); // answer
			a.sign = -1; // inverse
			b.sign = -1; // inverse
			return ans; // answer
		} else {
			/* both are positive, must multiply normally */
			BigNumber ans = new BigNumber(); // ans
			ans.sign = 1;
			int i, j, transport = a.list.size() + b.list.size(); // transport

			// initialize
			for (i = 0; i <= transport; ++i) {
				/* iterate elements */
				ans.list.add(0); // add 0
			}
			
			// iterate
			for (i = 0; i < a.list.size(); ++i) {
				/* iterate all digits in a */
				for (j = 0; j < b.list.size(); ++j) {
					/* iterate all digits in b */
					ans.list.set(i + j, ans.list.get(i + j) + a.list.get(i) * b.list.get(j)); // trans
				}
			}
			
			// normalize
			for (i = 0, transport = 0; i < ans.list.size(); ++i, transport /= 10) {
				/* iterate all */
				transport += ans.list.get(i);
				ans.list.set(i, transport % 10);
			}
			
			// normalize
			for (i = ans.list.size(); transport > 0; ++i, transport /= 10) {
				ans.list.add(transport % 10);
			}
			
			// drop unnecessary zeroes
			for (i = ans.list.size() - 1; i >= 0; i--) {
				/* iterate through digits */
				if(ans.list.get(i).equals(0)) {
					/* element is zero */
				 	ans.list.remove(ans.list.size() - 1); // remove it
				} else {
					/* element is not zero */
					break; // exit
				}
			}
			
			// return
			return ans; // answer
		}
	}

	/**
	 * Power Method
	 * @param a number to be powered in BigNumber format
	 * @param b number to be powered at in long format
	 * @return number power in BigNumber format
	 * @see Long 
	 */
	public static BigNumber power(BigNumber a, Long b) {
		
		if (b < 0) {
			/* exponent is wrong */
			return BigNumber.Const0(); // answer
		} else if (b == 0) {
			/* exponent is zero */
			return BigNumber.Const1(); // answer
		} else if (b == 1) {
			/* exponent is one */
			return BigNumber.equalTo(a); // answer
		} else if (b % 2 == 0) {
			/* b is even */
			BigNumber aux = BigNumber.power(a, b / 2); // square root
			return BigNumber.multiply(aux, aux); // squared
		} else {
			/* b is odd */
			BigNumber aux = BigNumber.power(a, b / 2); // square root
			return BigNumber.multiply(a, power(a, b - 1)); // squared
		}
	}
	
	/**
	 * Conversion Method to Long
	 * @return string in StringBuilder format
	 * @see String 
	 */
	public StringBuilder convertToString() {
		
		StringBuilder string = new StringBuilder("");
		if (this.sign < 0) {
			/* set minus */
			string.append('-');
		}
		
		// iterate
		for (int i = this.list.size() - 1; i >= 0; i--) {
			/* iterate all digits */
			string.append((char)(this.list.get(i) + '0'));
		}
		
		if (this.list.size() == 0) {
			/* zero case */
			string.append('0');
		}
		
		// return
		return string; // answer
	}
	
	/**
	 * Conversion Method to Long
	 * if long is overflown, the returned Long value is 0
	 * @return long in long format
	 * @see String 
	 */
	public Long convertToLong() {
		
		if (this.sign > 0) {
			/* this is positive */
			if (BigNumber.compare(this, 
					BigNumber.power(new BigNumber("2"), new Long(63)), false) < 0) {
				/* it's ok to convert */
				long x = 0, p = 1; // answer
				
				// iterate
				for (int i = 0; i < this.list.size(); ++i, p *= 10) {
					/* iterate through all digits */
					x += p * this.list.get(i);
				}

				// return 
				return new Long(x); // answer
			} else {
				/* no conversion possible */
				return new Long(0);
			}
		} else if (this.sign < 0) {
			/* this is negative */
			if (BigNumber.compare(this, 
					BigNumber.power(new BigNumber("2"), new Long(63)), false) <= 0) {
				/* it's ok to convert */
				long x = 0, p = 1; // answer
				
				// iterate
				for (int i = 0; i < this.list.size(); ++i, p *= 10) {
					/* iterate through all digits */
					x -= p * this.list.get(i);
				}

				// return 
				return new Long(x); // answer
			} else {
				/* no conversion possible */
				return new Long(0);
			}
		} else {
			/* this is zero */
			return new Long(0); // anwer
		}
	}
	
	/**
	 * Equality Method
	 * @param obj number to be compared to in Object format
	 * @return true or false in boolean format  
	 */
	public boolean equals(Object obj) {
		
		if (obj.getClass() != this.getClass()) {
			/* classes are different */
			return false;
		} else {
			BigNumber aux = (BigNumber)obj; // compared number
			/* classes are equal */
			if (this.list.size() != aux.list.size()) {
				/* lengths are different */
				return false;
			} else if (this.sign != aux.sign) {
				/* signs are different */
				return false;
			} else {
				/* signs are ok */
				
				// iterate all digits
				for (int i = this.list.size() - 1; i >= 0; i--) {
					/* iterate all digits */
					if (this.list.get(i) != aux.list.get(i)) {
						/* different digit */
						return false;
					}
				}
				
				// equal
				return true;
			}
		}
	}
	
	/**
	 * Inequality Method
	 * @param a first number to be compared in BigNumber format
	 * @param b second number to be compared in BigNumber format
	 * @param sign if sign is true, sign will be taken into account; else, not
	 * @return -1 for a < b, 0 for a = b, 1 for a > b in int format
	 */
	public static int compare(BigNumber a, BigNumber b, boolean sign) {
		
		if (sign == true) {
			/* sign matters */
			if (a.sign < b.sign) {
				/* a is definetly smaller */
				return -1;
			} else if (a.sign > b.sign) {
				/* a is definetly larger */
				return 1;
			} else {
				/* a and b have same sign */
				if (a.sign < 0) {
					/* a and b are negative */
					a.sign = 1; // inverse
					b.sign = 1; // inverse
					int result = -compare(a, b, false); // answer
					a.sign = -1; // inverse
					b.sign = -1; // inverse
					return result; // answer
				} else if (a.sign > 0) {
					/* a and b are positive */
					if (a.list.size() < b.list.size()) {
						/* a is smaller than b */
						return -1;
					} else if (a.list.size() > b.list.size()) {
						/* a is larger than b */
						return 1;
					} else {
						/* a and b have the same length */
						
						// iterate all digits
						for (int i = a.list.size() - 1; i >= 0; i--) {
							/* iterate all digits */
							if (a.list.get(i) < b.list.get(i)) {
								/* a is smaller than b */
								return -1;
							} else if (a.list.get(i) > b.list.get(i)) {
								/* a is larger than b */
								return 1;
							} 
						}
						
						// equal
						return 0;
					}
				} else {
					/* a and b are 0 */
					return 0;
				}
			}
		} else {
			/* sign does not matter */
			if (a.list.size() < b.list.size()) {
				/* a is smaller than b */
				return -1;
			} else if (a.list.size() > b.list.size()) {
				/* a is larger than b */
				return 1;
			} else {
				/* a and b have the same length */
				
				// iterate all digits
				for (int i = a.list.size() - 1; i >= 0; i--) {
					/* iterate all digits */
					if (a.list.get(i) < b.list.get(i)) {
						/* a is smaller than b */
						return -1;
					} else if (a.list.get(i) > b.list.get(i)) {
						/* a is larger than b */
						return 1;
					} 
				}
				
				// equal
				return 0;
			}
		}
	}
	
	/**
	 * 0 (zero) Constant Method 
	 * @return 0 in BigNumber format 
	 */
	public static BigNumber Const0() {
		
		BigNumber ans = new BigNumber(); // answer
		ans.sign = 0; // set sign
		return ans; // answer
	}
	
	/**
	 * 1 (one) Constant Method 
	 * @return 1 in BigNumber format 
	 */
	public static BigNumber Const1() {

		BigNumber ans = new BigNumber(); // answer
		ans.sign = 1; // set sign
		ans.list.add(1); // set 1
		return ans; // answer
	}

	/**
	 * 10 (ten) Constant Method 
	 * @return 10 in BigNumber format 
	 */
	public static BigNumber Const10() {

		BigNumber ans = new BigNumber(); // answer
		ans.sign = 1; // set sign
		ans.list.add(0); // set 0
		ans.list.add(1); // set 1
		return ans; // answer
	}
	
	/**
	 * 100 (hundred) Constant Method 
	 * @return 100 in BigNumber format 
	 */
	public static BigNumber Const100() {

		BigNumber ans = new BigNumber(); // answer
		ans.sign = 1; // set sign
		ans.list.add(0); // set 0
		ans.list.add(0); // set 0
		ans.list.add(1); // set 1
		return ans; // answer
	}
	
	/**
	 * 1000 (thounsand) Constant Method 
	 * @return 1000 in BigNumber format 
	 */
	public static BigNumber Const1000() {

		BigNumber ans = new BigNumber(); // answer
		ans.sign = 1; // set sign
		ans.list.add(0); // set 0
		ans.list.add(0); // set 0
		ans.list.add(0); // set 0 
		ans.list.add(1); // set 1
		return ans; // answer
	}
}
