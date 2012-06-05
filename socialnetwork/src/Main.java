import java.util.Iterator;
import java.util.Map;

public class Main {

	/**
	 * @param args arguments
	 */
	public static void main(String[] args) {
		
		Person P1 = new Person();
		P1.setFirstName("Andrei");
		P1.setLastName("Albesteanu");
		
		Person P2 = new Person();
		P2.setFirstName("Codrin");
		P2.setLastName("Ditu");
		
		Person P3 = new Person();
		P3.setFirstName("Codrut");
		P3.setLastName("Burdujoc");
		
		Person P4 = new Person();
		P4.setFirstName("Vlad");
		P4.setLastName("Tudose");
		
		Person P5 = new Person();
		P5.setFirstName("Vlad");
		P5.setLastName("Manea");
		
		Network N = new Network();
		
		N.addPerson("deyinn", P1);
		N.addPerson("kodranu_15", P2);
		N.addPerson("codabur", P3);
		N.addPerson("tva", P4);
		N.addPerson("vma", P5);
		
		N.addFriendToPerson("deyinn", "vma", 1);
		N.addFriendToPerson("codabur", "deyinn", 2);
		N.addFriendToPerson("codabur", "kodranu_15", 3);
		N.addFriendToPerson("codabur", "vma", 4);
		N.addFriendToPerson("vma", "codabur", 5);
		N.addFriendToPerson("tva", "kodranu_15", 6);
		N.addFriendToPerson("vma", "kodranu_15", 7);
		
		System.out.println(N.toString());
		System.out.println(N.getFriendship("codabur"));
		System.out.println(N.getPopularity("kodranu_15"));
		System.out.println(N.getPopularity("codabur"));
		
		System.out.println(N.getDistance("vma", "kodranu_15"));
		
		Map<String, Integer> friendsOfVma = N.getFriendsAtDistance("vma", 8);
		Iterator<Map.Entry<String, Integer>> jt = friendsOfVma.entrySet().iterator(); 
		while (jt.hasNext()) {
			Map.Entry<String, Integer> next = jt.next();
			System.out.println(next.getKey() + " " + next.getValue());
		}
		
		N.removePerson("vma");
		
		System.out.println(N.toString());
		System.out.println(N.getFriendship("codabur"));
		System.out.println(N.getPopularity("kodranu_15"));
		System.out.println(N.getPopularity("codabur"));
		
		/*
		System.out.println(P1.getDistance(P4));
		System.out.println(P2.getDistance(P2));
		System.out.println(P4.getDistance(P3));
		Map<Person, Integer> friendsOfP1 = P1.getFOAFAtDistance(9);
		Iterator<Map.Entry<Person, Integer>> jt = friendsOfP1.entrySet().iterator(); 
		while (jt.hasNext()) {
			Map.Entry<Person, Integer> next = jt.next();
			System.out.println(next.getKey().getName() + " " + next.getValue());
		}
		*/
	}
}
