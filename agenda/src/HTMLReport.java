import java.io.*;
import java.util.*;

import org.apache.velocity.*;
import org.apache.velocity.app.*;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: HTMLReport Class
 */
public class HTMLReport implements Report {

	/*
	 * Data
	 */
	String file;
	String layout;
	
	/**
	 * <b>Description</b>: Constructs a HTMLReport object
	 * @param layout layout from which report is read
	 * @param file file to which report is written
	 */
	public HTMLReport(String layout, String file) {
		this.file = file;
		this.layout = layout;
	}
	
	/**
	 * <b>Description</b>: Creates a report
	 * @param friends set of friends
	 */
	public void create(Set<Friend> friends) {

		FileWriter fWriter = null; // primitive stream
		BufferedWriter out = null; // processing stream
		
        try {

    		fWriter = new FileWriter(file); // primitive stream
    		out = new BufferedWriter(fWriter); // processing stream
        	
    		VelocityEngine velocityEngine = new VelocityEngine(); // create engine 
        	velocityEngine.init(); // initialize engine

        	/* create and initialize map according to sign */
    		Map<Integer, HashSet<Friend>> reported = new HashMap<Integer, HashSet<Friend>>();
    		for (int i = 1; i <= 12; ++i) {
    			/* iterate signs */
    			reported.put(i, new HashSet<Friend>()); // initialize
    		}

    		/* add each friend to its sign */
    		Iterator<Friend> it = friends.iterator();
    		while (it.hasNext()) {
    			/* iterate friends */
    			Friend friend = it.next(); // get friend
    			reported.get(Agenda.getSign(friend.getDay(), friend.getMonth())).add(friend); // add friend
    		}

    		VelocityContext context = new VelocityContext(); // create context
            Template template = velocityEngine.getTemplate(layout); // create template
            StringWriter writer = new StringWriter(); // primitive stream

            int k = 0;
	        for (Integer i = 1; i <= 12; ++i) {
	        	/* iterate signs */
	        	
    			Set<Friend> signedFriends = reported.get(i); // get friends of sign
        		List<Map<String, String>> friendsList = new ArrayList<Map<String, String>>(); // create list
        		
        		/* put each friend as map in list for report */ 
        		Iterator<Friend> kt = signedFriends.iterator(); 
        		while (kt.hasNext()) {
        			/* iterate friends of sign */
        			
        			Friend signedFriend = kt.next(); // get friend
       				Map<String, String> entry = new HashMap<String, String>(); // create map
       				
       				String name = signedFriend.getName();
       				if (name == "") {
       					name = "Unnamed Friend <span class=\"gray\">No. " + (++k) + "</span>";
       				}
       				
       				entry.put("name", name); // put name
       				entry.put("address", signedFriend.getAddress()); // put address
       				entry.put("phone", signedFriend.getPhone()); // put phone
       				entry.put("web", signedFriend.getWeb()); // put web
       				entry.put("email", signedFriend.getEmail()); // put email
       				//entry.put("sign", signedFriend.getSign().toString()); // put sign
       				
       				friendsList.add(entry); // add to list
        		}
        		
       			context.put("A" + i.toString(), friendsList); // add to context
    		}
	        
    		template.merge(context, writer); // add context to primitive stream
    		out.write(writer.toString()); // write to output
    		out.flush(); // flush
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fWriter != null) {
					fWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
