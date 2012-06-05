/**
 * Main
 * @version 0.1
 * @author Vlad Manea
 */

public class Main {

	/**
	 * @param args arguments
	 */
	public static void main(String[] args) {
		
		BigNumber P = BigNumber.add(new BigNumber("0"), new BigNumber("0"));
		System.out.println(P.convertToString());

		P = BigNumber.add(new BigNumber("0"), new BigNumber("555"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("0"), new BigNumber("-555"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("567"), new BigNumber("0"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("-567"), new BigNumber("0"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("-563"), new BigNumber("1020"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("-635"), new BigNumber("635"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("-5630"), new BigNumber("1096"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("537"), new BigNumber("-1027"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("587"), new BigNumber("-587"));
		System.out.println(P.convertToString());

		P = BigNumber.add(new BigNumber("1579"), new BigNumber("-999"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("-156"), new BigNumber("-1560"));
		System.out.println(P.convertToString());
		
		P = BigNumber.add(new BigNumber("156"), new BigNumber("1569"));
		System.out.println(P.convertToString());
		
		
		P = BigNumber.subtract(new BigNumber("0"), new BigNumber("0"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("0"), new BigNumber("5"));
		System.out.println(P.convertToString());

		P = BigNumber.subtract(new BigNumber("0"), new BigNumber("-5"));
		System.out.println(P.convertToString());

		P = BigNumber.subtract(new BigNumber("55506786887866766767876786786786789"), new BigNumber("0"));
		System.out.println(P.convertToString());

		P = BigNumber.subtract(new BigNumber("-7954545445343"), new BigNumber("0"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("-790"), new BigNumber("350"));
		System.out.println(P.convertToString());

		P = BigNumber.subtract(new BigNumber("795"), new BigNumber("-763"));
		System.out.println(P.convertToString());

		P = BigNumber.subtract(new BigNumber("-96"), new BigNumber("-198"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("-963"), new BigNumber("-963"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("-9531"), new BigNumber("-1"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("73"), new BigNumber("196"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("25"), new BigNumber("25"));
		System.out.println(P.convertToString());
		
		P = BigNumber.subtract(new BigNumber("27963"), new BigNumber("10"));
		System.out.println(P.convertToString());
		
		P = BigNumber.multiply(new BigNumber("0"), new BigNumber("10"));
		System.out.println(P.convertToString());
		
		P = BigNumber.multiply(new BigNumber("12345"), BigNumber.Const100());
		System.out.println(P.convertToString());
		
		P = BigNumber.multiply(new BigNumber("27963"), new BigNumber("-100"));
		System.out.println(P.convertToString());

		P = BigNumber.multiply(new BigNumber("-27963"), new BigNumber("-100"));
		System.out.println(P.convertToString());

		P = BigNumber.multiply(new BigNumber("-999999999999999999"), new BigNumber("999999"));
		System.out.println(P.convertToString());

		P = BigNumber.multiply(new BigNumber("123456789"), new BigNumber("-123456789012345"));
		System.out.println(P.convertToString());

		P = BigNumber.power(new BigNumber("123456"), new Long(10));
		System.out.println(P.convertToString());
		
		P = new BigNumber("123456789");
		Long L = P.convertToLong();
		System.out.println(L);

		P = new BigNumber("-1234567891234567");
		L = P.convertToLong();
		System.out.println(L);
	
		P = new BigNumber("-123456789123456789123456789");
		L = P.convertToLong();
		System.out.println(L);
	}
}
