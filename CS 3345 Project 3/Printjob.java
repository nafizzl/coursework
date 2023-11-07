public class Printjob implements Comparable<Printjob>{
    private String nm;
    private int user_priority, numpages;
    private char jobType;                                   // the different data in a Printjob object
    
    public Printjob(String name, int priority, int pages, char type) {
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

    public char getJobType() {
        return jobType;                                     // different methods for retrieving data
    }

    public int getPriority() {
        return getUser_Priority() * getNumPages();          // getting overall priority
    }

    @Override
    public int compareTo(Printjob compare) {
        if (this.getPriority() < compare.getPriority()) {
            return this.getPriority();
        }
        else {
            return compare.getPriority();
        }
    }                                                       // override compareto to return the highest priority 
}