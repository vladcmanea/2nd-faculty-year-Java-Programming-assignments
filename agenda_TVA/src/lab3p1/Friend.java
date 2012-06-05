package lab3p1;

import java.io.Serializable;

/**
 * @author Vlad Manea
 * @author Vlad Tudose
 * @version 0.1
 * <b>Description</b>: Friend class
 */
public class Friend implements Serializable {

    /*
     * Data
     */
    private String name = ""; // name
    private String address = ""; // address
    private String phone = ""; // phone
    private String web = ""; // web
    private String email = ""; // email
    private Integer day = 1; // day
    private Integer month = 1; // day
    private Integer year = 1; // day

    /**
     * <b>Description</b>: gets day
     * @return day in Integer format
     */
    public Integer getDay() {
        /* gets day */

        Integer copyDay = new Integer(day); // create copy
        return copyDay; // return
    }

    /**
     * <b>Description</b>: gets month
     * @return month in Integer format
     */
    public Integer getMonth() {
        /* gets month */

        Integer copyMonth = new Integer(month); // create copy
        return copyMonth; // return
    }

    /**
     * <b>Description</b>: gets year
     * @return year in Integer format
     */
    public Integer getYear() {
        /* gets year */

        Integer copyYear = new Integer(year); // create copy
        return copyYear; // return
    }

    /**
     * <b>Description</b>: sets valid date privately
     * @param day day in Integer format
     * @param month month in Integer format
     * @param year in Integer format
     */
    private void setValidDate(Integer day, Integer month, Integer year) {
        /* sets date */

        this.day = day; // set day
        this.month = month; // set month
        this.year = year; // set year
    }

    /**
     * <b>Description</b>: sets valid date privately
     * @param day day in Integer format
     * @param month month in Integer format
     * @param year in Integer format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setDate(Integer day, Integer month, Integer year) {
        /* sets date */

        if (1 <= year) {
            if (1 <= month && month <= 12) {
                if (month == 1 || month == 3 || month == 5 || month == 7
                        || month == 8 || month == 10 || month == 12) {
                    if (1 <= day && day <= 31) {
                        setValidDate(day, month, year);
                        return true; // valid in large months
                    }
                } else if (month == 2) {
                    if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
                        if (1 <= day && day <= 29) {
                            setValidDate(day, month, year);
                            return true; // valid in leap year February
                        }
                    } else {
                        if (1 <= day && day <= 28) {
                            setValidDate(day, month, year);
                            return true; // valid in non-leap year February
                        }
                    }
                } else {
                    if (1 <= day && day <= 30) {
                        setValidDate(day, month, year);
                        return true; // valid in small months
                    }
                }
            }
        }

        return false; // invalid
    }

    /**
     * <b>Description</b>: sets name
     * @param name name in String format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setName(String name) {
        /* sets name */

        if (name.length() == 0) {
            return false; // invalid
        } else {
            this.name = name; // set name
            return true; // valid
        }
    }

    /**
     * <b>Description</b>: gets name
     * @return name in String format
     */
    public String getName() {
        /* gets name */
        return this.name; // return
    }

    /**
     * <b>Description</b>: sets address
     * @param address address in String format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setAddress(String address) {
        /* sets address */

        if (address.length() == 0) {
            return false; // failed
        } else {
            this.address = address; // set address
            return true; // successful
        }
    }

    /**
     * <b>Description</b>: gets address
     * @return address in String format
     */
    public String getAddress() {
        /* gets address */
        return this.address; // return
    }

    /**
     * <b>Description</b>: sets phone
     * @param phone phone in String format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setPhone(String phone) {
        /* sets phone */

        if (phone.length() == 0) {
            return false; // failed
        } else {
            this.phone = phone; // set phone
            return true; // successful
        }
    }

    /**
     * <b>Description</b>: gets phone
     * @return phone in String format
     */
    public String getPhone() {
        /* gets phone */
        return this.phone; // return
    }

    /**
     * <b>Description</b>: sets web
     * @param web web in String format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setWeb(String web) {
        /* sets web */

        if (web.length() == 0) {
            return false; // failed
        } else {
            this.web = web; // set web
            return true; // successful
        }
    }

    /**
     * <b>Description</b>: gets web
     * @return web in String format
     */
    public String getWeb() {
        /* gets web */
        return this.web; // return
    }

    /**
     * <b>Description</b>: sets email
     * @param email email in String format
     * @return success status (true for successful, false for failed)
     */
    public Boolean setEmail(String email) {
        /* sets email */

        if (email.length() == 0) {
            return false; // failed
        } else {
            this.email = email; // set email
            return true; // successful
        }
    }

    /**
     * <b>Description</b>: gets email
     * @return email in String format
     */
    public String getEmail() {
        /* gets email */
        return this.email; // return
    }
}
