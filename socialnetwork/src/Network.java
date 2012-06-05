import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Person
 * 
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 */
public class Network {

    /**
     * Data
     */
    private Map<String, Person> persons = new HashMap<String, Person>(); // persons
    private Map<String, HashMap<String, Integer>> friends =
            new HashMap<String, HashMap<String, Integer>>(); // friends
    private Map<String, HashSet<String>> pretenders =
            new HashMap<String, HashSet<String>>(); // pretenders

    /**
     * <b>Description</b>: Adds a person with an user name<br>
     * <b>Author</b>: Vlad Manea
     * @param username unique name of person in String format
     * @param person person to be added with the user name, in Person format
     * @returns success status in boolean format (true for successful)
     * @see String
     */
    public boolean addPerson(String username, Person person) {
        /* add person */
    	
    	if (this.persons.containsKey(username) == true) {
    		/* person already exists */
    		return false;
    	}
        
    	this.persons.put(username, person); // add to persons
        this.friends.put(username, new HashMap<String, Integer>()); // add to friends
        this.pretenders.put(username, new HashSet<String>()); // add to pretenders
        return true; // addition succeeded
    }

    /**
     * <b>Description</b>: Removes a person with an user name<br>
     * <b>Author</b>: Vlad Manea
     * @param username unique name of person in String format
     * @returns success status in boolean format (true for successful)
     * @see String
     */
    public boolean removePerson(String username) {
        /* remove person */
        
    	if (this.persons.containsKey(username) == false) {
            /* person does not exist */
    		return false; // removal failed
        }

        Iterator<String> it = this.pretenders.get(username).iterator(); // it
        
        while (it.hasNext()) {
            /* iterate through pretenders of current person */
            
        	String pretender = it.next(); // get pretender
            this.friends.get(pretender).remove(username); // remove from friends
        }

        HashMap<String, Integer> friends = this.friends.get(username); // friends
        Iterator<Map.Entry<String, Integer>> jt = friends.entrySet().iterator(); // it
        
        while (jt.hasNext()) {
            /* iterate through friends of current person */
            
        	String friend = jt.next().getKey(); // get friend
            this.pretenders.get(friend).remove(username); // remove from pretenders
        }

        this.friends.remove(username); // remove from friends
        this.pretenders.remove(username); // remove from pretenders
        this.persons.remove(username); // remove from persons
        return false; // removal succeeded
    }

    /**
     * <b>Description</b>: Puts friend to person with an user name<br>
     * <b>Author</b>: Vlad Manea
     * @param pretender unique name of pretending person in String format
     * @param friend unique name of proposed person in String format
     * @param degree degree of friendship (1..10, 1 for close)
     * @returns success status in boolean format (true for successful)
     * @see String
     * @see Integer
     */
    public boolean putFriendToPerson(String pretender, String friend, Integer degree) {
        /* puts a friend */
        
    	if (degree < 1 || degree > 10) {
            /* degree is wrong */
    		return false; // put failed
        }
        
    	if (this.persons.containsKey(pretender) == false) {
            /* pretender does not exist */
    		return false; // put failed
        }
        
    	if (this.persons.containsKey(friend) == false) {
            /* friend does not exist */
    		return false; // put failed
        }

        this.friends.get(pretender).put(friend, degree); // put to friends
        this.pretenders.get(friend).add(pretender); // put to pretenders
        return true; // put succeeded
    }

     /**
     * <b>Description</b>: Removes friend to person with an user name<br>
     * <b>Author</b>: Vlad Manea
     * @param pretender unique name of pretending person in String format
     * @param friend unique name of proposed person in String format
     * @returns success status in boolean format (true for successful)
     * @see String
     */
    public boolean removeFriendOfPerson(String pretender, String friend) {
        /* removes a friend */
        
    	if (this.persons.containsKey(pretender) == false) {
            /* pretender does not exist */
            return false; // removal failed
        }
        
    	if (this.persons.containsKey(friend) == false) {
            /* friend does not exist */
            return false; // removal failed
        }

        this.pretenders.get(friend).remove(pretender); // remove from pretenders
        this.friends.get(pretender).remove(friend); // remove from friends
        return true; // removal succeeded
    }

