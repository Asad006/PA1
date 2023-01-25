# Programming Assignment 1.
This program finds prime numbers from 1 to 10^8 using 8 Threads.
Threads share the work load equally.

### Execution Instructions
This is an IntelliJ project. it can easily clone to a new project from IntelliJ, 
by clicking on Open from CVS then copy the clone link of the project.
This project can be also executed by downloading the project then open it by any IDE.
On a Terminal, get into the src file where Counter.java is located, then execute the following commands
javac Counter.java followed with java Counter.

## Approach
I used a fixed thread pool to generate the 8 threads required for the assignment.
To test the prime numbers, I used the square root approach as it gives a faster execution time without
fully optimize as seive does.

## Correctness and Efficiency
Using multiple threads in this problem runs into the problem of equally sharing the work among threads.
To achieve an equal sharing of the work load, a counter is used as a shared resource for all threads, and each thread has
 mutual exclusion once it access it. The counter keeps
truck of the numbers that being tested whether it's a prime or not. The counter is an AtomicDouble, and the method getAnd
Increment() is called to increment the counter. The same idea is used for the sum of primes, the number of prime, and to save the primes.
The program runs little around the 11 s on i5 surface pro. IT could run faster of a more capable machine with more core CPUs.
For a Java based program 11s is very acceptable time of execution using square root optimization to find primes.


