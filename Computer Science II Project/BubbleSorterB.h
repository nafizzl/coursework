#ifndef BUBBLESORTERB_H
#define BUBBLESORTERB_H

#include <fstream>
using namespace std;

class BubbleSorter : virtual public BaseSorter {// specified child/parent relation and virtuality
public:

  BubbleSorter(ifstream& ifs, int size); // changed constructor as necessary
  
  ~BubbleSorter();
  
  void print();
  
  void sort();

protected:
  int *buf;
  int numEntries;
  
};

#endif // BUBBLESORTERB_H