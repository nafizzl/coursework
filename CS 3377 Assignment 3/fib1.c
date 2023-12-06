#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdint.h>  // had to use intptr_t because of some type of pointer conversion errors

void* fibonacci(void* arg) {
    intptr_t n = (intptr_t)arg;
    int *result = (int*)malloc(sizeof(int));
    int result1 = 0;
    int result2 = 0;    // fib(x-1) and fib(x-2) result holders if needed

    if (n <= 1) {
        *result = n;    // fib(0) = 0, fib(1) = 1
    } else {
        pthread_t fib1, fib2;

        pthread_create(&fib1, NULL, fibonacci, (void*)(intptr_t)(n - 1));
        pthread_create(&fib2, NULL, fibonacci, (void*)(intptr_t)(n - 2));   //fib(x) = fib(x-1) + fib(x-2)

        
        int *result1_ptr, *result2_ptr;
        pthread_join(fib1, (void**)&result1_ptr);
        pthread_join(fib2, (void**)&result2_ptr);       // threads work out the sequence

        result1 = *result1_ptr;
        result2 = *result2_ptr;         // store results

        free(result1_ptr);
        free(result2_ptr);              // free up memory

        *result = result1 + result2;    // final result
    }
    
    printf("Value of this fibonacci(%ld): %d\n", n, *result);
    pthread_exit(result);               // print values threads received and exit
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Error, please enter the nth fibonacci number you wish to calculate (starts from 0th term).\n");
        return 1;
    }       // requires user to input command line argument

    int n = atoi(argv[1]);

    if (n < 0) {
        printf("Error, please enter a non-negative number.\n");
        return 1;
    }       // can't be negative nth term

    pthread_t fib;
    int* result;

    pthread_create(&fib, NULL, fibonacci, (void*)(intptr_t)(n));
    pthread_join(fib, (void**)&result);     // carry out the function

    printf("Fibonacci(%d) = %d\n", n, *result);     // final result, which is always same

    free(result);       // free up memory

    return 0;
}
