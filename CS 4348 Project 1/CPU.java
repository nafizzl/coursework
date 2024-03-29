import java.io.*;
import java.util.Scanner;

    public class CPU {                                          // start of CPU class
        private int PC, SP, IR, AC, X, Y;                       // CPU registers
        private int address;                                    // variable holding address
        private static int timer, instructions;                 // keep track of timer interrupt limit and current instructions run
        private int sysStack, userStack;                        // stack limits
        private boolean userMode;                               // user mode or kernel mode checker
        private Memory mem;                                     // CPU will contain Memory object for interaction

        public CPU(int timerLength) {                           // constructor for CPU, with custom timer input
            PC = IR = AC = X = Y = 0;
            SP = 1000;
            mem = new Memory();
            sysStack = 1999;
            userStack = 999;
            userMode = true;
            instructions = 0;
            timer = timerLength;
        }

        public void fetch() {                           // fetch method that CPU will use to get instructions from memory
            IR = mem.read(address);
        }

        public void execute() {                         // execute method with long switch statement to account for different possible instructions
            while (true) { 
                switch (IR) {
                    case 1:
                                
                    case 2:

                    case 3:

                    case 4:

                    case 5:

                    case 6:

                    case 7:

                    case 8:

                    case 9:

                    case 10:

                    case 11:

                    case 12:

                    case 13:

                    case 14:

                    case 15:

                    case 16:

                    case 17:

                    case 18:

                    case 19:

                    case 20:

                    case 21:

                    case 22:

                    case 23:

                    case 24:

                    case 25:

                    case 26:

                    case 27:

                    case 28:

                    case 29:

                    case 30:

                    case 50:

                    default:
                }
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

        public void jumpIfNotEqual(int addr) {              // 22. Jump to the input address if the value in AC register is not 0
            if (AC != 0) {
                address = addr;
            }
        }

        public void endInstructions() {                     // 50. end the instructions, exit program
            System.exit(0);
        }

        
        //---------END OF INSTRUCTION SET---------------//

        //---------HELPFUL SUBMETHODS---------------//

        public static void main(String[] args) {
            if (args.length != 2) {
                System.out.println("Invalid argument length. Please include an input file and a timer length of your choice.");
                System.exit(1);
            }
            else {
                try {
                    @SuppressWarnings("deprecation")
                    Process mem = Runtime.getRuntime().exec("java OS$Memory" + args[0]);

                }
                catch (IOException e) {
                    System.out.println("Error when making processes.");
                }
                System.exit(0);         // exit program
            }
        }
        
    static class Memory {                                          // start of memory class
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

        public static void main(String[] args) {
            Scanner
        }
    }   // end of Memory class
    
}   // end of CPU class

