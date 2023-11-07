public class OutsidePrintjob extends Printjob {
    
    public OutsidePrintjob(String name, int priority, int pages, char type) {
        super(name, priority, pages, type);
    }                                               // constructur uses Printjob's constructur using super
    
    public double getCost() {
            return this.getNumPages() * 0.1;        // an extra method for getting data for outside job cost
    }
    
    
}