    /**
     * <b>Description</b>: Returns the number of pretenders of a person<br>
     * <b>Author</b>: Vlad Manea
     * @param person unique name of person in String format
     * @returns number of pretenders or -1 if person does not exist in Integer format 
     * @see String
     * @see Integer
     */
    public Integer getPopularity(String person) {
        /* get popularity */
        
    	if (this.persons.containsKey(person) == false) {
            /* person does not exist */
            return -1; // not popular
        }

        return this.pretenders.get(person).size(); // return size of pretenders
    }

    /**
     * <b>Description</b>: Returns the number of friends of a person<br>
     * <b>Author</b>: Vlad Manea
     * @param person unique name of person in String format
     * @returns number of friends or -1 if person does not exist in Integer format 
     * @see String
     * @see Integer
     */
    public Integer getFriendship(String person) {
        /* get friendship */
        
    	if (this.persons.containsKey(person) == false) {
            /* person does not exist */
            return -1; // not popular
        }

        return this.friends.get(person).size(); // return size of friends
    }

    /**
     * <b>Description</b>: Returns as a string the network in the format
     * { pretender1 ... pretenderk } => user => { friend1 ... friendj }<br>
     * <b>Author</b>: Vlad Manea
     * @return string representing network in String format
     * @see String
     */
    public String toString() {
        /* writes a string */
        
    	StringBuilder answer = new StringBuilder(""); // betweenness
        Iterator<Map.Entry<String, Person>> it = this.persons.entrySet().iterator(); // it
        
        while (it.hasNext()) {
            /* iterate all persons in network */
            
        	Map.Entry<String, Person> current = it.next(); // current person
            answer.append("{ ");
            Iterator<String> jt = this.pretenders.get(current.getKey()).iterator(); // it
            
            while (jt.hasNext()) {
                /* iterate through pretenders of current person */
                
            	String pretender = jt.next(); // get pretender
                answer.append(pretender);
                answer.append("(");
                answer.append(this.persons.get(pretender).getFirstName());
                answer.append(" ");
                answer.append(this.persons.get(pretender).getLastName());
                answer.append(") ");
            }

            answer.append("} => ");
            answer.append(current.getKey());
            answer.append("(");
            answer.append(current.getValue().getFirstName());
            answer.append(" ");
            answer.append(current.getValue().getLastName());
            answer.append(")");
            answer.append(" => { ");

            HashMap<String, Integer> friends = this.friends.get(current.getKey()); // frds
            Iterator<Map.Entry<String, Integer>> kt = friends.entrySet().iterator(); // it
            
            while (kt.hasNext()) {
                /* iterate through friends of current person */
               
            	String friend = kt.next().getKey(); // get friend
                answer.append(friend);
                answer.append("(");
                answer.append(this.persons.get(friend).getFirstName());
                answer.append(" ");
                answer.append(this.persons.get(friend).getLastName());
                answer.append(") ");
            }
            
            answer.append("} \n");
        }

        return answer.toString();
    }

