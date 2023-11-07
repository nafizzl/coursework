import Printjob;
import OutsidePrintjob;
import java.io.File;

public class Printer<AnyType> {
    private static class PrinterBinaryNode<AnyType> {
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
    }

    private PrinterBinaryNode<AnyType> insert(Printjob newJob, PrinterBinaryNode<AnyType> nowJob) {
        if (nowJob == null) {
            return new PrinterBinaryNode<>(newJob);
        }                                                   // if we reach a null node, just add that newJob
        else if (nowJob.job.getPriority() == nowJob.job.compareTo(newJob)) {
            if (nowJob.left == null) {
                insert(newJob, nowJob.left);
            }
            else {
                insert(newJob, nowJob.right);
            }
        }                                                   // if the priority of the newjob is lower, add it via pointer
        else {
            Printjob temp = nowJob.job;
            nowJob.job = newJob;
            if (nowJob.left == null) {
                insert(temp, nowJob.left);
            }
            else {
                insert(temp, nowJob.right);
            }                                              // if the priority of the newJob is higher, replace the current job and move the lower priority job down with recursive insert, rearranging everything
        }

        return nowJob;
    }



    public void remove() {
        currentJob = remove(currentJob);
    }
    
    private PrinterBinaryNode<AnyType> remove(PrinterBinaryNode<AnyType> currentJob) {

    }

    public static void main(String[] args) {
        String file = args[0];
        File inputFile = new File(file);

    }
}