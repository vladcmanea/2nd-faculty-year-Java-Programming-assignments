package lab3p1;

import java.util.Set;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Report Interface
 */
public interface Report {

    /**
     * <b>Description</b>: Creates a report
     * @param friends set of friends
     */
    void create(Set<Friend> friends);
}