    /**
     * <b>Description</b>: Returns distance between two persons<br>
     * <b>Author</b>: Vlad Manea
     * @param source unique name of person to search from in String format
     * @param destination unique name of person to search for in String format
     * @return minimum distance between source and destination in Integer format
     * @see String
     * @see Integer
     */
    public Integer getDistance(String source, String destination) {
        /* gets distance between two persons */
        
    	if (this.persons.containsKey(source) == false) {
            /* source does not exist */
            return -1; // get failed
        }
        
    	if (this.persons.containsKey(destination) == false) {
            /* friend does not exist */
            return -1; // get failed
        }

        Queue<String> queue = new LinkedList<String>(); // queue
        Map<String, Integer> dist = new HashMap<String, Integer>(); // distances
        Integer cMinPath = -1; // cMinPath

        dist.put(source, 0); // the distance between this and this is 0
        queue.add(source); // this person is in the queue

        while (queue.isEmpty() == false) {
            /* iterate queue */
            
        	String cPers = queue.poll(); // person
            Integer cDist = dist.get(cPers); // distance
            
            if ((cMinPath < 0) || ((cMinPath >= 0) && (cDist <= cMinPath))) {
                /* can continue */
                
            	Iterator<Map.Entry<String, Integer>> jt =
                        this.friends.get(cPers).entrySet().iterator(); // declare iterator
                
            	while (jt.hasNext()) {
                    /* iterate FOAF */
                    
            		String cFriend = jt.next().getKey(); // friend
                    Integer fDist = this.friends.get(cPers).get(cFriend); // distance

                    if ((cFriend == destination)
                            && (((cMinPath >= 0) && (cDist + fDist < cMinPath))
                            || (cMinPath == -1))) {
                        /* set cMinPath */
                        cMinPath = cDist + fDist; // set cMinPath
                    }

                    if ((dist.containsKey(cFriend) == true)
                            && (cDist + fDist < dist.get(cFriend))
                            && ((cMinPath < 0) || ((cMinPath >= 0)
                            && (cDist + fDist <= cMinPath)))
                            || dist.containsKey(cFriend) == false) {
                        /* already visited and relaxing / not visited */
                        
                    	dist.put(cFriend, cDist + fDist); // put new distance
                        queue.add(cFriend); // add friend to queue
                    }
                }
            }
        }

        if (dist.containsKey(destination) == true) {
            /* destination found */
            return dist.get(destination); // return distance
        } else {
            /* destination not found */
            return -1; // failure
        }
    }

    /**
     * <b>Description</b>: Returns all friends on a specified radius<br>
     * <b>Author</b>: Vlad Manea
     * @param source unique name of person to be searched from in String format
     * @param distance radius value in Integer format
     * @return a map of persons and their distances on that radius in Map format
     * @see String
     * @see Integer
     * @see Map
     */
    public Map<String, Integer> getFriendsAtDistance(String source, Integer distance) {
        /* gets persons at maximum distance */
        
    	if (this.persons.containsKey(source) == false) {
            /* source does not exist */
            return new HashMap<String, Integer>(); // get failed
        }

        Map<String, Integer> dist = new HashMap<String, Integer>(); // distances
        Queue<String> queue = new LinkedList<String>(); // queue

        dist.put(source, 0); // the distance between this and this is 0
        queue.add(source); // this person is in the queue

        while (queue.isEmpty() == false) {
            /* iterate queue */

            String cPers = queue.poll(); // current person
            Integer cDist = dist.get(cPers); // distance
            
            if (cDist <= distance) {
                /* can continue */
                
            	Iterator<Map.Entry<String, Integer>> jt =
                        this.friends.get(cPers).entrySet().iterator(); // declare iterator
                
            	while (jt.hasNext()) {
                    /* iterate FOAF */

                    String cFriend = jt.next().getKey(); // current friend
                    Integer fDist = this.friends.get(cPers).get(cFriend); // distance

                    if ((cDist + fDist <= distance)
                            && ((dist.containsKey(cFriend) == false)
                            || ((dist.containsKey(cFriend) == true)
                            && (cDist + fDist < dist.get(cFriend))))) {
                        /* already visited and relaxing / not visited */

                        dist.put(cFriend, cDist + fDist); // put new distance
                        queue.add(cFriend); // add friend to queue
                    }
                }
            }
        }

        return dist;
    }

