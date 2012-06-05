package lab3p1;

import java.util.*;
import java.io.*;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Agenda class
 */
public class Agenda implements Serializable {

    /*
     * data
     */
    private Set<Friend> friends = new HashSet<Friend>();

    public boolean equals(Agenda agenda) {
        return friends.equals(agenda.friends);
    }

    /**
     * <b>Description</b>: Adds friend to Agenda
     * @param friend friend to be added to Agenda in Friend format
     * @returns status of addition: true for success or false for failure
     */
    public boolean addFriend(Friend friend) {
        /* adds friend */

        if (friends.contains(friend) == true) {
            /* friend already exists */
            return false;
        }

        friends.add(friend); // add friend
        return true;
    }

    /**
     * <b>Description</b>: Removes friend from Agenda
     * @param friend friend to be removed from Agenda in Friend format
     * @returns status of removal: true for success or false for failure
     */
    public boolean removeFriend(Friend friend) {
        /* removes friend */

        if (friends.contains(friend) == false) {
            /* friend does not exist */
            return false;
        }

        friends.remove(friend); // remove friend
        return true;
    }

    /**
     * <b>Description</b>: Counts friends in Agenda
     * @returns number of friends in Integer format
     */
    public Integer countFriends() {
        /* counts friends */
        return friends.size(); // return
    }

    /**
     * <b>Description</b>: Serializes friends from file
     * @param file filename of file from which data is serialized
     */
    public void read(String file) {
        /* reads friends */

        FileInputStream fis = null; // primitive stream
        ObjectInputStream in = null; // processing stream

        try {
            fis = new FileInputStream(file); // primitive stream
            in = new ObjectInputStream(fis); // processing stream
            Agenda A = (Agenda) in.readObject();
            this.friends = A.friends;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <b>Description</b>: Serializes friends to file
     * @param file filename of file to which data is serialized
     */
    public void write(String file) {
        /* writes friends */

        FileOutputStream fos = null; // primitive stream
        ObjectOutputStream out = null; // processing stream

        try {
            fos = new FileOutputStream(file); // primitive stream
            out = new ObjectOutputStream(fos); // processing stream
            out.writeObject(this); // write object
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <b>Description</b>: Creates a report
     * @param report report to be made
     */
    public void createReport(Report report) {
        Set<Friend> copyFriends = new HashSet<Friend>();
        copyFriends.addAll(this.friends);
        report.create(copyFriends);
    }

    /**
     * <b>Description</b>: gets sign
     * @return sign in Integer format
     */
    public static Integer getSign(Integer day, Integer month) {
        /* get sign */

        if (month == 3) {
            if (day < 21) {
                return 12;
            } else {
                return 1;
            }
        } else if (month == 4) {
            if (day < 21) {
                return 1;
            } else {
                return 2;
            }
        } else if (month == 5) {
            if (day < 22) {
                return 2;
            } else {
                return 3;
            }
        } else if (month == 6) {
            if (day < 22) {
                return 3;
            } else {
                return 4;
            }
        } else if (month == 7) {
            if (day < 23) {
                return 4;
            } else {
                return 5;
            }
        } else if (month == 8) {
            if (day < 23) {
                return 5;
            } else {
                return 6;
            }
        } else if (month == 9) {
            if (day < 22) {
                return 6;
            } else {
                return 7;
            }
        } else if (month == 10) {
            if (day < 23) {
                return 7;
            } else {
                return 8;
            }
        } else if (month == 11) {
            if (day < 22) {
                return 8;
            } else {
                return 9;
            }
        } else if (month == 12) {
            if (day < 21) {
                return 9;
            } else {
                return 10;
            }
        } else if (month == 1) {
            if (day < 20) {
                return 10;
            } else {
                return 11;
            }
        } else {
            if (day < 19) {
                return 11;
            } else {
                return 12;
            }
        }
    }
}
