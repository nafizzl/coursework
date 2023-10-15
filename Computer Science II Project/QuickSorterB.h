#ifndef QUICKSORTERB_H
#define QUICKSORTERB_H

#include <fstream>
using namespace std;

class QuickSorter : virtual public BaseSorter { // specified child/parent relation and virtuality
public:
	 
  QuickSorter(ifstream& ifs, int size); // constructor adjusted
  
  ~QuickSorter();
  
  void print();
  
  void sort();

protected:
  
  void sort(int start, int end);
  
  int partition(int start, int end); // added these last two to work with overriden non-argument sort()

  int *buf;
  int numEntries;
};

#endif // QUICKSORTERB_H