    /**
     * <b>Description</b>: Returns betweenness of a person<br>
     * <b>Author</b>: Vlad Tudose
     * @param username unique name of person in String format
     * @returns Betweenness of user name when it exists or -1 otherwise
     * @see String
     */
    public double getBetweenness(String username) {
        /* gets betweenness */
        
    	if (this.persons.containsKey(username) == false) {
            /* person does not exist */
            return -1; // getBetweennes failed
        }

        Map<Map.Entry<String, String>, Integer> distance =
                new HashMap<Map.Entry<String, String>, Integer>(); // distances

        Map<Map.Entry<String, String>, Double> nrShorthestPath =
                new HashMap<Map.Entry<String, String>, Double>(); // number of shortest paths

        Iterator<Map.Entry<String, Person>> it = this.persons.entrySet().iterator();
        Iterator<Map.Entry<String, Person>> jt;
        Iterator<Map.Entry<String, Person>> kt = this.persons.entrySet().iterator();

        /* initialize distance and nrShorthestPath */
        while (it.hasNext()) {
            /* iterate persons */
            
        	String usernameIt = it.next().getKey(); // get user name
            jt = this.persons.entrySet().iterator(); // set iterator

            while (jt.hasNext()) {
                /* iterate persons */
                
            	String usernameJt = jt.next().getKey(); // get user name
                Integer degree = this.friends.get(usernameIt).get(usernameJt); // get friendship degree
                
                distance.put(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameJt),
                        new Integer(degree == null ? -1 : degree.intValue())); // set distance

                nrShorthestPath.put(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameJt),
                        new Double(degree == null ? 0 : 1)); // set nrShorthestPath
            }
        }

        /* compute distance and nrShorthestPath */
        while (kt.hasNext()) {
            /* iterate persons */
            
        	String usernameKt = kt.next().getKey(); // get user name
            it = this.persons.entrySet().iterator(); // declare iterator

            while (it.hasNext()) {
                /* iterate persons */
                
            	String usernameIt = it.next().getKey(); // get user name

                int ikDist = distance.get(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameKt)).intValue(); // get distance from usernameIt to usernameKt

                double ikNrp = nrShorthestPath.get(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameKt)).doubleValue(); // get number of paths from usernameIt to usernameKt

                if ((usernameIt.equals(usernameKt)) || (ikDist == -1)) {
                	/* same person or no path from usernameIt to usernameKt */
                    continue; // jump over
                }

                jt = this.persons.entrySet().iterator(); // set iterator
                while (jt.hasNext()) {
                    /* iterate persons */
                    
                	String usernameJt = jt.next().getKey(); // get user name

                    int kjDist = distance.get(
                            new HashMap.SimpleEntry<String, String>(
                            usernameKt, usernameJt)).intValue(); // get distance from usernameKt to usernameJt

                    double kjNrp = nrShorthestPath.get(
                            new HashMap.SimpleEntry<String, String>(
                            usernameKt, usernameJt)).doubleValue(); // get number of paths from usernameKt to usernameJt

                    int ijDist = distance.get(
                            new HashMap.SimpleEntry<String, String>(
                            usernameIt, usernameJt)).intValue(); // get distance from usernameIt to usernameJt

                    double ijNrp = nrShorthestPath.get(
                            new HashMap.SimpleEntry<String, String>(
                            usernameIt, usernameJt)).doubleValue(); // get number of paths from usernameIt to usernameJt

                    if (usernameJt.equals(usernameIt) || usernameJt.equals(usernameKt) || kjDist == -1) {
                        /* same person or no path from usernameKt to usernameJt */
                        continue; // jump over 
                    }

                    if ((ijDist == -1) || (ijDist >= ikDist + kjDist)) {
                        /* better distance from usernameIt to usernameJt found */
                        
                    	distance.put(
                                new HashMap.SimpleEntry<String, String>(
                                usernameIt, usernameJt),
                                new Integer(ikDist + kjDist)); // set distance

                        nrShorthestPath.put(
                                new HashMap.SimpleEntry<String, String>(
                                usernameIt, usernameJt),
                                new Double(
                                	ijDist > ikDist + kjDist ?	ikNrp * kjNrp 
                                							 :	ijNrp + ikNrp * kjNrp)
                                ); // set nrShorthestPath
                    }
                }
            }
        }

        double betweenness = 0; // initialize betweenness

        it = this.persons.entrySet().iterator(); // set iterator
        
        while (it.hasNext()) {
            /* iterate persons */

            String usernameIt = it.next().getKey(); // get user name

            int iuDist = distance.get(
                    new HashMap.SimpleEntry<String, String>(usernameIt, username)).intValue(); // get distance from usernameIt to user name

            double iuNrp = nrShorthestPath.get(
                    new HashMap.SimpleEntry<String, String>(usernameIt, username)).doubleValue(); // get number of paths from usernameIt to user name

            if (username.equals(usernameIt)) {
            	/* same person */
                continue; // jump over
            }

            jt = this.persons.entrySet().iterator(); // set iterator
            while (jt.hasNext()) {
                /* iterate persons */
                
            	String usernameJt = jt.next().getKey(); // get user name

                int ujDist = distance.get(
                        new HashMap.SimpleEntry<String, String>(username, usernameJt)).intValue(); // get distance from user name to usernameJt

                double ujNrp = nrShorthestPath.get(
                        new HashMap.SimpleEntry<String, String>(username, usernameJt)).doubleValue(); // get number of paths from user name to usernameJt

                int ijDist = distance.get(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameJt)).intValue(); // get distance from usernameIt to usernameJt

                double ijNrp = nrShorthestPath.get(
                        new HashMap.SimpleEntry<String, String>(usernameIt, usernameJt)).doubleValue(); // get number of paths from usernameIt to usernameJt

                if (!(username.equals(usernameJt)
                        || usernameIt.equals(usernameJt)
                        || ijDist == -1
                        || iuDist == -1
                        || ujDist == -1
                        || iuDist + ujDist > ijDist)) {
                    /* betweenness must be increased */
                    betweenness += iuNrp * ujNrp / ijNrp; // increase betweenness
                }
            }
        }

        return betweenness; // answer
    }

    /**
     * <b>Description</b>: Returns closeness of a person<br>
     * <b>Author</b>: Vlad Tudose
     * @param username unique name of person in String format
     * @returns Closeness of user name when it exists or -1 otherwise
     * @see String
     */
    public double getCloseness(String username) {
        /* gets closeness */
        
    	if (this.persons.containsKey(username) == false) {
            /* person does not exist */
            return -1; // getCloseness fail
        }

        Map<String, Integer> distance = new HashMap<String, Integer>(); //distance
        Queue<String> queue = new LinkedList<String>(); // queue

        queue.add(username); //initialize queue
        distance.put(username, new Integer(0)); //initialize distance

        while (!queue.isEmpty()) {
            /* iterate queue */

            String cPers = queue.poll(); //get current person
            Integer cDist = distance.get(cPers); //get distance from user name to current person

            Iterator<Map.Entry<String, Integer>> it =
                    friends.get(cPers).entrySet().iterator(); // declare iterator

            while (it.hasNext()) {
                /* iterate friends of current person */

                String cFriend = it.next().getKey(); //get current friend
                Integer cFriendDist = distance.get(cFriend); //get distance from current friend
                Integer fDist = friends.get(cPers).get(cFriend); //get distance from current person to current friend

                if (cFriendDist == null || cFriendDist > cDist + fDist) {
                    /* better distance found */
                    distance.put(cFriend, cDist + fDist); //update distance
                    queue.add(cFriend); //add current friend to queue
                }
            }
        }

        double sumDistance = 0; //initialize sumDistance
        Iterator<Map.Entry<String, Integer>> it =
                distance.entrySet().iterator(); // declare iterator

        while (it.hasNext()) {
            /* iterate distance */
            sumDistance += it.next().getValue(); //update sumDistance
        }

        if (sumDistance == 0) {
            /* user name is isolated */
            return 0; //return answer
        } else {
            /* user name is not isolated */
            return 1 / sumDistance; //return answer
        }
    }
}
