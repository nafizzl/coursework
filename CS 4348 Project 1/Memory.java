public class Memory {                                   // memory class
    private int[] storage;                              // int array storing numbers

    public Memory() {                                   // memory constructor, intializes a 2000 space array
        storage = new int[2000];
    }

    public int read(int address) {                      // memory read method, which returns the data at an address
        return storage[address];
    }
    
    public void write(int address, int data) {          // memory write method, which replaces the data at an address with input
        storage[address] = data;
    }
}
