import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CPU {
    private int PC, SP, IR, AC, X, Y;                       // CPU registers
    private Memory mem;                                     // CPU will contain Memory object for interaction
    private int address;
    
    public CPU() {                                  // constructor for CPU, initialize registers with 0 and make the "default" Memory object
        PC = SP = IR = AC = X = Y = 0;
        mem = new Memory();
    }

    public void fetch() {                           // fetch method that CPU will use to get instructions from memory
        IR = mem.read(address);
    }

    public void execute() {                         // execute method with long switch statement to account for different possible instructions
        switch (IR) {
            case 1:
                        
            default:
        }
    }

    //-------------INSTRUCTION SET---------------//
    public void loadValue(int value) {              // 1. load value at next line in input file to AC register
        AC = value;
    }
    
    public void loadAddress(int addr) {          // 2. load value at address in memory to AC register
        address = addr;
        AC = mem.read(address);
    }

    public void loadIndAddress(int addr) {       // 3. load value from address found in given address to AC register
        address = addr;
        AC = mem.read(mem.read(address));
    }

    public void loadIdxXAddress(int addr) {      // 4. load value at address + X to AC register
        address = addr;
        AC = mem.read(address + X);
    }

    public void loadIdxYAddress(int addr) {      // 5. load value at address + Y to AC register
        address = addr;
        AC = mem.read(address + Y);
    }

    public void loadSpXAddress() {                  // 6. load value at SP + X to AC register
        address = SP + X;
        AC = mem.read(address);
    }

    public void storeAddress(int addr) {         // 7. store the value in AC at address 
        address = addr;
        mem.write(address, AC);
    }

    public void getRandom() {
        AC = (int)(Math.random() * 100);            // 8. get a random value from 1-100 and assign to AC register
    }

    public void putPort(int port) {                 // 9. if next number is 1, display value in AC register as int,
        if (port == 1) {                            //    if next number is 2, display value in AC register as char
            System.out.println(AC);
        }
        if (port == 2) {
            System.out.println((char)(AC));
        }
    }

    public void addX() {                            // 10. add value in X register to value in AC register
        AC += X;
    }

    public void addY() {                            // 11. add value in Y register to value in AC register
        AC += Y;
    }

    public void subX() {                            // 12. subtract value in X register from value in AC register
        AC -= X;
    }

    public void subY() {                            // 13. subtract value in Y register to value in AC register
        AC -= Y;
    }

    public void copyToX() {                         // 14. Copy value in AC register to X register
        X = AC;
    }

    public void copyFromX() {                       // 15. Copy value in X register to AC register
        AC = X;     
    }

    public void copyToY() {                         // 16. Copy value in AC register to Y register
        Y = AC;
    }

    public void copyFromY() {                       // 17. Copy value in Y register to AC register
        AC = Y;     
    }

    public void copyToSP() {                         // 18. Copy value in AC register to SP register
        SP = AC;
    }

    public void copyFromSP() {                       // 19. Copy value in SP register to AC register
        AC = SP;     
    }

    public void jumpAddress(int addr) {              // 20. Jump to the input address 
        address = addr;
    }

    public void jumpIfEqual(int addr) {              // 21. Jump to the input address if the value in AC register is 0
        if (AC == 0) {
            address = addr;
        }
    }

    public void jumpIfNotEqual(int addr) {              // 22. Jump to the input address if the value in AC register is 0
        if (AC != 0) {
            address = addr;
        }
    }
    //---------END OF INSTRUCTION SET---------------//

    public static void main(String[] args) {        
        CPU c = new CPU();
    }
}