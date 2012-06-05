public class Main {

	public static void main(String[] args) {
		
		Friend f1 = new Friend();
		f1.setName("Sebastian Codrin Ditu");
		f1.setAddress("Iasi Targusor C3");
		//f1.setPhone("0740123456");
		f1.setWeb("http://students.info.uaic.ro/~sebastian.ditu");
		f1.setEmail("sebastian.ditu@info.uaic.ro");
		f1.setDate(15, 9, 1989);
		
		Friend f2 = new Friend();
		f2.setAddress("Iasi Valea Lupului");
		f2.setPhone("0740123478");

		Friend f3 = new Friend();
		f3.setName("Vlad Manea");
		f3.setAddress("Iasi Vasile Lupu");
		f3.setPhone("0740123456");
		f3.setWeb("http://students.info.uaic.ro/~vlad.manea");
		f3.setEmail("vlad.manea@info.uaic.ro");
		f3.setDate(10, 8, 1989);

		Friend f4 = new Friend();
		f4.setName("Codrut Andrei Burdujoc");
		f4.setAddress("Iasi Elena Doamna");
		f4.setPhone("0740458456");
		f4.setWeb("http://students.info.uaic.ro/~codrut.burdujoc");
		f4.setEmail("codrut.burdujoc@info.uaic.ro");
		f4.setDate(5, 3, 1989);

		Friend f5 = new Friend();
		f5.setName("Vlad Andrei Tudose");
		f5.setPhone("0772458456");
		f5.setWeb("http://students.info.uaic.ro/~vlad.tudose");
		f5.setDate(15, 4, 1989);
		
		Friend f6 = new Friend();
		f6.setName("Mihai Deaconu");
		f6.setAddress("Neamt Location");
		f6.setPhone("0792454516");
		f6.setWeb("http://students.info.uaic.ro/~mihai.deaconu");
		f6.setDate(15, 6, 1989);
		
		Friend f7 = new Friend();
		f7.setPhone("0772458676");
		f7.setWeb("http://students.info.uaic.ro/~radu.gagos");
		f7.setDate(15, 3, 1989);
		
		Friend f8 = new Friend();
		f8.setName("Mihai Valache");
		f8.setDate(11, 10, 1989);
		
		Agenda a = new Agenda();
		//a.read("serial.txt");
		a.addFriend(f1);
		a.addFriend(f2);
		a.addFriend(f3);
		a.addFriend(f4);
		a.addFriend(f5);
		a.addFriend(f6);
		a.addFriend(f7);
		a.addFriend(f8);
		//a.write("serial.txt");
		
		a.createReport(new HTMLReport("friends.html.template", "friends.html"));
		a.createReport(new PDFReport("friends.pdf.template", "friends.pdf"));
		a.createReport(new HTMLReport("friends.xml.template", "friends.xml"));
	}
}
