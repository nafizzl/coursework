#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int fibonacci(int n) {
    if (n <= 1) {
        return n;
    } else {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Error, please enter the nth Fibonacci number you wish to calculate (starts from 0th term).\n");
        return 1;
    }

    int n = atoi(argv[1]);

    if (n < 0) {
        printf("Error, please enter a non-negative number.\n");
        return 1;
    }

    int pipes[n + 1][2];  // Create an array of pipes, one for each child

    for (int i = 0; i <= n; ++i) {
        if (pipe(pipes[i]) == -1) {
            perror("pipe");
            return 1;
        }

        pid_t pid = fork();

        if (pid == -1) {
            perror("fork");
            return 1;
        } else if (pid == 0) {
            // Child process
            close(pipes[i][0]);  // Close read end of the pipe
            int result = fibonacci(i);
            write(pipes[i][1], &result, sizeof(result));
            close(pipes[i][1]);  // Close write end of the pipe
            exit(EXIT_SUCCESS);
        }
    }

    for (int i = 0; i <= n; ++i) {
        // Parent process
        close(pipes[i][1]);  // Close write end of the pipe

        int result;
        read(pipes[i][0], &result, sizeof(result));
        close(pipes[i][0]);  // Close read end of the pipe

        printf("Parent: Received a child's result for Fibonacci(%d) = %d\n", i, result);

        wait(NULL);  // Wait for the child to finish
    }

    return 0;
}
