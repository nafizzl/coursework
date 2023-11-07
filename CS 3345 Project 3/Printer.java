import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Printer<AnyType> {
    private static class PrinterBinaryNode<AnyType> {           // binary node subclass
        Printjob job;
        PrinterBinaryNode<AnyType> left;
        PrinterBinaryNode<AnyType> right;

        public PrinterBinaryNode(Printjob newJob) {
            job = newJob;
            left = null;
            right = null;
        }

        public PrinterBinaryNode(Printjob next, PrinterBinaryNode<AnyType> lft, PrinterBinaryNode<AnyType> rgt) {
            job = next;
            left = lft;
            right = rgt;
        }

    }

    private PrinterBinaryNode<AnyType> currentJob;

    public Printer() {
        currentJob = null;
    }

    public void insert(Printjob newJob) {
        currentJob = insert(newJob, currentJob);
        System.out.println("Inserted: " + newJob.getName() + ", " + newJob.getNumPages() + ", " + newJob.getUser_Priority() + ", " + newJob.getJobType() + "    |    Priority: " + newJob.getPriority());
    }   // public accessor method with printline

    private PrinterBinaryNode<AnyType> insert(Printjob newJob, PrinterBinaryNode<AnyType> nowJob) {
        if (nowJob == null) {
            return new PrinterBinaryNode<>(newJob);
        }                                                   // if we reach a null node, just add that newJob
        else if (nowJob.job.compareTo(newJob) < 0) {
            if (nowJob.left == null) {
                nowJob.left = insert(newJob, nowJob.left);
            }
            else if (nowJob.right == null){
                nowJob.right = insert(newJob, nowJob.right);
            }
            else {
                nowJob.left = insert(newJob, nowJob.left);      // these conditionals ensure an evenly filled tree
            }
        }                                                   // if the priority of the newjob is lower, add it via pointer
        else {
            Printjob temp = nowJob.job;
            nowJob.job = newJob;
            if (nowJob.left == null) {
                nowJob.left = insert(temp, nowJob.left);
            }
            else {
                nowJob.right = insert(temp, nowJob.right);
            }                                              // if the priority of the newJob is higher, replace the current job and move the lower priority job down with recursive insert, rearranging everything
        }

        return nowJob;
    }



    public void remove() {
        System.out.println("Removed: " + currentJob.job.getName() + ", " + currentJob.job.getNumPages() + ", " + currentJob.job.getUser_Priority() + ", " + currentJob.job.getJobType() + "    |    Priority: " + currentJob.job.getPriority());
        currentJob = remove(currentJob);
    }   // public accessor method with printline
    
    private PrinterBinaryNode<AnyType> remove(PrinterBinaryNode<AnyType> currentJob) {
        if (currentJob == null) {
            return null;
        }
        else if( currentJob.left != null && currentJob.right != null ) // Two children
        {
            if (currentJob.left.job.compareTo(currentJob.right.job) < 0) {
                currentJob.job = currentJob.left.job;
                currentJob.left = remove(currentJob.left);
            }
            else {
                currentJob.job = currentJob.right.job;
                currentJob.right = remove(currentJob.right);
            }                                                               // overwrite currentJob and remove the clone
        }
        else {
            currentJob = ( currentJob.left != null ) ? currentJob.left : currentJob.right;
        }                                                                   // just whichever one is there 
        return currentJob;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("File not inserted, run this program with a file linked.\n");
            System.exit(0);
        }                                                                  // remind user to include file
        System.out.println("File received, parsing and inserting...\n");
        Printer<Printjob> printer = new Printer<>(); 
        String file = args[0];
        File inputFile = new File(file);                                    // initialize
        try (Scanner scanner = new Scanner(inputFile)) {                    // try and catch using Scanner object to parse and retrieve info
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("\t");

                if (details.length == 4) {
                    String name = details[0];
                    int numPages = Integer.parseInt(details[1]);
                    int userPriority = Integer.parseInt(details[2]);
                    String jobType = details[3];

                    if (jobType.equals("I")) {
                        printer.insert(new Printjob(name, numPages, userPriority, jobType));
                    }
                    else {
                        printer.insert(new OutsidePrintjob(name, userPriority, numPages, jobType));
                    }                                                       // creates job based on type
                }
                else {
                    System.out.println("Invalid job format.\n");
                }
            }
        }
        catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        System.out.println("\nPrinting all jobs...\n");
        while (printer.currentJob != null) {
            printer.remove();
        }                                                                   // print everything

        System.out.println("\nPrinter is empty.\n");
    }
}
