public class Printjob implements Comparable<Printjob>{
    private String nm;
    private int user_priority, numpages;
    private String jobType;                                   // the different data in a Printjob object
    
    public Printjob(String name, int priority, int pages, String type) {
        nm = name;
        user_priority = priority;
        numpages = pages;
        jobType = type;                                     // printjob are given with all data
    }
    
    public String getName() {
        return nm;
    }
    
    public int getUser_Priority() {
        return user_priority;
    }

    public int getNumPages() {
        return numpages;
    }

    public String getJobType() {
        return jobType;                                     // different methods for retrieving data
    }

    public int getPriority() {
        return getUser_Priority() * getNumPages();          // getting overall priority
    }

    @Override
    public int compareTo(Printjob compare) {
        if (this.getPriority() < compare.getPriority()) {
            return -1;
        }
        else {
            return 1;
        }
    }                                                       // override compareto to return the highest priority 
}
