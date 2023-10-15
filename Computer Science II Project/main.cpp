#include <iostream>
#include <fstream>
#include "BaseSorterB.h"
#include "BubbleSorterB.h"
#include "InsertionSorterB.h"
#include "QuickSorterB.h"

using namespace std;

int main(int argc, char* argv[]){
	if (argc != 3) { // checks to see that there's two arguments given in command line, 3 because the first index of array will be taken up by file location
      cout << "Invalid number of command line arguments." << endl;
      cout << "Please rerun the program with two command line arguments:" << endl;
      cout << "The first argument is the text file with numbers, and the second is the case-sensitive keyword indicating which sorting method you wish to use." << endl;
      return 1; // 1 indicates error
   }
   
   ifstream ifsmain;
   ifsmain.open(argv[1]);
   if (!ifsmain.is_open()) {
      cout << "Could not open file." << endl;
      return 1; // 1 indicates error
   } // tests to see if stream can even open file
   
   int dumDum;
   int numNum = -1;
   while (!ifsmain.fail()){
   	ifsmain >> dumDum;
	numNum++;
   } // I used a dummy variable for the input stream to read the numbers and recorded the number of times it read numbers. I start from -1 because it adds an extra when I start with 0 and get 1 more number than what's on the file.
   
   // depending on the entered keyword, the desired sorting object is initialized, in which runtime polymorphism is then used to determine where to use the sort methods from      
   if (string(argv[2]) == "bubble"){ 
   BubbleSorter sortMain = BubbleSorter(ifsmain, numNum);
   cout << "Numbers in file before sorting:" << endl;
   sortMain.print();
   cout << endl;
   sortMain.sort();
   cout << "Numbers in file after sorting:" << endl;
   sortMain.print();
   cout << endl;
   }// statements for bubble sort
   else if (string(argv[2]) == "insertion"){
   InsertionSorter sortMain2 = InsertionSorter(ifsmain, numNum);
   cout << "Numbers in file before sorting:" << endl;
   sortMain2.print();
   cout << endl;
   sortMain2.sort();
   cout << "Numbers in file after sorting:" << endl;
   sortMain2.print();
   }// statements for insertion sort
   else if (string(argv[2]) == "quick"){
   QuickSorter sortMain3 = QuickSorter(ifsmain, numNum);
   cout << "Numbers in file before sorting:" << endl;
   sortMain3.print();
   cout << endl;
   sortMain3.sort();
   cout << "Numbers in file after sorting:" << endl;
   sortMain3.print();
  }// statements for quick sort
    else{
        cout << "You entered an invalid argument. Please keep in mind this is case-sensitive." << endl;
        cout << "Enter 'bubble' for Bubble Sort." << endl;
        cout << "Enter 'insertion' for Insertion Sort." << endl;
        cout << "Enter 'quick' for Quick Sort." << endl;
    }// outputs extra messages in case desired keywords weren't found
   
}